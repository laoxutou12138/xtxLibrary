package cn.edu.niit.servlet;

import cn.edu.niit.dao.LoginDao;
import cn.edu.niit.javabean.User;
import cn.edu.niit.service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

/**
 * @program: xtxLibrary
 * @ClassName: RegisterServlet
 * @description: Test
 * @author: XTX
 * @create: 2021-03-23 01:16
 **/
@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        RegisterService registerService = new RegisterService();
        String result = "注册失败";
        String userName, password, repassword,reader;
        userName = req.getParameter("username");
        password = req.getParameter("password");
        repassword=req.getParameter("repassword");
        reader = req.getParameter("reader");
        user = new User(userName, password, reader);
        LoginDao loginDao = new LoginDao();
        User byName = loginDao.selectOne(user.getUsername());
        if (byName != null) {
            result = "用户已经存在";
        } else if (password.equals(repassword)) {
                try {
                    result = registerService.register(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else {
                result = "密码不一致，请重新输入密码";
            }
        if (result.equals("注册成功")) {
            result = URLEncoder.encode(result,"UTF-8");
            resp.sendRedirect("/index.jsp?message=" + result);
        } else {
            req.getRequestDispatcher("/register.jsp?message=" +URLEncoder.encode(result,"utf-8")).forward(req, resp);

        }
    }
}

