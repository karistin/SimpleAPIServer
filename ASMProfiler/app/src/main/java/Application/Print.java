package Application;

/**
 * packageName    : Application
 * fileName       : Print
 * author         : lucas
 * date           : 2022-08-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-26        lucas       최초 생성
 */
public class Print {
    public static int PrintInt(int i, String j, boolean k) {
        System.out.println(i);
        return i;
    }

    public static void main(String[] args) {
        while(true){
            int a = PrintInt(10, "Hello", true);
            System.out.println("Done");
        }
    }
}
