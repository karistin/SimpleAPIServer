package com.lucas.app.common.controller;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * packageName    : com.lucas.app.common.controller
 * fileName       : Controller
 * author         : lucas
 * date           : 2022-09-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-21        lucas       최초 생성
 */
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String RequestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = RequestURI.substring(contextPath.length());

        Action action = null;
        String forward = null;


        ServletContext context = getServletContext();
        String fullPath = context.getRealPath("/WEB-INF/class.properties");

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(fullPath);

        prop.load(fis);

        fis.close();

        String classPath = prop.getProperty(command);

        try{
            Class<?> url = Class.forName(classPath);
            action = (Action) url.newInstance();
            forward = action.execute(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (forward != null) {
            if (forward.contains("RequestDispatcher:")) {
                String jspName = (forward.split(":")[1]);
                request.getRequestDispatcher("WEB-INF/" + forward.split(":")[1]).forward(request, response);
            }
            else{
                PrintWriter pr = response.getWriter();
                pr.print(forward);
                pr.flush();
                pr.close();
            }
        }
    }
}