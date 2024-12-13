package ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 20000);
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
        pw.println("Client Send: Hello World");

        BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
        );

        String line = br.readLine();
        System.out.println("1. read: " + line);
    }
}
