package com.lucas.app.common.controller;

import com.lucas.app.common.sql.DBConnector;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * packageName    : com.lucas.app.common.controller
 * fileName       : ConnectionController
 * author         : lucas
 * date           : 2022-10-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-27        lucas       최초 생성
 */

@WebServlet("/ConnectionPool")
public class ConnectionController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Connection con = DBConnector.getConnection();
            Statement stmt = con.prepareStatement("use swaig");

            String query = "select * from t_member";
            ResultSet rest = stmt.executeQuery(query);
            PrintWriter out = resp.getWriter();
            out.println(rest.next());
//            out.println("<!DOCTYPE html>");
//            while (rest.next()) {
//                out.println(rest.getString(1));
//                out.println(rest.getString(2));
//                out.println(rest.getString(3));
//                out.println(rest.getDate(4));
//            }
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
