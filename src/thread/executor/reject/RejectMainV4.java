package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static util.MyLogger.log;

public class RejectMainV4 {

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(
                1,
                1,
                0,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(), new MyRejectedExecutionHandler());

        executor.submit(new RunnableTask("task1"));
        executor.submit(new RunnableTask("task2"));
        executor.submit(new RunnableTask("task3"));
        executor.close();

        // 예외 정책 4. 사용자 정의 정책
        // 스프링을 쓰면 거절 된 작업을 모니터링 툴에 넘겨 그래프로 실시간으로 확인해 볼 수도 있다.
    }

    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {

        static AtomicInteger count = new AtomicInteger(0);

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // CAS 연산
            int i = count.incrementAndGet();
            log("[경고] 거절 된 누적 작업 수: " + i);
        }
    }
}
