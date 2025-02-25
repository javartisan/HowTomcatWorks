package org.how.tomcat.works.ex01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author : Ares
 * @version : 1.0
 * @createTime : Aug 21, 2012 9:51:37 PM
 * @description :
 * <p>
 * HTTP Response = Status-Line (( general-header | response-header |
 * entity-header ) CRLF) CRLF [ message-body ] Status-Line = HTTP-Version SP
 * Status-Code SP Reason-Phrase CRLF
 */
public class Response {
    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try {
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists()) {
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1) {
                    output.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
                // file not found
                String body = "<h1>File Not Found</h1>" + Thread.currentThread().getName();
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n"
                        + "Content-Type: text/html\r\n"
                        + "Content-Length: " + body.length() + "\r\n" + "\r\n"
                        + body;
                output.write(errorMessage.getBytes());
                System.err.println(Thread.currentThread().getName());
                Thread.sleep(20000);
            }
        } catch (Exception e) {
            // thrown if cannot instantiate a File object
            System.out.println(e.toString());
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }
}
