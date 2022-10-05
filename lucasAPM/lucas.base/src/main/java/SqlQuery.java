import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : SqlQuery
 * author         : lucas
 * date           : 2022-10-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-05        lucas       최초 생성
 */
public class SqlQuery {
    private static final SqlQuery sqlQuery = new SqlQuery();
    public static SqlQuery getInstance(){return sqlQuery;}
    private String url = "jdbc:mariadb://localhost:3306";
    private Connection conn;
    private Statement stat;

    public void connection(String schema,String id, String password)
    {
        url += "/"+schema+"?user="+id+"&password="+password;
        try
        {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            stat = conn.createStatement();
            System.out.println("Sql connection success");
        }catch (Exception e){
            System.out.println("Sql connection fail");
        }
    }

    public void excuteCol(String col, String table)
    {
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM " + table);
            while (result.next())
            {
                if (result.getString(col) != null)
                    System.out.println( result.getString(col));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertRow(String id, String clientIp, long responseTime)
    {
        String insert = "INSERT INTO transaction(txid,clientIp,response_time) values(" +
                "'" +id+ "'," +
                "INET_ATON('" +clientIp+"'),"
                +responseTime
                +")";
        try {
            stat.executeUpdate(insert);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertRow(String id, String clientIp,Timestamp timestamp, long responseTime)
    {
//        System.out.println(timestamp);
        String insert = "INSERT INTO transaction(txid,clientIp,start_time,response_time) values(" +
                "'" +id+ "'," +
                "INET_ATON('" +clientIp+"'),'"
                +timestamp.toString()+"',"
                +responseTime
                +")";
        System.out.println(insert);
        try {
            stat.executeUpdate(insert);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
