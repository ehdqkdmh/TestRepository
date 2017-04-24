package ch18.exam25.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientExample1 {
    public static void main(String[] args) {
        Socket socket = null;
        
        try {
            //how1
            //socket = new Socket("192.168.3.35", 50001);
            
            //how
            socket = new Socket();
            socket.connect(new InetSocketAddress("192.168.3.51", 50001));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        if(!socket.isClosed()) {
            try { socket.close(); } catch (IOException ex) {}
        }
    }
}
