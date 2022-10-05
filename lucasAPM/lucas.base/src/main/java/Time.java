import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : Time
 * author         : lucas
 * date           : 2022-10-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-05        lucas       최초 생성
 */
public class Time {
    public static Timestamp localDateTimeToTimeStamp(LocalDateTime ldt)
    {
        return Timestamp.valueOf(ldt);
    }
    public static LocalDateTime timeStampToLocalDateTime(Timestamp ts)
    {
        return ts.toLocalDateTime();
    }

    public static void main(String[] args) {
        Timestamp ts = localDateTimeToTimeStamp(LocalDateTime.now());
        System.out.println("ts : "+ts);
        LocalDateTime ldt = timeStampToLocalDateTime(ts);
        System.out.println("ldt : "+ldt);


    }
}
