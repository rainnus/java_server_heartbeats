package seu.server;
import seu.server.Handle;

import java.net.Socket;

public class StringCheck {

    @SuppressWarnings("unused")
	private StringBuilder string;
    private int size;
    private int totleSize;
    private String s;
    private Handle handle;
    private String temp;
    private String []part = new String[10];
    private Socket socket;

    public StringCheck(StringBuilder string, Socket socket) {
    	for(int i = 0; i <= 9 ; i++){
    		part[i] = "";
    	}
    	this.totleSize = string.length();
    	if(string.charAt(totleSize-1)=='/') {
    		this.temp = string.substring(0, totleSize-2);
    	}
    	else {
    		this.temp = string.toString();
    	}
        this.socket = socket;
        this.string = string;  
        this.handle = new Handle();
        sentToHandle();
    }

    private void sentToHandle() {
        int i = 0;
        while (!temp.equals("")) {
            part[i]= check();
            i++;
        }
        handle.infoHandle(part, i, socket);
    }

    private String check () {
        totleSize = temp.length();
        if (totleSize != 0) {
            if(!temp.contains("/")){
                String tempS= temp;
                temp = "";
                return tempS;
            }
            size = temp.indexOf("/");
            s = temp.substring(0,size);
            temp = temp.substring(size+1,totleSize);
        }
        return s;
    }
}
