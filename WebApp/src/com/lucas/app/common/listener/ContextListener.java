package com.lucas.app.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * packageName    : com.lucas.app.common.listener
 * fileName       : ContextListener
 * author         : lucas
 * date           : 2022-09-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-26        lucas       최초 생성
 */
@WebListener
public class ContextListener implements ServletRequestListener {

    Long time = 0L;
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
//        time = System.currentTimeMillis();
//        System.out.println("Client Request");
//        System.out.println(sre.getServletRequest().getRemoteAddr().toString());

        ServletRequestListener.super.requestInitialized(sre);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
//        System.out.println("Listener : "+sre.getServletRequest().getServletContext().getContextPath()+(System.currentTimeMillis()-time)+"ms");
        ServletRequestListener.super.requestDestroyed(sre);
    }
}
