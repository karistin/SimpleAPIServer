package com.lucas.app.common.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * packageName    : com.lucas.app.common.filter
 * fileName       : doFilter
 * author         : lucas
 * date           : 2022-09-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-26        lucas       최초 생성
 */
@WebFilter("/Id.do")
public class doFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
//        System.out.println("Do Filtering");

        filterChain.doFilter(servletRequest, servletResponse);
//        System.out.println("Do Filter After......");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
