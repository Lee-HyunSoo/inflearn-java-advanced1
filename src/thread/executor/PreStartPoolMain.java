package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static thread.executor.ExecutorUtils.printState;

public class PreStartPoolMain {

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(1000);
        printState(es);
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) es;

        // 스레드 풀에 스레드를 미리 생성.
        // 원래는 작업이 들어오면 생성된다.
        poolExecutor.prestartAllCoreThreads();
        printState(es);
    }
}
