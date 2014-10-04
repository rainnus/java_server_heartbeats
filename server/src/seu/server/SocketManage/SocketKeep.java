package seu.server.SocketManage;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by rainnus' on 2014/9/13.
 */
public class SocketKeep {
    /**
     * 配置信息加载
     */
    public static List<SocketEntity> socketEntityList = new ArrayList<SocketEntity>();
    /**
     * 连接对象保持，只保持需要系统保持的连接
     */
    public static Map<String, MySocket> socketMap = new LinkedHashMap<String, MySocket>();
    /**
     * 连接对象是否锁定 1：锁定，其他未锁定
     */
    public static Map<String, String> socketIsLock = new LinkedHashMap<String, String>();
    /**
     * 共用连接检测间隔
     */
    public static int commonCheckTime = 2;
    /**
     * 连接的数量，一定要和实际配置的数量匹配
     */
    public static int socketConnCount = 0;
    public static ExecutorService executorService = null;// 线程池
    /**
     * 初始化所有连接信息
     */
    public static void initSocketKeep() {
        Properties properties = null;
        try {
            properties = new Properties();
            String dbPath = new File("").getAbsolutePath().replace('\\', '/')+"/Socket.properties";
            properties.load(SocketKeep.class.getClassLoader().getResourceAsStream("Socket.properties"));
        } catch (Exception e) {
            properties = null;
        }
        if (properties != null) {
            try {
                commonCheckTime = Integer.parseInt(properties.getProperty("commonCheckTime"));
                socketConnCount = Integer.parseInt(properties.getProperty("socketConnCount"));
                executorService = Executors.newFixedThreadPool(socketConnCount + 1);
            } catch (Exception e) {
                executorService = Executors.newFixedThreadPool(1);
                // 系统忽略这两个属性的加载异常
            }

            SocketEntity socketEntity = null;
            for (int i = 1; i <= socketConnCount; i++) {
                String name = properties.getProperty("socket" + i);
                if(null != name){
                    socketEntity = new SocketEntity();
                    String ip = properties.getProperty("socket" + i + "_ip");
                    String port = properties.getProperty("socket" + i + "_port");
                    String isKeep = properties.getProperty("socket" + i + "_isKeep");

                    socketEntity.setName(name);
                    socketEntity.setIp(ip);
                    socketEntity.setPort(Integer.parseInt(port));
                    boolean keepConn = false;
                    if(null != isKeep && "1".equals(isKeep)){
                        keepConn = true;
                    }
                    socketEntity.setKeepConn(keepConn);
                    socketEntityList.add(socketEntity);
                }
            }
        }


        MySocket socket = null;
        for(SocketEntity socketEntity : socketEntityList){
            if(null != socketEntity && socketEntity.isKeepConn()){
                try {
                    socket = new MySocket(socketEntity.getIp(),socketEntity.getPort());
                    socket.setSoTimeout(0);
                    socket.setKeepAlive(true);
                    socket.setName(socketEntity.getName());
                } catch (Exception e) {
//                    System.out.println("初始化某个连接时错误！错误的连接将放弃！资源名称：" + socketEntity.getName());
                    socket = null;
                }
                if(null != socket){
                    socketMap.put(socketEntity.getName(), socket);
                }else{
                    socketMap.put(socketEntity.getName(), new MySocket());
                }
                socketIsLock.put(socketEntity.getName(), "0");
            }
        }
        // 开始执行检查
        executorService.execute(new CheckThread());
//        System.out.println("初始化Socket连接结束！");
    }
}