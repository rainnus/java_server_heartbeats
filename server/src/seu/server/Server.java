package seu.server;

import seu.entityclass.MainUser;
import seu.entityclass.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by rainnus' on 2014/9/1.
 */

public class Server{

    private int port = 8090; //����8090�˿�
    private ServerSocket server;
    public static ArrayList<Socket> clients;
    public static Map<Socket, MainUser> SocketEntityMap;
    private boolean isStart = false;
    public ThreadPool pool = null;
    private ServerThread serverThread = null;

    public Server() {
    }

    public void serverStart() {
        pool = new ThreadPool();
        try {
            clients = new ArrayList<Socket>();
            server = new ServerSocket(port);
            serverThread = new ServerThread();
            serverThread.start();
            isStart = true;
            System.out.println("�����������ɹ�");
            new Thread(new CheckServerSocket()).start();
        } catch (IOException e) {
            isStart = false;
            System.out.println("�����������쳣");
            e.printStackTrace();
        }

    }

    public void serverStop (){
            if (serverThread != null){
                serverThread.stop();
            }
            for(int i = clients.size()-1; i>=0; i--){
                new Task(clients.get(i)).write("closed");
                try {
                    clients.get(i).close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clients.remove(i);
            }
        if(server != null){
            try {
                server.close();
                isStart = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    class ServerThread extends Thread{
        @Override
        public void run() {

            while (true) {
                //server���Խ�������Socket����������server��accept����������ʽ��
                Socket socket = null;

                try {
                    socket = server.accept();
                } catch (IOException e) {

                }
                //����һ���߳�
//            new socketInterface(socket);
                pool.execute(socket);
            }
        }
    }


}
