package work.theadpool.demo.Task;

import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskFactory {

    private static final AtomicInteger seq = new AtomicInteger(0);

    /**
     * 使用自增id创建一个id不唯一的task
     * @return
     */
    public static CustomedTask createTask() {
        return new CustomedTask(seq.incrementAndGet());
    }

    /**
     * 创建一个task处理tcp请求
     * @return
     */
    public static TcpMessageTask createTcpTask(Socket socket) {
        TcpMessageTask task = new TcpMessageTask(socket);
        return task;
    }
}
