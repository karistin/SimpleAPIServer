package agent.util;

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
    private static String[] classFilters = {
            "java","jdk","javax","sun","com/sun","agent.","org/apache/maven","org/junit", "org/springframework", "org/springdoc","io/swagger","org/apache","org/w3c",
            "nonapi/io","org/slf4j","ch/qos","com/fasterxml","org/xml","io/github","org/webjars","org/aopalliance"
    };

//    private static String[] classFilters = {
//            "java","jdk","javax","sun","com/sun","agent.","org/springframework", "org/springdoc","ch/qos","com/fasterxml","org/xml
//    };
    static public boolean classFilering(String className){
        for (String classFilter : classFilters) {
            if (className.toLowerCase().contains(classFilter)) {
                return false;
            }
        }
        return true;
    }
}
