package work.theadpool.demo;

import work.theadpool.Task;
import work.theadpool.ThreadPool;
import work.theadpool.demo.Observer.CustomedObserver;
import work.theadpool.demo.Task.TaskFactory;

/**
 * 线程池测试
 * 测试将多个任务添加到线程池中执行
 *
 * 1. 程序算法核心代码：
 * work.threadpool 下
 *
 * 2. 演示代码：
 * work.threadpool.demo 下
 * 客户端代码：TcpClient
 * 功能：往服务端发送一系列消息，打印收到的应答信息
 *
 * 服务端代码：TcpServer
 * 功能：实现一个简单的自动应答机器人，收到客户端的消息后提交到线程池执行，得到结果后返回给客户端
 *
 * 需先启动 TcpServer ，再启动 TcpClient
 */
public class ThreadTest {

    public static ThreadPool threadPool = new ThreadPool(4);
    public static CustomedObserver observer = new CustomedObserver();

    public static void main(String[] args) {
        int maxId = 10000; //最大循环次数
        while (true) {
            Task task = TaskFactory.createTask();
            //添加自定义的监听器，打印任务执行过程中汇报的内容
            task.addListener(observer);
            if(task.getId() > maxId) {
                break;
            }
            boolean res = threadPool.perform(task);
            if(res) {
                System.out.println(String.format("task:%d 执行成功", task.getId()));
            }
            else {
                System.out.println(String.format("task:%d 等待队列已满，任务放弃", task.getId()));
            }
        }
        threadPool.destroyPool();
    }
}
