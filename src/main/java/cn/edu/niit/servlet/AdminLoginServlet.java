package cn.edu.niit.servlet;

import cn.edu.niit.javabean.Admin;
import cn.edu.niit.service.AdminLoginService;

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
@WebServlet(name = "AdminLoginServlet",urlPatterns = "/admin/login")
public class AdminLoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin adminlogin=new Admin(req.getParameter("username"),req.getParameter("password"));

        AdminLoginService loginService=new AdminLoginService();
        String result=loginService.adminLogin(adminlogin,req.getSession());

        if ("1".equals(result)){
            resp.sendRedirect("./admin/main.jsp");
        }else {
            req.getRequestDispatcher("/index.jsp?message="+URLEncoder.encode(result,"utf-8"))
                    .forward(req,resp);
        }
    }
}
