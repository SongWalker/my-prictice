package work.theadpool;

/**
 * 监听器
 * 用于监听Task的执行状态
 * Task使用addListener方法注册监听器，在运行期间，通过调用report方法向已注册的监听器发送状态信息
 * @param <F>
 */
public interface TaskObserver<F> {
     void report(F progress);
}
