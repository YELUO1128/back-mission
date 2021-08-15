import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.*;
public class sdClient {
    public static void main(String[] args) throws IOException {
//        InetAddress iA = InetAddress.getByName("192.168.2.103");
//        Socket s = new Socket(iA,10086);
        Socket s = new Socket("192.168.2.103",10000);

        //输出流
        OutputStream os = s.getOutputStream();
        os.write("hello".getBytes());

        s.close();
    }

}


