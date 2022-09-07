package agent.bridge.datastore;

/**
 * packageName    : agent.bridge.datastore
 * fileName       : UnknownDatabaseVendor
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
public class UnknownDatabaseVendor extends JdbcDatabaseVendor{
    public static final DatabaseVendor INSTANCE = new UnknownDatabaseVendor();

    private UnknownDatabaseVendor(){
        super("Unknown", DatastoreVendor.JDBC.name(),false);
    }
    @Override
    public DatastoreVendor getDatastoreVendor() {
        return DatastoreVendor.JDBC;
    }
}
