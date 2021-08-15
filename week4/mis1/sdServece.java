import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.*;
public class sdServece {
    public static void main(String[] args) throws IOException {
        ServerSocket ss =  new ServerSocket(10000);

        //accept()侦听连接并接收
        Socket s = ss.accept();

        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys);
        String data = new String (bys,0,len);
        System.out.println("数据："+data);

        ss.close();
    }
}
