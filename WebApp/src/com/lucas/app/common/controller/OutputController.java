package com.lucas.app.common.controller;


import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet("/output")
public class OutputController extends HttpServlet {
    @Override
    public void init() throws ServletException {
//        System.out.println("Output Servlet Init =============");
        super.init();
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
//        IHttpTrace httpTrace = Request.DynamicHttp(request);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.print("Service");
        out.close();
        super.service(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.print("GET");
        out.close();


        super.doGet(request, response);
    }

    @Override
    public void destroy() {
//        System.out.println("Output Servlet Destory =============");
        super.destroy();
    }
}
