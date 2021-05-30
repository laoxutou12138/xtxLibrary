package cn.edu.niit.servlet;

import cn.edu.niit.service.BorrowInfoService;
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
 * @ClassName: StoreBookServlet
 * @description: Test
 * @author: XTX
 * @date: 2021/4/20 21:22
 **/
@WebServlet(name = "StoreBookServlet", urlPatterns = "/book/store")
public class StoreBookServlet extends HttpServlet {
    private BorrowInfoService borrowInfoService = new BorrowInfoService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramJson = IOUtils.toString(req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject =JSON.parseObject(paramJson,HashMap.class);
        String username = (String) parseObject.get("user");
        String bookId = (String) parseObject.get("book");
        Boolean bookstore = (Boolean) parseObject.get("store");
        String borrow_date = (String) parseObject.get("borrowTime");
        String message = borrowInfoService.storeBook(bookstore,username, bookId,borrow_date);
        resp.getWriter().print(message);
    }
}
