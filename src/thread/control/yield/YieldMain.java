package thread.control.yield;

import thread.start.HelloRunnable;

import static util.ThreadUtils.sleep;

public class YieldMain {

    static final int THREAD_COUNT = 1000;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(new MyRunnable());
            thread.start();
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " - " + i);
                // 1. empty
//                 sleep(1); // 2. sleep
                 Thread.yield(); // 3. yield
                 /*
                 RUNNABLE 상태는 실행 중(Running) / 스케줄링 큐에서 대기 중(Ready) 두가지 상태가 있다.
                 -> 자바는 두 상태를 구별할 수 없다.

                 sleep()은 상태를 TIMED_WAITING 상태로 바꾼다.
                 yield()는 상태를 RUNNABLE로 유지한다. (Running -> Ready -> Running ...)
                 -> 항상 양보하는 것은 아니고, 운영체제에 힌트를 주는 것.
                 -> 운영체제의 판단으로 넌 지금 기다릴 필요가 없는데? 하면 계속 스레드를 사용하게 된다.
                 -> 때문에 Ready가 된다고 무조건 다른 스레드에 양보하는 것은 아니고, 다시 자기가 쓸 수도 있다.
                 */
            }
        }
    }
}
