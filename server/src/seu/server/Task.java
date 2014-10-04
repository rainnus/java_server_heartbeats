package seu.server;

import seu.server.StringCheck;

import java.io.*;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Task implements Runnable{
    private Socket socket;
    public static boolean isKeep = true;
    public Task(Socket socket) {
        this.socket = socket;
    }

    public void run() {
            try {
                handleSocket();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }

        private void handleSocket() {

            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();
            String temp;
            int index;
            while (isKeep){
                try {
                    br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    temp = br.readLine();
                    if(temp.equals("")) {
                        isKeep = false;
                    }else {
                        while (true) {
                            if ((index = temp.indexOf("eof")) != -1) {
                                sb.append(temp.substring(0, index));
                                break;
                            }
                            sb.append(temp);
                        }
                    }
//                @SuppressWarnings("unused")
                    new StringCheck(sb, socket);
                    System.out.println("from client: " + sb);
                    //∂¡ÕÍ∫Û–¥“ªæ‰

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    public void write (String s) {

        Writer writer = null;
        try {
            writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(s);
            writer.write("eof\n");
            writer.flush();
//            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write (Object object) {
        try {
            ObjectOutputStream objStream = new ObjectOutputStream(socket.getOutputStream());
            objStream.writeObject(object);
            objStream.flush();
//            objStream.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
