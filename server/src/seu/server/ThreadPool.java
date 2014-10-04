package seu.server;

import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by rainnus' on 2014/9/2.
 */
public class ThreadPool {

    //线程池类
    public ExecutorService pool = null;
    private Task task = null;

    //初始化线程池
    ThreadPool() {
        pool = Executors.newCachedThreadPool();
        System.out.println("the pool is created");
    }

    //执行线程
    public void execute(Socket socket) {
        task = new Task(socket);
        pool.execute(task);
    }

}
