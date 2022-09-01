package Application;

/**
 * packageName    : Application
 * fileName       : CustomThreadPoolTest
 * author         : lucas
 * date           : 2022-09-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-01        lucas       최초 생성
 * https://roytuts.com/custom-thread-pool-in-java/
 */

public class CustomThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " is executing task.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        CustomThreadPool threadPool = new CustomThreadPool(2);

        threadPool.execute(r);
        threadPool.execute(r);
        threadPool.shutdown();

        // threadPool.execute(r);
    }

}
