package lucas.base.util;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * packageName    : lucas.base.util
 * fileName       : DateTimehelper
 * author         : lucas
 * date           : 2022-10-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-07        lucas       최초 생성
 */
public class DateTimeHelper {

    private static class Day {
        public Day(String date, String day, long time) {
            this.date = date;
            this.wday = day;
            this.time = time;
        }

        public final String date;
        public final String wday;
        public final long time;
    }


    private DateTimeHelper(TimeZone zone) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.setTimeZone(zone);
//            BASE_TIME = sdf.parse("20000101").getTime();
//            open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DateTimeHelper getDefault() {
        return null;
    }

    public String datetime(long time) {
        return null;
    }
}
