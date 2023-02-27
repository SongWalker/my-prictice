package work.theadpool.demo;

import java.io.*;
import java.net.Socket;

/**
 * tcp客户端
 * 测试通过TCP发送任务到服务端
 */
public class TcpClient {

    public static String serverIp = "127.0.0.1";

    public static int serverPort = 8090;

    //指定要发送的一些简单消息
    public static String[] questions = {"你好", "今天天气不错", "你的名字", "今天星期几"};

    public static void main(String[] args) {
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        Socket socket = null;
        try {
            //发送一系列消息给服务端
            for (int i = 0; i < questions.length; i++) {
                // 和服务器创建连接
                socket = new Socket(serverIp, serverPort);
                // 要发送给服务器的信息
                String msg = questions[i];
                os = socket.getOutputStream();
                pw = new PrintWriter(os);
                System.out.println("提问：" + msg);
                pw.write(msg);
                pw.flush();
                socket.shutdownOutput();
                // 从服务器接收的信息
                is = socket.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                String info = null;
                while ((info = br.readLine()) != null) {
                    System.out.println("服务器返回信息：" + info);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
                if (is != null) is.close();
                if (os != null) os.close();
                if (pw != null) pw.close();
                if (socket != null) socket.close();
            } catch (Exception e) {
            }
        }
    }
}
