package cn.edu.niit.servlet;

import cn.edu.niit.javabean.BorrowHistory;
import cn.edu.niit.service.BorrowHistoryService;
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
 * @ClassName: BorrowHistoryServlet
 * @description: Test
 * @author: XTX
 * @date: 2021/4/27 17:55
 **/
@WebServlet(name = "BorrowHistoryServlet", urlPatterns = "/book/borrowHistory")
public class BorrowHistoryServlet extends HttpServlet {

    private BorrowHistoryService borrowHistoryService=new BorrowHistoryService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramJson = IOUtils.toString(
                req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject = JSON.parseObject(
                paramJson, HashMap.class);
        String param = (String) parseObject.get("search");
        int pageNum = (int) parseObject.get("pageNum");
        int pageSize = (int) parseObject.get("pageSize");
        List<BorrowHistory> borrowHistories = new ArrayList<BorrowHistory>();
        int count = 0;
        //2.
        if (param != null) {
        } else {
            borrowHistories = borrowHistoryService.searchAllBorrowHistory((String) req.getSession().getAttribute("id"), pageNum, pageSize);
        }
        count = borrowHistoryService.countBorrowHistoryNum((String) req.getSession().getAttribute("id"));
        req.getSession().setAttribute("borrowHistories", borrowHistories);
        resp.getWriter().print(count);
    }
}
