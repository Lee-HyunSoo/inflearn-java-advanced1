package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class PoolSizeMainV2 {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        // 위 코드와 동일한 코드
//        ExecutorService es = new ThreadPoolExecutor(2,
//                2, 0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>());

        log("pool 생성");
        printState(es);

        for (int i = 1; i <= 6; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es, taskName);
        }
        es.close();
        log("== shutdown 완료 ==");

        // newFixedThreadPool()의 문제점
        // 스레드수는 고정인데, 내부 큐의 사이즈는 무한으로 늘어날 수 있기 때문에,
        // 사용자의 요청이 갑자기 폭증해버린다면 처리량이 요청량보다 훨씬 떨어지게 된다.

        // 중요한 점은, 고정 스레드 풀이기 때문에 CPU, 메모리만을 확인해선 알 수가 없다.
        // 실행되는 스레드 수는 고정되어있기 때문에,
        // 사용자가 늘어나도 CPU, 메모리 사용량이 확 늘어나지 않기 때문이다.
        // 이런 경우에는 큐의 크기를 로그를 찍던 해서 확인해봐야한다.
    }
}
