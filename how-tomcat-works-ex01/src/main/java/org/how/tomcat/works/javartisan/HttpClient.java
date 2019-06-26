package org.how.tomcat.works.javartisan;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Daxin
 */
public class HttpClient {


    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(InetAddress.getByName("127.0.0.1"), 8080));
        OutputStream out = socket.getOutputStream();
        String param = "{\"name\":\"daxin\"}";
        // http content-length必须有，否则服务器端无法解析
        // 查询参数在请求行的url后面
        String body = "POST /?count=10&uv=100 HTTP/1.1\r\n" +
                "Host: 127.0.0.1:8080\r\n" +
                "Content-Type: application/json\r\n" +
                "Content-Length: " + param.length() + "\r\n" +
                "Connection: keep-alive\r\n" + "\r\n"
                + param;
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
