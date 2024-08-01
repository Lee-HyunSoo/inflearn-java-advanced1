package thread.start;

public class HelloRunnableMain {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ": main() start");

        HelloRunnable runnable = new HelloRunnable();
        Thread thread = new Thread(runnable); // 쓰레드 안에 작업을 넣을 수 있다. -> 작업과 쓰레드를 분리
        thread.start();

        System.out.println(Thread.currentThread().getName() + ": main() end");
    }
}
