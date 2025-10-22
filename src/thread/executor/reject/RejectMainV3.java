package thread.executor.reject;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectMainV3 {

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(
                1,
                1,
                0,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());

        executor.submit(new RunnableTask("task1"));
        executor.submit(new RunnableTask("task2"));
        executor.submit(new RunnableTask("task3"));
        executor.submit(new RunnableTask("task4"));
        executor.close();

        // 예외 정책 3. CallerRunsPolicy
        // 요청이 초과될 시 해당 요청을 호출 스레드에 위임한다.
        // 이 경우 main이 생산자고, 스레드 풀의 스레드가 소비자인데,
        // 들어온 작업을 main에 넘겨 작업 처리를 하게 한다.

//        15:20:54.476 [pool-1-thread-1] task1 시작
//        15:20:54.476 [     main] task2 시작
//        15:20:55.488 [pool-1-thread-1] task1 완료
//        15:20:55.488 [     main] task2 완료
//        15:20:55.489 [     main] task3 시작
//        15:20:56.500 [     main] task3 완료
//        15:20:56.500 [pool-1-thread-1] task4 시작
//        15:20:57.503 [pool-1-thread-1] task4 완료

        // 이 정책의 목적은,
        // 생산자 스레드가 소비자 대신 일을 수행하기 때문에,
        // 작업의 생산 자체가 느려진다는 점이다.
        // 덕분에 생산 속도가 너무 빠르다면 생산 속도가 좀 느려지게 조절할 수 있다.
    }
}
