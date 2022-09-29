import lucas.base.tranacation.TranscationId;

import java.util.Date;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : Main
 * author         : lucas
 * date           : 2022-09-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-29        lucas       최초 생성
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(TranscationId.generateID());
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date(System.currentTimeMillis()));
    }
}
