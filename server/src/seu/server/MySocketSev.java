package seu.server;

import seu.server.SocketManage.SocketKeep;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by rainnus' on 2014/9/17.
 */
public class MySocketSev extends Socket{
    private String name;

    public MySocketSev() {
    }

    public MySocketSev(String ip, int port) throws UnknownHostException, IOException {
        super(ip, port);
    }

//    /**
//     * 覆盖关闭的方法
//     */
//    @Override
//    public synchronized void close() throws IOException {
//        SocketKeep.socketIsLock.put(this.name, "0");
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
