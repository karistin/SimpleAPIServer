package lucas.base.sql;

import lucas.base.trace.TraceContext;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * packageName    : lucas.base.sql
 * fileName       : Config
 * author         : lucas
 * date           : 2022-10-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-26        lucas       최초 생성
 */
public class Config {
    private Config(){}
    private static class Singleton{
        private static final Config instance = new Config();
    }

    public static Config getInstance() {
        return Singleton.instance;
    }
    private Connection conn = null;
    private String url = "jdbc:mariadb://localhost:3306/transactionDB?user=root&password=1234";

    public Connection sqlLogin(){

        try{
//            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("DB 연결 실패. mariaDB의 아이디 비밀번호가 Config 클래스와 일치하는지 확인해주세요.");
        }
        return conn;
    }

    public void insertTx(String txid, long ResponseTime, long CpuTime, long sqlTime, String serviceName, String remoteIp, int error) {
        String query = "insert into transaction (txid, ResponseTime, CpuTime, SqlTime, serviceName, remoteIp ,Error) values(?, ?, ?, ?, ?, ?, ? ,?)";
        try (PreparedStatement pstmt = this.conn.prepareStatement(query)) {
            pstmt.setString(1, txid);
            pstmt.setLong(2, ResponseTime);
            pstmt.setLong(3, CpuTime);
            pstmt.setLong(4, sqlTime);
            pstmt.setString(5, serviceName);
            pstmt.setString(6, remoteIp);
            pstmt.setInt(7, error);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public void insertTxProfile(TraceContext ctx) {
//        String query = "insert into transactionprofile (txid, ResponseTime, CpuTime, SqlTime, serviceName, remoteIp, endTime ,Error) values(?, ?, ?, ?, ?, ?, ? ,?)";
//        try (PreparedStatement pstmt = this.conn.prepareStatement(query)) {
//            pstmt.setString(1, txid);
//            pstmt.setLong(2, ResponseTime);
//            pstmt.setLong(3, CpuTime);
//            pstmt.setLong(4, sqlTime);
//            pstmt.setString(5, serviceName);
//            pstmt.setString(6, remoteIp);
//            pstmt.setTimestamp(7, time);
//            pstmt.setInt(8, error);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
