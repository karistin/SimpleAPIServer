package com.lucas.app.handler.action.main;

import com.google.gson.Gson;
import com.lucas.app.common.controller.Action;
import com.lucas.app.handler.dao.main.ExampleDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.ResultSet;

/**
 * packageName    : com.lucas.app.handler.action.main
 * fileName       : queryResultAction
 * author         : lucas
 * date           : 2022-10-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-21        lucas       최초 생성
 */
public class queryResultAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Gson gson = new Gson();
        ResultSet rset = ExampleDAO.getInstance().bookQuery(request);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int count = 0;
        while(rset.next()) {
            // Print a paragraph <p>...</p> for each record
            out.println("<p>" + rset.getString("author")
                    + ", " + rset.getString("title")
                    + ", $" + rset.getDouble("price") + "</p>");
            count++;
        }
        out.close();
        return "RequestDispatcher:jsp/queryResult.jsp";
    }
}
