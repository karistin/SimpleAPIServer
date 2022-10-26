package lucas.base.sql;

import java.sql.Connection;
import java.sql.DriverManager;

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
}
