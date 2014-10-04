package seu.server;

import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by rainnus' on 2014/9/2.
 */
public class ThreadPool {

    //�̳߳���
    public ExecutorService pool = null;
    private Task task = null;

    //��ʼ���̳߳�
    ThreadPool() {
        pool = Executors.newCachedThreadPool();
        System.out.println("the pool is created");
    }

    //ִ���߳�
    public void execute(Socket socket) {
        task = new Task(socket);
        pool.execute(task);
    }

}
