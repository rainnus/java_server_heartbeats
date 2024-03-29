package seu.server.SocketManage;

import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by rainnus' on 2014/9/13.
 */

public class CheckThread  implements Runnable {
    ClientTask clientCheck = null;
    public void run() {
        while(true){
            MySocket socket = null;
            for(SocketEntity socketEntity : SocketKeep.socketEntityList){
                if(null != socketEntity && socketEntity.isKeepConn()){
                    String isLock = SocketKeep.socketIsLock.get(socketEntity.getName());
                    // 如果当前未被使用
                    if(!"1".equals(isLock)){
                        // 锁定引用
                        SocketKeep.socketIsLock.put(socketEntity.getName(), "1");
                        socket = SocketKeep.socketMap.get(socketEntity.getName());
                        try {
                            // 发送一个心跳包
                            socket.sendUrgentData(0xFF);
//                            Writer writer = new OutputStreamWriter(socket.getOutputStream());
//                            writer.write("heart"+"eof");
//                            writer.flush();
                            System.out.println("check socket:"+socketEntity.getName());
                            // 释放资源
                            SocketKeep.socketIsLock.put(socketEntity.getName(), "0");
                            System.out.println(socketEntity.getName() + " is ok");
                        } catch (Exception e) {
                            System.out.println("reset socket");
                            // 如果异常，应该建立一个线程去初始化该连接
                            InitSocket initS = new InitSocket(socketEntity.getName());
                            new Thread(initS).start();
                        }
                    }
                }
            }
            // 执行间隔
            try {
                System.out.println(SocketKeep.commonCheckTime + " seconds later check another time");
                Thread.sleep(SocketKeep.commonCheckTime * 1000);
            } catch (Exception e) {
            }
        }
    }
}