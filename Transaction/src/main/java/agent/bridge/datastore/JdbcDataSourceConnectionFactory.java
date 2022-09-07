package agent.bridge.datastore;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * packageName    : agent.bridge.datastore
 * fileName       : JdbcDataSourceConnectionFactory
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 *  Vendor , dataSource , uesrname, password 포함
 *  dataSource => 케넥션풀
 */
public class JdbcDataSourceConnectionFactory implements ConnectionFactory{

    private final DatabaseVendor databaseVendor;

/*
*   connectionPool
*   JNDI(Java Naming and Directory Interface)
*
 * */
    private final DataSource dataSource;
    private final String username;
    private final String password;

//   default
    public JdbcDataSourceConnectionFactory(DatabaseVendor databaseVendor, DataSource dataSource) {
        this(databaseVendor, dataSource, null, null);
    }

    public JdbcDataSourceConnectionFactory(DatabaseVendor databaseVendor, DataSource dataSource, String username, String password) {
        this.databaseVendor = databaseVendor;
        this.dataSource = dataSource;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        try {
            if( username == null || password == null){
                return dataSource.getConnection();
            }
            return dataSource.getConnection(username, password);
        }catch (SQLException e){
            logError();
            throw e;
        }catch (Exception e){
            logError();
        }
        return null;
    }

    @Override
    public DatabaseVendor getDatabaseVendor() {
        return databaseVendor;
    }


    private void logError(){
//        get logger form agent
    }
}
