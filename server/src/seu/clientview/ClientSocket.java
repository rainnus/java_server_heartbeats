package seu.clientview;

import java.io.*;
import java.net.Socket;
import javax.swing.JOptionPane;

import seu.entityclass.*;;

public class ClientSocket {
    
	Socket client = null;
    String host = "127.0.0.1";
    int port = 8090;

    public ClientSocket() {
        try {
            client = new Socket(host, port);
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "hostName=" + host + " \nportNum=" + port + "\n\n---->IO�쳣����" + e.getMessage());
        }
    }

    //����������������Ի�ȡ�ַ�����Ϣ
	public String getStrMessage(String info) {
        
    	StringBuffer sb = new StringBuffer();
    	try {
            Writer writer = new OutputStreamWriter(client.getOutputStream());
            writer.write(info);
            writer.write("eof\n");
            writer.flush();

            //������
            BufferedReader br = null;
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            String temp;
            int index;
            while ((temp = br.readLine()) != null) {
                if ((index = temp.indexOf("eof")) != -1) {
                    sb.append(temp.substring(0, index));
                    break;
                }
                sb.append(temp);
            }
            br.close();
    		writer.close();
            client.close();
            
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "hostName=" + host + " \nportNum=" + port + "\n\n---->IO�쳣����" + e.getMessage());
        }
    	return sb.toString();

    }
	
	//����������������Ի�ȡ�������Ϣ
	public MainUser getMUserMessage(String info){
		MainUser user = new MainUser();
		try {
            Writer writer = new OutputStreamWriter(client.getOutputStream());
            writer.write(info);
            writer.write("eof\n");
            writer.flush();
            
            ObjectInputStream read = new ObjectInputStream(client.getInputStream());
            user = (MainUser)read.readObject();
            read.close();
            writer.close();
            client.close();
            
		}catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.toString());
		}catch (IOException e) {
			JOptionPane.showMessageDialog(null, "hostName=" + host + " \nportNum=" + port + "\n\n---->IO�쳣����" + e.getMessage());
		}
		
 		return user;
	}
}



