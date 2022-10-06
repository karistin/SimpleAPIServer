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
    public void init() throws ServletException {
//        System.out.println("Controller Servlet Init =============");
        super.init();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        long time = System.currentTimeMillis();
//        Enter.onEnter(request, response);
//        System.out.println(request.getClass().getName());

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

//        System.out.println(Thread.currentThread().getName());

        try{
            Class<?> url = Class.forName(classPath);
            action = (Action) url.newInstance();
            forward = action.execute(request, response);
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

//        System.out.println("controller : "+(System.currentTimeMillis()-time)+"ms");

        System.out.println(request.getQueryString());
    }

    @Override
    public void destroy() {
//        System.out.println("Controller Servlet Destory =============");
        super.destroy();
    }
}
