import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

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
//        Timestamp ts = localDateTimeToTimeStamp(LocalDateTime.now());
//        System.out.println("ts : "+ts);
//        LocalDateTime ldt = timeStampToLocalDateTime(ts);
//        System.out.println("ldt : "+ldt);
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println("Local Date Time - " + localDateTime);
//        Timestamp timestamp = Timestamp.valueOf(localDateTime);
//        System.out.println("Timestamp from localdatetime is " + timestamp);
        Calendar cal = Calendar.getInstance();
//        Date date = System.currentTimeMillis();
        Date date = cal.getTime();
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println(dateString);
    }
}
