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

    private static String[] classFilters = {
            "java","jdk","javax","sun","com/sun","com/Agent","org/apache/maven","org/junit", "org/springframework", "org/springdoc","org/apache","org/w3c","$","org/slf4j","com/fasterxml","nonapi",
            "io/swagger","ch/qos","$$","Com.","com/googlecode/lanterna"
    };
    private static boolean Filtering(String name)
    {
//        String name ="Com/Agent/MyBCIMethod";
        for (String classFilter: classFilters)
        {
            if(classFilter.contains(classFilter))
                return false;
        }
        return true;
    }
    public static void main(String args[]) {
        System.out.println(Filtering("Com/Agent/MyBCIMethod"));
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
