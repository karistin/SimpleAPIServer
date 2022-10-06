package com.lucas.app.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : com.lucas.app.common.controller
 * fileName       : Enter
 * author         : lucas
 * date           : 2022-10-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-06        lucas       최초 생성
 */
public class Enter {
    public static void onEnter(Object req, Object resp) {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        System.out.println(request.getLocalAddr());
    }
}
