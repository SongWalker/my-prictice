package work.theadpool.demo.Observer;

import work.theadpool.TaskObserver;

public class TcpServerObserver implements TaskObserver {

    /**
     * 将tcp请求处理过程中汇报的状态信息打印出来
     * 包括当前处理改请求的线程号
     * @param message
     */
    public void report(Object message) {
        System.out.println(String.format("[Thread %d] %s", Thread.currentThread().getId(), message));
    }
}
