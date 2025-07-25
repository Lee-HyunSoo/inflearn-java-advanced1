package thread.sync.test;

import static util.MyLogger.log;

public class SyncTest2Main {
    public static void main(String[] args) throws InterruptedException {
        MyCounter myCounter = new MyCounter();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                myCounter.count();
            }
        };
        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        thread1.start();
        thread2.start();
    }

    static class MyCounter {
        public void count() {
            // 스레드마다 스택이 생성되기 때문에 지역 변수는 완전 격리되어있음
            // 동시성 문제가 발생할 일이 없다.
            int localValue = 0;
            for (int i = 0; i < 1000; i++) {
                localValue = localValue + 1;
            }
            log("결과: " + localValue);
        }
    }
}
