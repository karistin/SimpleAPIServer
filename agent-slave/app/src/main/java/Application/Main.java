package Application;

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
    public static List<Integer> arry  = new ArrayList<>();
    public static int an;
    public static void main(String[] args) {
        an = 4;
        arry.add(an);
        an = 5;
        arry.add(an);
        System.out.println(arry);
    }

}
