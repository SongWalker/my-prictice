package work.theadpool.demo;

import work.theadpool.ThreadPool;
import work.theadpool.demo.Observer.TcpServerObserver;
import work.theadpool.demo.Task.TaskFactory;
import work.theadpool.demo.Task.TcpMessageTask;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * tcp服务端
 * 测试通过TCP接受客户端发送过来的任务，使用线程池执行
 */
public class TcpServer {

    public static int serverPort = 8090;

    // 服务端初始化线程池用于处理客户端请求
    // 线程池大小设置为4
    // 等待队列设置为1000
    public static ThreadPool threadPool;

    public static void main(String[] args) {
        try {
            threadPool = new ThreadPool(4, 1000);
            System.out.println("初始化线程池大小:4,等待队列长度:1000");
            // 创建服务端socket
            ServerSocket serverSocket = new ServerSocket(serverPort);
            TcpServerObserver observer = new TcpServerObserver();
            System.out.println("服务启动,端口号:" + serverPort);
            // 创建客户端socket
            Socket socket;
            //循环监听客户端的连接
            while(true){
                // 监听客户端
                socket = serverSocket.accept();
                System.out.println("收到客户端请求，地址为:" + socket.getRemoteSocketAddress().toString());
                //使用线程池处理客户端的请求
                //TcpMessageTask会根据客户端传来的消息，进行简单的对话
                TcpMessageTask task = TaskFactory.createTcpTask(socket);
                task.addListener(observer);
                threadPool.perform(task);
            }
        }
        catch (Exception e) {

        }
    }
}
