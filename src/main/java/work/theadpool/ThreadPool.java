package work.theadpool;

import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程池
 * 通过poolSize设置线程池大小
 * perform方法收到提交的任务时，线程池未满则使用Worker创建一个新线程并执行该任务
 * 当线程池已达到poolSize，则不再创建新线程，新的任务添加至等待队列workerQueue
 * Worker的run方法会轮询获取等待队列的任务来执行
 */
public class ThreadPool {

    //默认等待队列最大长度，可适当调大
    private int awaitPoolSize = 100;

    //等待队列
    private final LinkedBlockingQueue<Runnable> workerQueue;

    //线程池
    private final HashSet<Worker> workers = new HashSet<Worker>();

    //正在等待的task数量
    private final AtomicInteger awaitCount = new AtomicInteger(0);

    //线程池正在工作的线程数量
    private final AtomicInteger workingCount = new AtomicInteger(0);

    private final ReentrantLock mainLock = new ReentrantLock();

    //线程池大小
    private volatile int poolSize;

    //是否关闭线程池
    private volatile boolean shutdown = false;

    public ThreadPool(int initialSize) {
        this.poolSize = initialSize;
        workerQueue = new LinkedBlockingQueue<Runnable>(awaitPoolSize);
    }

    public ThreadPool(int initialSize, int awaitPoolSize) {
        this.poolSize = initialSize;
        this.awaitPoolSize = awaitPoolSize;
        workerQueue = new LinkedBlockingQueue<Runnable>(awaitPoolSize);
    }

    /**
     * 线程池大小
     * @return
     */
    public int getSize(){
        return this.poolSize;
    }

    /**
     * 空闲的线程数
     * @return
     */
    public int getAvailable() {
        return poolSize - workingCount.intValue();
    }

    public void resize(int newSize) {
        if(newSize > poolSize) {
            this.poolSize = newSize;
        }
    }

    /**
     * 销毁线程池
     * 执行改方法后，等待队列拒绝接受新的任务
     * 等待所有任务执行结束后关闭线程
     */
    public void destroyPool() {
        this.shutdown = true;
        while (workerQueue.size() == 0 && workingCount.intValue() == 0) {
            for(Worker worker:workers) {
                worker.thread.interrupt();
            }
        }
    }

    /**
     * 接受一个任务并处理
     * @param task
     * @return
     */
    public boolean perform(Runnable task) {
        //当线程池关闭时，则不再接受新的task
        if(shutdown) {
            return false;
        }
        mainLock.lock();
        try {
            //线程池数量未满则创建新线程
            if(workers.size() < poolSize) {
                Worker worker = new Worker(task);
                Thread t = worker.thread;
                t.start();
                workers.add(worker);
            }
            else {
                //超过等待队列则放弃任务
                if(awaitCount.intValue() > awaitPoolSize) {
                    System.out.println("等待队列已满，放弃任务");
                    return false;
                }
                else {
                    //加入等待队列尾部
                    workerQueue.offer(task);
                    awaitCount.incrementAndGet();
                }
            }
        }
        finally {
            mainLock.unlock();
        }
        return true;
    }

    public final class Worker implements Runnable {

        private Thread thread;

        private Runnable task;

        public Worker(Runnable runnable) {
            thread = new Thread(this);
            task = runnable;
        }

        public void run() {
            while (true) {
                if (task != null || (task = getTask()) != null) {
                    workingCount.addAndGet(1);
                    try {
                        task.run();
                        task = null;
                    }
                    finally {
                        workingCount.addAndGet(-1);
                    }
                } else {
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    /**
     * //从等待队列中取新的task交由worker执行
     * @return
     */
    private Runnable getTask() {
        try {
            Runnable task = workerQueue.take();
            return task;
        }
        catch (InterruptedException e) {
        }
        finally {
            awaitCount.addAndGet(-1);
        }
        return null;
    }
}
