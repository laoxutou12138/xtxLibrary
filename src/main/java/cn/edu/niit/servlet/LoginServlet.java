package cn.edu.niit.servlet;

import cn.edu.niit.javabean.Login;
import cn.edu.niit.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


/**
 * @program: xtxLibrary
 * @ClassName: LoginServlet
 * @description: Test
 * @author: XTX
 * @create: 2021-03-22 15:03
 **/
@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login=new Login(req.getParameter("username"),req.getParameter("password"));
        LoginService loginService=new LoginService();
        String result=loginService.login(login,req.getSession());
        if ("1".equals(result)){
            resp.sendRedirect("/main.jsp");
        }else {
            req.getRequestDispatcher("/index.jsp?message="+ URLEncoder.encode(result,"utf-8")).forward(req,resp);
        }
    }
}
