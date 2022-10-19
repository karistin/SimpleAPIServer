package com.lucas.app.handler.action.main;

import com.lucas.app.common.controller.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : com.lucas.app.handler.action.main
 * fileName       : SaveCookieAction
 * author         : lucas
 * date           : 2022-09-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-29        lucas       최초 생성
 */
public class GetCookieAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "RequestDispatcher:jsp/getCookie.jsp";
    }
}
