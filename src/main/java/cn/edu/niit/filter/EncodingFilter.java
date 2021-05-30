package cn.edu.niit.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @program: xtxLibrary
 * @ClassName: EncodingFilter
 * @description: Test
 * @author: XTX
 * @create: 2021-03-29 13:57
 **/
@WebFilter(filterName = "EncodingFilter" ,urlPatterns = "/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fchain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        fchain.doFilter(req,resp);



    }

    @Override
    public void destroy() {

    }
}
