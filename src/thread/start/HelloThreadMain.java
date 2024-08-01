package thread.start;

public class HelloThreadMain {

    public static void main(String[] args) {
        Thread thread = Thread.currentThread(); // 현재 실행하는 쓰레드를 반환하는 메서드
        System.out.println(thread.getName() + ": main() start");

        HelloThread helloThread = new HelloThread();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 전");
        helloThread.start();
        System.out.println(Thread.currentThread().getName() + ": start() 호출 후");

        System.out.println(thread.getName() + ": main() end");
    }
}
