package work.theadpool;

import java.util.HashSet;

public abstract class Task<E,F> implements Runnable{

    private HashSet<TaskObserver> listeners = new HashSet<TaskObserver>();

    public Task(E param) {

    }

    /**
     * 获取task的id
     * @return
     */
    public int getId() {
        return this.hashCode();
    }

    public void run() {

    }

    /**
     * 添加一个监听器
     * @param taskObserver
     */
    public void addListener(TaskObserver<F> taskObserver) {
        listeners.add(taskObserver);
    }

    /**
     * 移除一个监听器
     * @param taskObserver
     */
    public void removeListener(TaskObserver<F> taskObserver) {
        listeners.remove(taskObserver);
    }

    /**
     * 向所有注册的监听器报告task执行的进度
     * @param progress
     */
    public void notifyAll(F progress) {
        for (TaskObserver observer:listeners) {
            observer.report(progress);
        }
    }

}
