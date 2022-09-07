package agent.bridge.datastore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * packageName    : agentbridgedatastore
 * fileName       : DatabaseVendor
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
public interface DatabaseVendor {

    String getName();

    String getType();

    boolean isExplainPlanSupported();

    String getExplainPlanSql(String sql) throws SQLException;

    Collection<Collection<Object>> parseExplainPlanResultSet(int columnCount, ResultSet rs, RecordSql recordSql)
            throws SQLException;

    String getExplainPlanFormat();

//    return Vendor
    DatastoreVendor getDatastoreVendor();
}
