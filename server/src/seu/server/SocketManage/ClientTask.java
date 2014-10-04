package seu.server.SocketManage;

import java.io.*;

/**
 * Created by rainnus' on 2014/9/17.
 */
public class ClientTask {
    private String i = "";//选择的连接
    private MySocket socket;


    public ClientTask(String i, MySocket socket) {
        this.i = i;
        this.socket = socket;

    }

    public Object sentMessage(String info) {
        Object obj = new Object();
        try {
            Writer writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(info);
            writer.write("eof\n");
            writer.flush();
//            new Thread();

            //??????


//            ObjectInputStream read = new ObjectInputStream(client.getInputStream());
//
//            obj = read.readObject();
//            read.close();
//            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//         catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        return obj;
    }

    public void receive() {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String temp;
            int index;
            while ((temp = br.readLine()) != null) {
                if ((index = temp.indexOf("eof")) != -1) {
                    sb.append(temp.substring(0, index));
                    break;
                }
                sb.append(temp);
                System.out.println("from server: " + sb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
