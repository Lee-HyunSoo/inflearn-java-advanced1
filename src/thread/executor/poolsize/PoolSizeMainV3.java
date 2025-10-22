package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class PoolSizeMainV3 {

    public static void main(String[] args) {
//        ExecutorService es = Executors.newCachedThreadPool();
        // 위 코드와 동일한 코드
//        ExecutorService es = new ThreadPoolExecutor(0,
//                Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
//                new SynchronousQueue<Runnable>());

        ExecutorService es = new ThreadPoolExecutor(0,
                Integer.MAX_VALUE, 3, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        log("pool 생성");
        printState(es);

        for (int i = 1; i <= 4; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es, taskName);
        }

        sleep(3000);
        log("== 작업 수행 완료 ==");
        printState(es);

        sleep(3000);
        log("== maximumPoolSize 대기 시간 초과 ==");
        printState(es);

        es.close();
        log("== shutdown 완료 ==");

        // newCachedThreadPool()
        // 기본 스레드를 사용하지 않고, 60초 생존 주기를 가진 초과 스레드만 사용
        // 초과 스레드의 수는 제한 없음
        // 큐에 작업을 저장하지 않음 (SynchronousQueue) -> 요청이 오면 스레드가 직접받아 바로 처리
        // 모든 요청이 대기하지 않고 스레드가 바로 처리

        // SynchronousQueue는 아주 특별한 블로킹 큐
        // 내부 저장공간이 없고, 생산자의 작업을 소비자 스레드에 직접 전달
        // 즉, 저장 공간 크기가 0인 상태에서
        // 생산자 스레드는 큐에 작업을 전달하고,
        // 소비자 스레드가 큐에서 작업을 꺼낼때까지 대기한다.
        // 이름 그대로 생산자와 소비자를 동기화하는 큐
        // 중간에 버퍼를 두지않는 스레드간 직거래라 볼 수 있다.

        // newCachedThreadPool()의 문제점
        // 갑자기 사용자가 폭증하는 경우, CPU와 메모리 사용량이 순간 급격히 올라 시스템이 느려질 수 있다.
        // 즉, 스레드가 무한히 생성될 수 있기 때문에,
        // 서버 스펙에 비해 스레드가 너무 많이생겨 시스템이 멈출 수도 있다.
    }
}
