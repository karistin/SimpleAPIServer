package com.lucas.app.common.sql;

import org.mariadb.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * packageName    : com.lucas.app.common.sql
 * fileName       : Config
 * author         : lucas
 * date           : 2022-09-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-21        lucas       최초 생성
 */
public class Config {
    /*
    *  DB에 로그인을 대신 해주는 클래스 입니다. 로그인만 대신 해주고, 쿼리문을 입력하는건 외부(DAO)에서
    *  DButils 라이브러리를 사용합니다.
    * */
    private Config(){}
    private static class Singleton{
        private static final Config instance = new Config();
    }

    public static Config getInstance(){
        return Singleton.instance;
    }

    private Connection conn = null;
    private String url = "jdbc:mariadb://localhost:3306/swaig?user=root&password=1234";

    public Connection sqlLogin(){
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("DB 연결 실패. mariaDB의 아이디 비밀번호가 Config 클래스와 일치하는지 확인해주세요.");
        }
        return conn;
    }
}
