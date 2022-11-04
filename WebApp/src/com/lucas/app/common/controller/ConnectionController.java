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
import java.util.Date;

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
            Statement stmt = con.createStatement();

            String query = "select * from t_member";
            ResultSet rest = stmt.executeQuery(query);
            PrintWriter out = resp.getWriter();
//            out.println(rest.next());
            out.println("<!DOCTYPE html>");
            out.print("<html><body>");
            out.print("<table border=1><tr align='center' bgcolor='lightgreen'>");
            out.print("<td>아이디</td><td>비밀번호</td ><td>이름</td><td>이메일</td><td>가입일</td><td >삭제</td></tr>");
            while (rest.next()) {
                String id = rest.getString("id");
                String pwd = rest.getString("pwd");
                String name = rest.getString("name");
                String email = rest.getString("email");
                Date joinDate = rest.getDate("joinDate");
                out.print("<tr><td>"+id+"</td><td>"
                        +pwd+"</td><td>"
                        +name+"</td><td>"
                        +email+"</td><td>"
                        +joinDate+"</td><td>"
                        +"<a href='/Member?command=delMember&id="+id+"'>삭제 </a></td></tr>");
            }
            out.print("</table></body></html>");
            out.print("<a href='/form.do'>새 회원 등록하기</a");
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
