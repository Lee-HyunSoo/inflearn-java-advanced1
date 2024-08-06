package util;

import static util.MyLogger.log;

public abstract class ThreadUtils {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log("인터럽트 발생, " + e.getMessage());
            // 인터럽트 예외 발생 시 RuntimeException 으로 바꿔서 던짐
            throw new RuntimeException(e);
        }
    }
}
