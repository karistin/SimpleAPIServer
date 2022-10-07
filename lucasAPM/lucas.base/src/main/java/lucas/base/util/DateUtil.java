package lucas.base.util;

/**
 * packageName    : lucas.base.util
 * fileName       : DateUtil
 * author         : lucas
 * date           : 2022-10-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-07        lucas       최초 생성
 */
public class DateUtil {


    public static final DateTimeHelper helper = DateTimeHelper.getDefault();
    public static String datetime(long time) {
        return helper.datetime(time);
    }
}
