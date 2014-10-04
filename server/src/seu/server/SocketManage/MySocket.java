package seu.server.SocketManage;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by rainnus' on 2014/9/13.
 */
public class MySocket extends Socket{
    private String name;

    public MySocket() {
    }

    public MySocket(String ip, int port) throws UnknownHostException, IOException {
        super(ip, port);
    }

    /**
     * 覆盖关闭的方法
     */
    @Override
    public synchronized void close() throws IOException {
        SocketKeep.socketIsLock.put(this.name, "0");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

