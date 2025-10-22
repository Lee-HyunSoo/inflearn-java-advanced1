package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class PoolSizeMainV4 {

//    static final int TASK_SIZE = 1100; // 1. 일반적인 상황
//    static final int TASK_SIZE = 1200; // 2. 긴급한 상황
    static final int TASK_SIZE = 1201; // 3. 요청을 거절해야하는 상황

    public static void main(String[] args) {
        ExecutorService es = new ThreadPoolExecutor(
                100,
                200,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000));
        printState(es);

        long startMs = System.currentTimeMillis();
        for (int i = 1; i <= TASK_SIZE; i++) {
            String taskName = "task" + i;
            try {
                es.execute(new RunnableTask(taskName));
                printState(es, taskName);
            } catch (RejectedExecutionException e) {
                log(taskName + " -> " + e);
            }
        }
        es.close();
        long endMs = System.currentTimeMillis();
        log("time: " + (endMs - startMs));

        // 1. 일반적인 상황인 경우
        // 요청이 1100개가 들어오면 스레드 100개 작업 수행, 큐에 1000개 작업이 들어가며
        // 1100개의 작업을 100개의 스레드가 1초마다 처리하므로 약 11초 걸린다.

        // 2. 긴급 상황인 경우
        // 요청이 1200개가 들어오면 스레드 100개 작업 수행, 큐에 1000개의 작업이 들어있을 때
        // 추가로 작업이 들어오면 100개의 스레드를 더 만들게 되고, 스레드풀에 200개의 스레드가 생성된다.
        // 때문에 200개의 스레드가 1200개의 작업을 수행하는 상황이 만들어지고, 약 6초 걸린다.

        // 3. 요청을 거절해야하는 상황
        // 요청이 1200개가 들어오면 스레드 100개 작업 수행, 큐에 1000개의 작업이 들어있을 때
        // 추가로 작업이 들어오면 100개의 스레드를 더 만들게 되고, 스레드풀에 200개의 스레드가 생성된다.
        // 때문에 200개의 스레드가 1200개의 작업을 수행하는 상황이 만들어지고, 약 6초 걸린다.
        // 다만, 초과된 1개의 스레드의 경우 거절되기 때문에 RejectExecutionException 에외가 터진다.

        // 주의!
        // new ArrayBlockingQueue<>(1000);
        // 위처럼 큐의 사이즈를 설정해주지않고 new LinkedBlockingQueue<>(); 이렇게 해버리면,
        // 큐의 사이즈가 무한히 늘어나기 때문에 긴급상황이라 감지하지 못하고, 추가로 스레드를 만들지 않는다.
        // 즉, 기본 스레드 100개를 가지고 무한한 작업을 처리해야하는 상황이 발생한다.
    }
}
