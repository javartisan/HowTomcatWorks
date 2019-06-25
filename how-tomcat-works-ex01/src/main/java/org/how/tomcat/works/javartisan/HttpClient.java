package org.how.tomcat.works.javartisan;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.UUID;

/**
 * @author Daxin
 */
public class HttpClient {


    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 8080));
        OutputStream out = socket.getOutputStream();
        String body = "GET / HTTP/1.1\r\n" +
                "Host: 127.0.0.1:8080\r\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36\r\n" +
                "Connection: keep-alive\r\n" + "\r\n"
                + "Hello " + UUID.randomUUID().toString() + "!";
        out.write(body.getBytes());
        InputStream in = socket.getInputStream();
        byte[] data = new byte[1024];
        int i = in.read(data);
        if (i > 0) {
            System.out.println(new String(data, 0, i));
        } else {
            System.out.println("i<0！");
        }

        socket.close();
    }
}
