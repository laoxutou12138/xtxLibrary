package cn.edu.niit.servlet;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: xtxLibrary
 * @ClassName: testServlet
 * @description: Test
 * @author: XTX
 * @date: 2021/4/21 13:35
 **/

@WebServlet("/testServlet")
public class testServlet extends HttpServlet {
    JSONObject json = new JSONObject();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json; charset=utf-8");
        String value = req.getParameter("value");
        System.out.println(value);
        PrintWriter out = resp.getWriter();
        json.put("message","后台接受到："+value);
        out.println(json);
    }
}
