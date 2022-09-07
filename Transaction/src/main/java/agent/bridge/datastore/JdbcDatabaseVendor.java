package agent.bridge.datastore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

/**
 * packageName    : agent.bridge.datastore
 * fileName       : JdbcDatabaseVendor
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
public abstract class JdbcDatabaseVendor implements DatabaseVendor{
    protected final String name;
    protected String type;
    protected boolean explainPlanSupported;

    public JdbcDatabaseVendor(String name, String type, boolean explainPlanSupported) {
        this.name = name;
        this.type = type;
        this.explainPlanSupported = explainPlanSupported;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isExplainPlanSupported() {
        return explainPlanSupported;
    }

    @Override
    public String getExplainPlanSql(String sql) throws SQLException {
        if (!isExplainPlanSupported()) {
            throw new SQLException("Unable to run explain plans for " + getName() + " databases");
        }
        return "EXPLAIN " + sql;
    }

    @Override
    public Collection<Collection<Object>> parseExplainPlanResultSet(int columnCount, ResultSet rs, RecordSql recordSql) throws SQLException {
        Collection<Collection<Object>> explains = new LinkedList<>();
        while (rs.next()) {
            Collection<Object> values = new LinkedList<>();
            for (int i = 1; i <= columnCount; i++) {
                Object obj = rs.getObject(i);
                values.add(obj == null ? "" : obj.toString());
            }
            explains.add(values);
        }
        return explains;
    }

    @Override
    public String getExplainPlanFormat() {
        return "text";
    }

    public abstract DatastoreVendor getDatastoreVendor();
}
