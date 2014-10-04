package seu.server;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by rainnus' on 2014/9/1.
 */

public class ClientSocket {
    Socket client = null;
    String host = "127.0.0.1";
    int port = 8090;

    public ClientSocket() {
        try {
            client = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Object sentMessage(String info) {
        Object obj = new Object();
        try {
            Writer writer = new OutputStreamWriter(client.getOutputStream());
            writer.write(info);
            writer.write("eof\n");
            writer.flush();

            //¶Á²Ù×÷
            BufferedReader br = null;
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String temp;
            int index;
            while ((temp = br.readLine()) != null) {
                if ((index = temp.indexOf("eof")) != -1) {
                    sb.append(temp.substring(0, index));
                    break;
                }
                sb.append(temp);
            }
            System.out.println("from server: " + sb);
            br.close();

//            ObjectInputStream read = new ObjectInputStream(client.getInputStream());
//
//            obj = read.readObject();
//            read.close();
            writer.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//         catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        return obj;
    }

}


