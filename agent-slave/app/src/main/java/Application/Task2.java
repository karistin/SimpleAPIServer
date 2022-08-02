package Application;


/**
 * packageName    : Application
 * fileName       : Task2
 * author         : lucas
 * date           : 2022-08-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-02        lucas       최초 생성
 */
public class Task2 {
    public static void tasking2(){
        int sum =0;
        for (int i = 0; i < 1000; ++i) {
            for (int j = 0; j < 10000; ++j) {
                sum += j;
            }
        }
        Working.working();
    }
}
