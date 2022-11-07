package com.lucas.app.handler.dao.main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lucas.app.common.sql.Config;
import com.lucas.app.common.sql.DBConnector;
import com.lucas.app.handler.dto.ExampleDTO;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.lucas.app.handler.dao.main
 * fileName       : ExampleDAO
 * author         : lucas
 * date           : 2022-09-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-21        lucas       최초 생성
 *  db에 직접적으로 접근하는 클래스
 *
 */
public class ExampleDAO {
    public static ExampleDAO it;

    public static ExampleDAO getInstance(){
        if(it == null)
            it = new ExampleDAO();
        return it;
    }

    public ResultSet bookQuery(HttpServletRequest request) {
        String sqlStr = "select * from books where author = "
                + "'" + request.getParameter("author") + "'"
                + " and qty > 0 order by price desc";

        try {
            Connection conn = DBConnector.getConnection();
            ResultSet rset = null;
            Statement stmt = conn.createStatement();
            rset = stmt.executeQuery(sqlStr);
            conn.close();
            return rset;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<ExampleDTO> getAllExampleData(){
        List<Map<String, Object>> listofMaps = null;
        try {
            Connection conn = DBConnector.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            listofMaps = queryRunner.query(conn, "SELECT * FROM `Example`", new MapListHandler());
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }


        Gson gson = new Gson();
        ArrayList<ExampleDTO> selectedList = gson.fromJson(gson.toJson(listofMaps), new TypeToken<List<ExampleDTO>>() {
        }.getType());
        return selectedList;
    }
}
