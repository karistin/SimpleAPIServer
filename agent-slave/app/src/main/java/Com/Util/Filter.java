package Com.Util;

/**
 * packageName    : agent
 * fileName       : Filter
 * author         : lucas
 * date           : 2022-07-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-20        lucas       최초 생성
 */
public class Filter {
//    private static String[] classFilters = {
//            "java","jdk","javax","sun","com/sun","Com/Agent","org/apache/maven","org/junit", "org/springframework", "org/springdoc","org/apache","org/w3c","$","org/slf4j","com/fasterxml","nonapi",
//            "io/swagger","ch/qos","$$","Com/Agent/MyBCIMethod","com/google/","com.googlecode"
//    };
    private static String[] classFilters = {
            "jdk","javax","sun","com/sun","Com/Agent","org/apache/maven","org/junit", "org/springdoc","org/apache","org/w3c","$","org/slf4j","com/fasterxml",
            "io/swagger","ch/qos","$$","Com/Agent/MyBCIMethod","com/google/","com.googlecode","org/springframework","java"
    };
//    private static String[] classFilters = {
//            "java","jdk","javax","sun","com/sun","Agent.","org/apache/maven","org/junit", "org/springframework", "org/springdoc","org/apache","org/w3c",
//            "nonapi/io","org/slf4j","ch/qos","com/fasterxml","org/xml","io/github","org/webjars","org/aopalliance","io/swagger","$"
//    };
//    make white list

    static public boolean classFilering(String className){
        for (String classFilter : classFilters) {
            if (className.toLowerCase().contains(classFilter)) {
                return false;
            }
        }
        return true;
    }
}
