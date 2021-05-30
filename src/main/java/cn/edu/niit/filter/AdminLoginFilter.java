package cn.edu.niit.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: xtxLibrary
 * @ClassName: AdminLoginFilter
 * @description: Test
 * @author: XTX
 * @create: 2021-03-29 14:38
 **/

@WebFilter(filterName = "AdminLoginFilter",urlPatterns = "/login")
public class AdminLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fChain) throws IOException, ServletException {


        String  role = req.getParameter("role");
        if ("0".equals(role)) {
            ((HttpServletRequest)req).getRequestDispatcher("/admin/login").forward(req,resp);
        }else {
            ((HttpServletRequest)req).getRequestDispatcher("/login").forward(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}
