package seu.server;

import seu.server.SocketManage.*;

import java.net.Socket;

/**
 * Created by rainnus' on 2014/9/17.
 */
public class CheckServerSocket implements Runnable {
    ClientTask clientCheck = null;

    public void run() {
        while (Server.clients != null) {
            MySocket socket = null;
            for (Socket socketExit : Server.clients) {
                if (socketExit != null) {
                    try {
                        // ����һ��������
                        socketExit.sendUrgentData(0xFF);
                        System.out.println("check user number: " + Server.SocketEntityMap.get(socketExit).getUname());
                        System.out.println("success! ");
                        // �ͷ���Դ
                    } catch (Exception e) {
                        System.out.println("socket is interrupted");
                        // ����쳣���ӱ���ɾȥ
                        Server.SocketEntityMap.remove(socketExit);
                        Server.clients.remove(socketExit);
                    }
                }
            }
            try{
                Thread.sleep(5*1000);
                System.out.println("���������� " + Server.clients.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // ִ�м��

    }
}
