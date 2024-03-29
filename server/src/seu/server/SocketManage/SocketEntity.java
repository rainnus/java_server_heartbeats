package seu.server.SocketManage;

/**
 * Created by rainnus' on 2014/9/14.
 */

public class SocketEntity {
    /**
     * 连接的名字，以名字作为Key
     */
    private String name;
    /**
     * 连接的IP
     */
    private String ip;
    /**
     * 连接的端口
     */
    private int port;
    /**
     * 是否保持连接
     */
    private boolean keepConn;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public boolean isKeepConn() {
        return keepConn;
    }
    public void setKeepConn(boolean keepConn) {
        this.keepConn = keepConn;
    }
}