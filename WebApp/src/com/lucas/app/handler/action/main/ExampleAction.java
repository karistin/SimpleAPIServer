package com.lucas.app.handler.action.main;

import com.google.gson.Gson;
import com.lucas.app.common.controller.Action;
import com.lucas.app.handler.dao.main.ExampleDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : com.lucas.app.handler.action.main
 * fileName       : ExampleAction
 * author         : lucas
 * date           : 2022-09-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-21        lucas       최초 생성
 */
public class ExampleAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Gson gson = new Gson();
        System.out.println(request.getRequestURI());
        request.setAttribute("ExampleData", gson.toJson(ExampleDAO.getInstance().getAllExampleData()));

        return "RequestDispatcher:jsp/example.jsp";
    }
}
