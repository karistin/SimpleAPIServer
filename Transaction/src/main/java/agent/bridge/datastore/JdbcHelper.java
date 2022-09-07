package agent.bridge.datastore;

import java.util.regex.Pattern;

/**
 * packageName    : agent.bridge.datastore
 * fileName       : JdbcHelper
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
public class JdbcHelper {

    private static final Pattern VENDOR_PATTERN = Pattern.compile("jdbc:([^:]*).*");

}
