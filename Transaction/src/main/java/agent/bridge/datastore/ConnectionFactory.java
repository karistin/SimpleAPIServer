package agent.bridge.datastore;



import java.sql.Connection;
import java.sql.SQLException;

/**
 * packageName    : agentbridgedatastore
 * fileName       : ConnectionFactory
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
public interface ConnectionFactory {
    Connection getConnection() throws SQLException;

    DatabaseVendor getDatabaseVendor();
}
