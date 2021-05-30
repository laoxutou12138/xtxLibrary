package cn.edu.niit.servlet;

import cn.edu.niit.service.FavoritesInfoService;
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
 * @ClassName: Collection_statusServlet
 * @description: Test
 * @author: XTX
 * @date: 2021/4/24 1:14
 **/
@WebServlet(name = "Collection_statusServlet", urlPatterns = "/book/collection")
public class Collection_statusServlet extends HttpServlet {
    FavoritesInfoService favoritesInfoService=new FavoritesInfoService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramJson = IOUtils.toString(
                req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject =
                JSON.parseObject(paramJson,
                        HashMap.class);
        String username = (String) parseObject.get("user");
        String bookId = (String) parseObject.get("book");
        String message = favoritesInfoService.storeBook(username, bookId);
        resp.getWriter().print(message);
    }
}
