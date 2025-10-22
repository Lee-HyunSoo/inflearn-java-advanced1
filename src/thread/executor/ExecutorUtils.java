package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

import static util.MyLogger.log;

public abstract class ExecutorUtils {

    public static void printState(ExecutorService executorService) {
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            int pool = poolExecutor.getPoolSize(); // 풀에 스레드가 몇개인지
            int active = poolExecutor.getActiveCount(); // 동작 중인 스레드가 몇개인지
            int queuedTask = poolExecutor.getQueue().size();// 작업이 몇개 실행 중인지
            long completedTask = poolExecutor.getCompletedTaskCount();// 완료된 작업이 몇개인지
            log("[pool=" + pool + ", active=" + active + ", queuedTasks=" + queuedTask + ", completedTask=" + completedTask + "]");
        } else {
            log(executorService);
        }
    }

    public static void printState(ExecutorService executorService, String taskName) {
        if (executorService instanceof ThreadPoolExecutor poolExecutor) {
            int pool = poolExecutor.getPoolSize(); // 풀에 스레드가 몇개인지
            int active = poolExecutor.getActiveCount(); // 동작 중인 스레드가 몇개인지
            int queuedTask = poolExecutor.getQueue().size();// 작업이 몇개 실행 중인지
            long completedTask = poolExecutor.getCompletedTaskCount();// 완료된 작업이 몇개인지
            log(taskName + " -> [pool=" + pool + ", active=" + active + ", queuedTasks=" + queuedTask + ", completedTask=" + completedTask + "]");
        } else {
            log(executorService);
        }
    }
}
