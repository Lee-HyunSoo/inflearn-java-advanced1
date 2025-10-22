package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV1 {

    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        // 위 큐의 최대 사이즈 2
        // 기본 스레드 개수 2개
        // 스레드 2개 실행 중, 대기 큐에도 2개의 작업이 차버렸다? -> 긴급한 상황
        // 스레드를 maximumPoolSize 까지 하나씩 늘림 (초과 스레드)
        // 초과 스레드까지 만들었는데 더 작업이 들어온다? -> RejectException
        // 작업이 모두 완료되고, 초과 스레드가 keepAliveTime 동안 작업을 안한다? -> 풀에서 제거
        // keepAliveTime이 지나기전에 만약 작업을 할당 받으면, 시간은 리셋된다.
        ExecutorService es = new ThreadPoolExecutor(
                2,
                4,
                3000,
                TimeUnit.MILLISECONDS,
                workQueue);
        printState(es);

        es.execute(new RunnableTask("task1"));
        printState(es, "task1");

        es.execute(new RunnableTask("task2"));
        printState(es, "task2");

        es.execute(new RunnableTask("task3"));
        printState(es, "task3");

        es.execute(new RunnableTask("task4"));
        printState(es, "task4");

        es.execute(new RunnableTask("task5"));
        printState(es, "task5");

        es.execute(new RunnableTask("task6"));
        printState(es, "task6");

        try {
            es.execute(new RunnableTask("task7"));
            printState(es, "task7");
        } catch (RejectedExecutionException e) {
            log("task7 실행 거절 예외 발생: " + e);
        }

        sleep(3000);
        log("== 작업 수행 완료 ==");
        // [pool=4, active=0, queuedTasks=0, completedTask=6]
        printState(es);

        sleep(3000);
        log("== maximumPoolSize 대기시간 초과 ==");
        // [pool=2, active=0, queuedTasks=0, completedTask=6]
        printState(es);

        es.close();
        log("== shutdown 완료 ==");
        printState(es);
    }
}
