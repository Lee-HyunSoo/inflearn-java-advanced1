package thread.control;

import util.ThreadUtils;

public class CheckedExceptionMain {

    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

    static class CheckedRunnable implements Runnable {

        @Override
        public void run() {
            ThreadUtils.sleep(1000);
//            throw new Exception(); // 컴파일 에러
            throw new RuntimeException(); // 가능
        }
    }
}
