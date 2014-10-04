package seu.server.SocketManage;

/**
 * Created by rainnus' on 2014/9/13.
 */
public class InitSocket implements Runnable{
    private static boolean isHave = false;
    private SocketEntity socketEntity = null;
    private String name;
    public InitSocket(String name){
        this.name = name;
        // 检测是否有某个连接的配置信息
        for(SocketEntity socketEntity : SocketKeep.socketEntityList){
            if(null != socketEntity && socketEntity.isKeepConn()){
                if(socketEntity.getName().equals(name)){
                    this.setSocketEntity(socketEntity);
                    isHave = true;
                }
            }
        }
    }
    public void run() {
        boolean isError = true;
        MySocket socket = null;
        if(isHave){
            while(isError){
                try {
                    socket = new MySocket(this.getSocketEntity().getIp(),this.getSocketEntity().getPort());
                    socket.setSoTimeout(0);
                    socket.setKeepAlive(true);
                    socket.setName(this.name);
                    // 发送一个心跳包
                    socket.sendUrgentData(0xFF);
                } catch (Exception e) {
                    System.out.println("can't connect " + this.name);
                    socket = null;
                }
                if(null != socket){
                    SocketKeep.socketMap.put(this.getSocketEntity().getName(), socket);
                    // 设置连接当前可用
                    SocketKeep.socketIsLock.put(this.getSocketEntity().getName(), "0");
                    System.out.println("success "+ this.name);
                    isError = false;
                }
                try {
                    Thread.sleep(2 * 1000);
                } catch (Exception e) {
                }

            }
        }else{
            System.out.println("error "+this.name);
        }
        System.out.println("Init socket success " + this.name);
    }
    public SocketEntity getSocketEntity() {
        return socketEntity;
    }
    public void setSocketEntity(SocketEntity socketEntity) {
        this.socketEntity = socketEntity;
    }
}