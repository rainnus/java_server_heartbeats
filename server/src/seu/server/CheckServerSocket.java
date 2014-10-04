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
                        // 发送一个心跳包
                        socketExit.sendUrgentData(0xFF);
                        System.out.println("check user number: " + Server.SocketEntityMap.get(socketExit).getUname());
                        System.out.println("success! ");
                        // 释放资源
                    } catch (Exception e) {
                        System.out.println("socket is interrupted");
                        // 如果异常，从表中删去
                        Server.SocketEntityMap.remove(socketExit);
                        Server.clients.remove(socketExit);
                    }
                }
            }
            try{
                Thread.sleep(5*1000);
                System.out.println("在线人数： " + Server.clients.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 执行间隔

    }
}
