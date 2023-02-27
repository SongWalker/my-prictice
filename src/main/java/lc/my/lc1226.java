package lc.my;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class lc1226 {

    //private Semaphore limit = new Semaphore(4);

    ReentrantLock[] forks = new ReentrantLock[5]; //初始化

    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        if(philosopher % 2 != 0) {
            forks[philosopher].lock();
            pickRightFork.run();
            forks[(philosopher + 1)% 5].lock();
            pickLeftFork.run();
            eat.run();
            putRightFork.run();
            forks[(philosopher + 1)% 5].unlock();
            putLeftFork.run();
            forks[philosopher].unlock();
        }
        else {
            forks[(philosopher + 1)% 5].lock();
            pickLeftFork.run();
            forks[philosopher].lock();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            forks[philosopher].unlock();
            putRightFork.run();
            forks[(philosopher + 1)% 5].unlock();
        }

    }
}
