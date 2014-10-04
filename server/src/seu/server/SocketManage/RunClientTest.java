package seu.server.SocketManage;

import seu.server.ThreadPool;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rainnus' on 2014/9/15.
 */
public class RunClientTest {
    public static void main(String[] args) {
        SocketKeep.initSocketKeep();
//            while(true){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//        while(true){
            try {

                MySocket socket1 = SocketKeep.socketMap.get("socket1");
                ClientTask client1 = new ClientTask("1", socket1);
                if (socket1.isConnected()) {
                    System.out.println(format.format(new Date()) + "Socket1  " + socket1.toString());
                    client1.sentMessage("00/09012420/123/1");
//                    socket1.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

//
            try {
                MySocket socket2 = null;
                for(SocketEntity socketEntity : SocketKeep.socketEntityList){
                    if(socketEntity.getName().equals("socket2")){
                        socket2 = new MySocket(socketEntity.getIp(), socketEntity.getPort());
                    }
                }
                ClientTask client2 = new ClientTask("2", socket2);
                if (null != socket2) {
                    System.out.println(format.format(new Date()) + "socket2  " + socket2.toString());
                    client2.sentMessage("00/123/1");
                    socket2.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
        }

//        }

//        }
