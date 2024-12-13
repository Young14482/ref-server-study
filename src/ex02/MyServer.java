package ex02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    /*
      buyer:1 -> 사과
      seller:1 -> 당근
      buyer:2 -> 라면
      seller:2 -> 우유

      else >> 404
     */
    public static void main(String[] args) {
        try {
            // 1. 리스너 생성 및 대기
            ServerSocket serverSocket = new ServerSocket(20000);
            Socket socket = serverSocket.accept();

            // 2. 반이중 연결 (요청에 대한 응답이 존재) | 버퍼 달기
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            // 3. 요청 받고 응답하기 >> 원래는 잠깐의 텀을 두어 점유권을 가져올수 있도록 해야함
            while (true) {
                String line = br.readLine();
                String response = parser(line);
                pw.println(response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String parser(String line) {
        String[] split = line.split(":");
        String header = split[0];
        String body = split[1];
        String response = "404";

        if (header.equals("buyer")) {
            if (body.equals("1")) {
                response = "사과";
            } else if (body.equals("2")) {
                response = "라면";
            }
        }

        if (header.equals("seller")) {
            if (body.equals("1")) {
                response = "당근";
            } else if (body.equals("2")) {
                response = "우유";
            }
        }
        return response;
    }
}
