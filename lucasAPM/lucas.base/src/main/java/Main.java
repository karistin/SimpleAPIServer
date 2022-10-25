
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

        SqlQuery sqlQuery = SqlQuery.getInstance();
        sqlQuery.connection("transactionDB", "root", "1234");
//        sqlQuery.excuteCol("start_time", "transaction");
//        sqlQuery.insertRow(TranscationId.generateID(), "127.0.0.1",System.currentTimeMillis());
//        sqlQuery.insertRow(TranscationId.generateID(),"127.0.0.1", Timestamp.valueOf(LocalDateTime.now()),System.currentTimeMillis());

//        java 8 / 9
        System.out.println(ClassLoader.getSystemResource("java/lang/Class.class"));
        System.out.println(3&3);
    }
}
