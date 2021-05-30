package cn.edu.niit.servlet;

import cn.edu.niit.javabean.User;
import cn.edu.niit.service.LoginService;
import cn.edu.niit.service.UpdateUserService;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @program: xtxLibrary
 * @ClassName: UpdateUserServlet
 * @description: Test
 * @author: XTX
 * @create: 2021-03-24 15:38
 **/
@MultipartConfig
@WebServlet(name = "UpdateUserServlet", urlPatterns = "/UpdateUser")
public class UpdateUserServlet extends HttpServlet {
    private LoginService loginService = new LoginService();
    JSONObject json = new JSONObject();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=utf-8");
        User user = null;
        UpdateUserService updateUserService = new UpdateUserService();
        String result = "个人信息修改失败";
        String username, reader, mydescribe, cellphone, email, sex;
        username = req.getParameter("username");
        reader = req.getParameter("reader");
        mydescribe = req.getParameter("remarks");
        cellphone = req.getParameter("cellphone");
        email = req.getParameter("email");
        sex = req.getParameter("sex");

        user = new User();
        user.setReader(reader);
        user.setUsername(username);
        user.setCellphone(cellphone);
        user.setMydescribe(mydescribe);
        user.setEmail(email);
        user.setSex(sex.equals("男") ? true : false);

        String filename = null;
        Part part = req.getPart("imageUpload");
        String disposition = part.getHeader("Content-Disposition");
        if (part.getSize() > 0L) {
            String suffix = disposition.substring(disposition.lastIndexOf("."), disposition.length() - 1);
            filename = UUID.randomUUID() + suffix;
            InputStream is = part.getInputStream();
            String serverpath = "C:\\img";
            FileOutputStream fos = new FileOutputStream(serverpath + "/" + filename);
            byte[] bty = new byte[1024];
            int length = 0;
            while ((length = is.read(bty)) != -1) {
                fos.write(bty, 0, length);
            }
            fos.close();
            is.close();
            user.setPortrait("img/" + filename);
        }
        try {
            if (part.getSize() > 0L) {
                result = updateUserService.update(user, req.getSession());
            } else {
                result = updateUserService.updateOther(user, req.getSession());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result.equals("个人信息已更新")) {
            req.getSession().removeAttribute("user");
            req.getSession().setAttribute("user", loginService.getUserInfo(user.getUsername()));
            result = URLEncoder.encode(result, "UTF-8");
            req.getRequestDispatcher("/personalInfo.jsp?message=" + result).forward(req, resp);
        } else {
            req.getRequestDispatcher("/personalInfo.jsp?message=" + URLEncoder.encode(result, "utf-8")).forward(req, resp);
        }
    }
}
