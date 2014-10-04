package seu.server;

/**
 * Created by rainnus' on 2014/9/3.
 */
public class RunClient {

    public static void main (String[] args) {

        for (int i = 0; i<= 10000; i++){
            ClientSocket myClient = new ClientSocket();
            myClient.sentMessage("00/123/3/0");
        }

    }
}
