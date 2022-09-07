package agent.bridge.datastore;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

/**
 * packageName    : agent.bridge.datastore
 * fileName       : JdbcDriverConnectionFactory
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
public class JdbcDriverConnectionFactory implements ConnectionFactory{

    private static final Properties EMPTY_PROPERTIES = new Properties();

//   WeakReference logic 빠른 GC를 요구 , 루트 셋의 참조의 참조
    private final WeakReference<DatabaseVendor> databaseVendor;
    private final WeakReference<Driver> driver;
    private final String url;
    private final Properties props;

    public JdbcDriverConnectionFactory(DatabaseVendor databaseVendor, Driver driver, String url, Properties props) {
        this.databaseVendor = new WeakReference<>(databaseVendor);
        this.driver = new WeakReference<>(driver);
        this.url = url;
        this.props = (props == null || props.isEmpty()) ? EMPTY_PROPERTIES : props;
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            Driver jdbcDriver = driver.get();
            if (jdbcDriver != null) {
                return jdbcDriver.connect(url, props);
            }
            throw new RuntimeException("JDBC Driver has been Garbage Collected");
        } catch (SQLException e) {
            logError();
            throw e;
        } catch (Exception e) {
            logError();
            throw new SQLException(e);
        }
    }

    @Override
    public DatabaseVendor getDatabaseVendor() {
        DatabaseVendor vendor = databaseVendor.get();
        if (vendor != null) {
            return vendor;
        }
        return UnknownDatabaseVendor.INSTANCE;
    }



    private void logError(){
//        get logger form agent
    }

}
