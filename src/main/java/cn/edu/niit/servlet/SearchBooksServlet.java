package cn.edu.niit.servlet;

import cn.edu.niit.javabean.Book;
import cn.edu.niit.javabean.BorrowHistory;
import cn.edu.niit.service.BookService;
import cn.edu.niit.service.BorrowHistoryService;
import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
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
 * @ClassName: SearchBooksServlet
 * @description: Test
 * @author: XTX
 * @date: 2021/4/12 16:06
 **/
@WebServlet(name = "SearchBooksServlet", urlPatterns = "/book/search")
public class SearchBooksServlet extends HttpServlet {

    private BookService bookService = new BookService();
    private BorrowHistoryService borrowHistoryService = new BorrowHistoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramJson = IOUtils.toString(req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject = JSON.parseObject(paramJson, HashMap.class);
        String param = (String) parseObject.get("search");
        int pageNum = (int) parseObject.get("pageNum");
        int pageSize = (int) parseObject.get("pageSize");
        List<Book> books = new ArrayList<>();
        int count = 0;
        if (param != null) {
        } else {
            List<BorrowHistory> borrowHistories = borrowHistoryService.searchAllBorrowHistory((String) req.getSession().getAttribute("id"), pageNum, pageSize);
            bookService.BooksStats(borrowHistories,bookService);
            books = bookService.searchAllBooks((String) req.getSession().getAttribute("id"), pageNum, pageSize);
        }
        count = bookService.countNum();
        req.getSession().setAttribute("books", books);
        resp.getWriter().print(count);
    }
}
