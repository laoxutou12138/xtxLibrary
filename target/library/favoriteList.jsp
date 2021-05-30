<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cn.edu.niit.javabean.FavoritesInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: XTX
  Date: 2021/3/22
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="./layui/css/layui.css">
    <script src="./layui/jquery.js"></script>
</head>
<body>
<%
    List<FavoritesInfo> favoritesInfos = new ArrayList<FavoritesInfo>();
%>
<div class="layui-form" id="content">
    <table class="layui-table">
        <colgroup>
            <col width="150">
            <col width="150">
            <col width="200">
        </colgroup>
        <thead>
        <tr>
            <th>书名</th>
            <th>作者</th>
            <th>分类</th>
            <th style="width: 520px">描述</th>
            <th style="margin: auto">操作</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="favoritesInfo" items="${sessionScope.favoritesInfos}"
                   varStatus="state">
            <tr>
                <td>${favoritesInfo.bookName}</td>
                <td>${favoritesInfo.bookAuthor}</td>
                <td>${favoritesInfo.book_sort}</td>
                <td>${favoritesInfo.book_decription}</td>
                <td>
                    <button class="layui-btn layui-btn-xs borrow collect"
                            data-index="${favoritesInfo.book_id}">
                            ${favoritesInfo.collection_status?"已收藏":"收藏"}
                    </button>

                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<div id="page" style="text-align:center;margin:0 auto;"></div>

<script src="/layui/layui.js"></script>
<script>
    layui.use(['laypage', 'layer', 'element'], function () {
            var laypage = layui.laypage
                , layer = layui.layer, element =
                layui.element;
            var $ = layui.$;
            var count = 0, page = 1, limit = 5;

            $(document).ready(function () {
                getContent(1, 10);
                laypage.render({
                    elem: 'page',
                    count: count,
                    curr: page,
                    limits: [5, 10, 15, 20],
                    limit: limit,
                    layout: ['count', 'prev', 'page', 'next', 'limit'],
                    jump: function (obj, first) {
                        if (!first) {
                            getContent(obj.curr, obj.limit);
                            page = obj.curr;
                            limit = obj.limit;
                        }
                    }
                });
            });


            $(document).on('click', '.collect', function () {
                var that = this;
                var name = $(this).parents("tr").find("td").eq(0).text();
                var bookid = $(this).attr("data-index");


                $.ajax({
                    type: 'POST',
                    url: "/book/collection",
                    data: JSON.stringify({
                        user: ${sessionScope.id}+"",
                        book: bookid,

                    }),
                    contentType: "application/json;charset=utf-8",
                    success: function (data) {
                        layer.msg(data)
                        if (data == '收藏成功') {
                            $(that).text("已收藏")

                            var frames = parent.document.getElementsByTagName("iframe");
                            for (let i = 0; i < frames.length; i++) {
                                console.log(frames[i])
                                if (frames[i].getAttribute("id") == '7') {
                                    console.log("执行刷新")
                                    var src = frames[i].getAttribute("src");
                                    frames[i].setAttribute("src", src + "?" + Math.random())
                                }
                            }
                            for (let i = 0; i < frames.length; i++) {
                                console.log(frames[i])
                                if (frames[i].getAttribute("id") == '1'){
                                    console.log("执行刷新")
                                    var src = frames[i].getAttribute("src");
                                    frames[i].setAttribute("src", src+"?"+Math.random())
                                }
                            }
                        } else if (data == '已取消收藏') {
                            $(that).text("收藏")

                            var frames = parent.document.getElementsByTagName("iframe");
                            for (let i = 0; i < frames.length; i++) {
                                console.log(frames[i])
                                if (frames[i].getAttribute("id") == '7') {
                                    console.log("执行刷新")
                                    var src = frames[i].getAttribute("src");
                                    frames[i].setAttribute("src", src + "?" + Math.random())
                                }
                            }

                            for (let i = 0; i < frames.length; i++) {
                                console.log(frames[i])
                                if (frames[i].getAttribute("id") == '1'){
                                    console.log("执行刷新")
                                    var src = frames[i].getAttribute("src");
                                    frames[i].setAttribute("src", src+"?"+Math.random())
                                }
                            }
                        }

                    }
                });
            })

            function getContent(page, size) {
                $.ajax({
                    type: 'POST',
                    url: "/book/favorites",
                    async: false,
                    data: JSON.stringify({
                        pageNum: page,
                        pageSize: size
                    }),
                    contentType: "application/json;charset=utf-8",
                    success: function (data) {
                        $('#content').load(location.href + " #content");
                        console.log(data)
                        count = data;
                    }
                });
            }
        }
    );
</script>


</body>
</html>
