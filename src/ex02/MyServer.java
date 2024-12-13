package ex02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    public static void main(String[] args) {
        System.out.println("0. Server started");
        try {
            ServerSocket serverSocket = new ServerSocket(20000); // 1. 서버소켓 생성

            Socket socket = serverSocket.accept(); // 2. 리스닝
            System.out.println("1. oh connect");

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            String line = br.readLine(); // 버퍼에 있는 메시지를 \n 까지 읽음
            System.out.println("2. read: " + line);

            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println("Server Send: peanut");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
