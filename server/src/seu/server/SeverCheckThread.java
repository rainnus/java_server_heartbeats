//package seu.server;
//
//import seu.server.SocketManage.*;
//
//import java.net.Socket;
//
///**
// * Created by rainnus' on 2014/9/17.
// */
//public class SeverCheckThread implements Runnable{
//        ClientTask clientCheck = null;
//        public void run() {
//            while(true){
//                MySocket socket = null;
//                for(Socket socketEntity : Server.SocketEntityMap){
//                    if(null != socketEntity){
//                        String isLock = SocketKeep.socketIsLock.get(socketEntity.getName());
//                        // �����ǰδ��ʹ��
//                        if(!"1".equals(isLock)){
//                            // ��������
//                            SocketKeep.socketIsLock.put(socketEntity.getName(), "1");
//                            socket = SocketKeep.socketMap.get();
//                            try {
//                                // ����һ��������
//                                socket.sendUrgentData(0xFF);
////                            Writer writer = new OutputStreamWriter(socket.getOutputStream());
////                            writer.write("heart"+"eof");
////                            writer.flush();
//                                System.out.println("check socket:"+socketEntity.getName());
//                                // �ͷ���Դ
//                                SocketKeep.socketIsLock.put(socketEntity.getName(), "0");
//                                System.out.println(socketEntity.getName() + " is ok");
//                            } catch (Exception e) {
//                                System.out.println("reset socket");
//                                // ����쳣��Ӧ�ý���һ���߳�ȥ��ʼ��������
//                                InitSocket initS = new InitSocket(socketEntity.getName());
//                                new Thread(initS).start();
//                            }
//                        }
//                    }
//                }
//                // ִ�м��
//                try {
//                    System.out.println(SocketKeep.commonCheckTime + " seconds later check another time");
//                    Thread.sleep(SocketKeep.commonCheckTime * 1000);
//                } catch (Exception e) {
//                }
//            }
//        }
//}
