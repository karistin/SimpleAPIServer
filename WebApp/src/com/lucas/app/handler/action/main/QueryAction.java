package com.lucas.app.handler.action.main;

import com.lucas.app.common.controller.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : com.lucas.app.handler.action.main
 * fileName       : QueryAction
 * author         : lucas
 * date           : 2022-10-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-21        lucas       최초 생성
 */
public class QueryAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "RequestDispatcher:jsp/Query.jsp";
    }
}
