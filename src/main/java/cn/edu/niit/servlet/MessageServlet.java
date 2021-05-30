package cn.edu.niit.servlet;

import cn.edu.niit.service.MessageBoardService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @program: xtxLibrary
 * @ClassName: MessageBoardServlet
 * @description: Test
 * @author: XTX
 * @date: 2021/4/27 22:37
 **/
@WebServlet(name = "MessageServlet", urlPatterns = "/MessageBoardPublish")
public class MessageServlet extends HttpServlet {

    private MessageBoardService messageBoardService =new MessageBoardService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramJson = IOUtils.toString(req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject =JSON.parseObject(paramJson, HashMap.class);
        String username = (String) parseObject.get("user");
        String messageBoard=(String) parseObject.get("message");
        String message = messageBoardService.insertMessage(username, messageBoard);
        resp.getWriter().println(message);
    }
}
