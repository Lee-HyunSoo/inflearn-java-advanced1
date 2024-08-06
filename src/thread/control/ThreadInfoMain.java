package thread.control;

import thread.start.HelloRunnable;

import static util.MyLogger.log;

public class ThreadInfoMain {

    public static void main(String[] args) {
        // main 스레드
        Thread mainThread = Thread.currentThread();
        log("mainThread = " + mainThread);
        // 자바가 내부적으로 만듬, 절대 중복 x
        log("mainThread.threadId() = " + mainThread.threadId());
        // main, 중복될 수 있다.
        log("mainThread.getName() = " + mainThread.getName());
        // 우선순위는 기본이 5, 높을수록 많이 실행된다. (운영체제, 스케줄러한테 힌트를 줌)
        log("mainThread.getPriority() = " + mainThread.getPriority());
        log("mainThread.getThreadGroup() = " + mainThread.getThreadGroup());
        // RUNNABLE -> Thread 가 실행될 수 있는 상태
        log("mainThread.getState() = " + mainThread.getState());

        // my 스레드
        Thread myThread = new Thread(new HelloRunnable(), "myThread");
        log("mainThread = " + myThread);
        // 자바가 내부적으로 만듬, 절대 중복 x
        log("mainThread.threadId() = " + myThread.threadId());
        // myThread, 중복될 수 있다.
        log("mainThread.getName() = " + myThread.getName());
        // 우선순위는 기본이 5, 높을수록 많이 실행된다. (운영체제, 스케줄러한테 힌트를 줌)
        log("mainThread.getPriority() = " + myThread.getPriority());
        log("mainThread.getThreadGroup() = " + myThread.getThreadGroup());
        // NEW -> Thread 새로 생성, 아직 실행 안함
        log("mainThread.getState() = " + myThread.getState());
    }
}
