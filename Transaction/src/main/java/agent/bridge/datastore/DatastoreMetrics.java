package agent.bridge.datastore;

import java.sql.Connection;

/**
 * packageName    : agent.bridge.datastore
 * fileName       : DatastoreMetrics
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
public class DatastoreMetrics {

    public static final String DATABASE_GET_CONNECTION = "Datastore/getConnection";
    public static final String DATABASE_ERRORS_ALL = "DataStoreErrors/all";

    /*
    *
    * @param connection Connection used to run this query
    * @param sql The raw SQL string used in this query
    * @param params The parameters provided with the query (or null if no parameters are required)
    * */
    public static void noticeSql(Connection connection, String sql, Object[] params) {
        // No-op
    }
}
