package org.how.tomcat.works.javartisan;


import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class HttpService {

    public static void startHttpService(int port) throws Exception {
        ServerSocket server = new ServerSocket(port, 5, InetAddress.getByName("127.0.0.1"));
        String body = "<h1>Hello World !</h1><h5>Hello World !</h5>";
        // content length 是
        String msg = "HTTP/1.1 200\r\n"
                // 如上是状态行
                + "Content-Type: text/html\r\n"
                + "Content-Length: " + (body.length()) + "\r\n"
                // 如上是消息报头
                // 如下是http报文协议中约定的一行
                + "\r\n"
                //如下是响应正文
                + body;

        while (true) {
            Socket income = server.accept();
            print(income.getInputStream());
            income.getOutputStream().write(msg.getBytes());
            System.out.println("=========");
            // 使用完毕服务器端关闭连接，否则客户端一直等待
            income.close();
        }
    }

    public static void print(InputStream in) throws Exception {
        StringBuilder sb = new StringBuilder();
        byte[] data = new byte[1024];
        int i = in.read(data);
        if (i > 0) {
            sb.append(new String(data, 0, i));
            System.out.println(sb.toString());
        }

    }

    //error
    public static void print2(InputStream in) throws Exception {
        StringBuilder sb = new StringBuilder();
        byte[] data = new byte[1024];
        int i = in.read(data);
        while (i > 0) {
            sb.append(new String(data, 0, i));
            i = in.read(data);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        startHttpService(8080);
    }
}
