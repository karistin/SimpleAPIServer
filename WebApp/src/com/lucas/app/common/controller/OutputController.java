package com.lucas.app.common.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * packageName    : com.lucas.app.common.controller
 * fileName       : OutputController
 * author         : lucas
 * date           : 2022-09-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-26        lucas       최초 생성
 */
@WebServlet("/WebServlet")
public class OutputController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println(request.getClass().getName());
//        System.out.println(request.getRequestURI());
//        System.out.println(request.getRequestURL());
//        System.out.println(request.getRemoteAddr());

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>Hello, World</title></head>");
        out.println("<body>");
        out.println("<h1>Hello, world!</h1>");  // says Hello
        // Echo client's request information
        out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
        out.println("<p>Protocol: " + request.getProtocol() + "</p>");
        out.println("<p>PathInfo: " + request.getPathInfo() + "</p>");
        out.println("<p>Remote Address: " + request.getRemoteAddr() + "</p>");
        out.println("<p>Method : " + request.getMethod() + "</p>");
        out.println("<p>Query String : " + request.getQueryString() + "</p>");
        out.println("<p>Content Type : " + request.getContentType() + "</p>");
        out.println("<p>Remote User : " + request.getRemoteUser() + "</p>");
        Enumeration<String> parmameters = request.getParameterNames();
        while (parmameters.hasMoreElements()) {
            out.println(parmameters.nextElement());
        }
        Enumeration<String> attribute = request.getAttributeNames();
        while (attribute.hasMoreElements()) {
            out.println(attribute.nextElement());
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            out.println(cookie.getName());
        }
//        out.println("<p>Remote User : " + request.getHeader() + "</p>");
        // Generate a random number upon each request
        out.println("<p>A Random Number: <strong>" + Math.random() + "</strong></p>");
        out.println("</body></html>");
        out.close();  // Always close the output writer

    }

}
