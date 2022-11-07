package com.lucas.app.common.sql;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * packageName    : com.lucas.app.common.sql
 * fileName       : DBConnector
 * author         : lucas
 * date           : 2022-10-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-27        lucas       최초 생성
 */
public class DBConnector {
    private DBConnector(){}
    private static class Singleton{
        private static final DBConnector instance = new DBConnector();
    }


    public static DBConnector getInstance() {
        return Singleton.instance;
    }
    public static Connection getConnection() throws NamingException, SQLException {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        InitialContext initalContext = new InitialContext();
        DataSource ds = (DataSource) initalContext.lookup("java:comp/env/jdbc/mariadb");
//        System.out.println(ds.);
        Connection con = ds.getConnection();

        return con;
    }
}
