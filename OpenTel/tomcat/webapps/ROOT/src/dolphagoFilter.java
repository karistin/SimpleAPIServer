package lucas.servlet.sample;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * packageName    : lucas.servlet.sample
 * fileName       : dolphagoFilter
 * author         : lucas
 * date           : 2022-09-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-16        lucas       최초 생성
 */

@WebFilter("/*")
public class dolphagoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //여기에 전처리
        request.setCharacterEncoding("UTF-8");
        System.out.println("doFilter() 전....");
        chain.doFilter(request, response);
        //여기에 후처리
        System.out.println("doFilter() 후....");

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
