package ru.job4j.junior.io.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());


    public static void main(String[] args) {
        boolean serverActive = true;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (serverActive) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    String request;
                    String response = "";
                    while (!str.isEmpty()) {
                        if (str.contains("GET /?msg=")) {
                            request = parseRequest(str);
                            if (request.equals("Exit")) {
                                serverActive = false;
                                response = "Bye";
                            } else if (request.equals("Hello")) {
                                response = "Hello, dear friend!";
                            } else {
                                response = request;
                            }
                        }
                        System.out.println(str);
                        str = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write(response.getBytes());
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in server", e);
        }
    }

    private static String parseRequest(String str) {
        String req = str.replace("GET /?msg=", "");
        return req.substring(0, req.indexOf(" "));
    }
}
