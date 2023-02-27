package work.theadpool.demo.Task;

import work.theadpool.Task;

/**
 * 自定义的任务
 */
public class CustomedTask extends Task {

    private int id;

    public CustomedTask(int param) {
        super(param);
        this.id = param;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void run() {
        //自定义线程的任务 打印线程号、任务id，执行信息
        notifyAll(String.format("[thread:%s] taskId:%d 任务开始", Thread.currentThread().getId(), getId()));
        notifyAll(String.format("[thread:%s] taskId:%d 执行中",  Thread.currentThread().getId(), getId()));
        notifyAll(String.format("[thread:%s] taskId:%d 结束",  Thread.currentThread().getId(), getId()));
    }
}
