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
 * fileName       : IdAction
 * author         : lucas
 * date           : 2022-09-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-26        lucas       최초 생성
 */
public class IdAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "RequestDispatcher:jsp/Id.jsp";
    }
}
