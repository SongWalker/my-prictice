package work.theadpool.demo.Observer;

import work.theadpool.TaskObserver;

/**
 * 自定义的监听器
 */
public class CustomedObserver implements TaskObserver {

    public void report(Object progress) {
        //直接打印任务报告的状态
        System.out.println(progress);
    }
}
