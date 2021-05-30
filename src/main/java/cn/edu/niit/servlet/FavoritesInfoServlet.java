package cn.edu.niit.servlet;

import cn.edu.niit.javabean.FavoritesInfo;
import cn.edu.niit.service.FavoritesInfoService;
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
 * @ClassName: FavoritesInfoServlet
 * @description: Test
 * @author: XTX
 * @date: 2021/4/23 23:48
 **/
@WebServlet(name = "FavoritesInfoServlet", urlPatterns = "/book/favorites")
public class FavoritesInfoServlet extends HttpServlet {
    FavoritesInfoService favoritesInfoService=new FavoritesInfoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String paramJson = IOUtils.toString(
                req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject = JSON.parseObject(paramJson, HashMap.class);
        String param = (String) parseObject.get("search");
        int pageNum = (int) parseObject.get("pageNum");
        int pageSize = (int) parseObject.get("pageSize");
        List<FavoritesInfo> favoritesInfos=new ArrayList<FavoritesInfo>();
        int count = 0;
        if (param != null) {
        } else {
            favoritesInfos =favoritesInfoService.searfavoritesInfoByUserName((String) req.getSession().getAttribute("id"), pageNum, pageSize);
        }
        count=favoritesInfoService.countfavoritesInfoNum((String) req.getSession().getAttribute("id"));
        req.getSession().setAttribute("favoritesInfos", favoritesInfos);
        resp.getWriter().print(count);
    }
}
