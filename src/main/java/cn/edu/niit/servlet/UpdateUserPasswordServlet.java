package cn.edu.niit.servlet;

import cn.edu.niit.dao.LoginDao;
import cn.edu.niit.javabean.User;
import cn.edu.niit.service.UpdateUserService;

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
 * @ClassName: UpdateUserPasswordServlet
 * @description: Test
 * @author: XTX
 * @create: 2021-03-24 20:01
 **/
@WebServlet(name = "UpdateUserPasswordServlet", urlPatterns = "/UpdateUserPW")

public class UpdateUserPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = null;
        UpdateUserService updateUserService = new UpdateUserService();
        String result = "个人信息修改失败";
        String username,oldpassword,password,repassword;
        username=req.getParameter("username");
        oldpassword = req.getParameter("oldPassword");
        password = req.getParameter("password");
        repassword=req.getParameter("repassword");
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        LoginDao loginDao = new LoginDao();
        User byName = loginDao.selectOne(user.getUsername());
        if (byName.getPassword().equals(oldpassword)) {

            if (password.equals(repassword)) {
                try {
                    result = updateUserService.updatePW(user,req.getSession());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                result="两次密码不一样，请重新尝试";
            }
        }else {
            result="原密码错误！";
        }
        if (result.equals("密码修改成功")) {
            result = URLEncoder.encode(result,"UTF-8");
            resp.sendRedirect("/UpdatePassword.jsp?message=" + result);

        } else {

            req.getRequestDispatcher("/UpdatePassword.jsp?message=" +URLEncoder.encode(result,"utf-8")).forward(req, resp);

        }
    }
}
