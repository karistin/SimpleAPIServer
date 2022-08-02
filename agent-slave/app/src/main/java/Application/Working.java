package Application;

/**
 * packageName    : Application
 * fileName       : Working
 * author         : lucas
 * date           : 2022-08-02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-02        lucas       최초 생성
 */
public class Working {
    public static void working()
    {
        int sum =0;
        for (int i = 0; i < 1000; ++i) {
            for (int j = 0; j < 10000; ++j) {
                sum += j;
            }
        }
    }
}
