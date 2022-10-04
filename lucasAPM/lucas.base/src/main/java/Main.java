import lucas.base.tranacation.TranscationId;

import java.sql.*;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : Main
 * author         : lucas
 * date           : 2022-09-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-29        lucas       최초 생성
 */
public class Main {
    public static void main(String[] args) {
        Time time;
        String url = "jdbc:mariadb://localhost:3306/transactionDB?user=root&password=1234";

        String id  = TranscationId.generateID();
        String clientIp = "127.0.0.1";
        long responseTime = System.currentTimeMillis();
        System.out.println(responseTime);

        String insert = "INSERT INTO transaction(txid,clientIp,response_time) values("
                +id+",INET_ATON(" +clientIp+"),"+responseTime+")";

        Connection conn;
        ResultSet listofMaps = null;
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            Statement stat = conn.createStatement();
//            listofMaps= stat.executeQuery("SELECT * from transaction");
//            while(listofMaps.next())
//            {
//                System.out.println(listofMaps.getTime("start_time"));
//            }

            stat.executeUpdate(insert);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
