package seu.lib.soket;

import java.io.*;
import java.net.Socket;
import javax.swing.JOptionPane;



public class ClientSocket {
    
	Socket client = null;
    String host = "127.0.0.1";
    int port = 8090;

    public ClientSocket() {
        try {
            client = new Socket(host, port);
        } catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "hostName=" + host + " \nportNum=" + port + "\n\n---->IOÒì³£´íÎó" + e.getMessage());
        }
    }

    //Ïò·þÎñÆ÷·¢ËÍÃüÁîÒÔ»ñÈ¡×Ö·û´®ÐÅÏ¢
	public String getStrMessage(String info) {
        
    	StringBuffer sb = new StringBuffer();
    	try {
            Writer writer = new OutputStreamWriter(client.getOutputStream());
            writer.write(info);
            writer.write("eof\n");
            writer.flush();

            //¶Á²Ù×÷
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
        	JOptionPane.showMessageDialog(null, "hostName=" + host + " \nportNum=" + port + "\n\n---->IOÒì³£´íÎó" + e.getMessage());
        }
    	return sb.toString();

    }
	
	//Ïò·þÎñÆ÷·¢ËÍÃüÁîÒÔ»ñÈ¡Àà¶ÔÏóÐÅÏ¢
	
}



