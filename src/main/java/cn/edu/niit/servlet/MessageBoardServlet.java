package cn.edu.niit.servlet;

import cn.edu.niit.javabean.MessageBoard;
import cn.edu.niit.service.MessageBoardService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: xtxLibrary
 * @ClassName: MessageBoardServlet
 * @description: Test
 * @author: XTX
 * @date: 2021/4/27 22:37
 **/
@WebServlet(name = "MessageBoardServlet", urlPatterns = "/MessageBoard")
public class MessageBoardServlet extends HttpServlet {
    private MessageBoardService messageBoardService =new MessageBoardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramJson = IOUtils.toString(
                req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject = JSON.parseObject(paramJson, HashMap.class);
        int pageNum = (int) parseObject.get("pageNum");
        int pageSize = (int) parseObject.get("pageSize");
        int count = 0;
        List<MessageBoard> messageBoards =new ArrayList<MessageBoard>();
        messageBoards=messageBoardService.searchAllMessage(pageNum, pageSize);
        count=messageBoardService.messageCount();
        req.getSession().setAttribute("messageBoards", messageBoards);
        resp.getWriter().print(count);
    }
}
