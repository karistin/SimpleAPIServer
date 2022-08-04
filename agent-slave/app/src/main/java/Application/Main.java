package Application;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;



/**
 * packageName    : Application
 * fileName       : Main
 * author         : lucas
 * date           : 2022-07-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-25        lucas       최초 생성
 */
public class Main {
    static class AAA {
        void aaa() {
            bbb();
        }

        void bbb() {
            ccc();
            sleep(1000);
            ddd();
            sleep(1000);
            eee();
            sleep(1000);
            fff();
        }

        void ccc() {
            try {
                Exception e = new Exception();
                e.initCause(new IOException("No space memory"));
                throw e;
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        void ddd() {
            // get stack trace and print
            StackTraceElement[] stacks = (new Throwable()).getStackTrace();
            for (StackTraceElement element : stacks) {
                System.out.println(element);
            }
        }

        void eee() {
            // print stack trace
            (new Throwable()).printStackTrace();
        }

        void fff() {
            try {
                Exception e = new Exception();
                e.initCause(new IOException("No space memory"));
                throw e;
            } catch(Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);

                pw.append("+++Start printing trace:\n");
                e.printStackTrace(pw);
                pw.append("---Finish printing trace");
                System.out.println(sw.toString());
            }
        }

        void sleep(long ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        AAA a = new AAA();
        a.aaa();
    }
//    public static void main(String[] args) {
//        while(true){
//            Task1.tasking1();
//            Task2.tasking2();
//            Task3.tasking3();
//            Task4.tasking4();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }

}
