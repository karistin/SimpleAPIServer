package application;

import static java.lang.Thread.sleep;

/**
 * packageName    : application
 * fileName       : Main
 * author         : lucas
 * date           : 2022-07-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-22        lucas       최초 생성
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        while (true) {
            main.sayHi();
            Thread.sleep(100);
        }
    }

    private void sayHi() throws InterruptedException {
        sleep();
        System.out.println("hi, ksj"+System.currentTimeMillis());
    }

    private void sleep() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 200));
    }

}
