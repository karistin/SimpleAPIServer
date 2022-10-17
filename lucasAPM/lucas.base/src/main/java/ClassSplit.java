import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : ClassSplit
 * author         : lucas
 * date           : 2022-10-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-17        lucas       최초 생성
 */
public class ClassSplit {
    public static void main(String[] args) {
//        String className = "com/lucas/app/common/filter/doFilter";
//        String[] sp = className.split("/");
//        System.out.println(sp[sp.length-1]);
        List<String> stringList = new ArrayList<>();
        stringList.add( "0");
        stringList.add( "1");
        stringList.add("2");

        System.out.println(stringList.get(0));

        System.out.println(stringList.size());
    }
}
