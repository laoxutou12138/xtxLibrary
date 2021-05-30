<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cn.edu.niit.javabean.Book" %>
<%@ page import="cn.edu.niit.javabean.BorrowInfo" %>
<%@ page import="cn.edu.niit.javabean.FavoritesInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: XTX
  Date: 2021/3/15
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="./layui/css/layui.css">

    <script src="./layui/jquery.js"></script>
</head>

<body>
<div class="layui-nav-item demoTable">
    <button class="layui-btn" data-type="getCheckLength"
            style="float: right">
        搜索
    </button>
    <input type="text" class="layui-input"
           placeholder="请输入搜索信息......"
           style="float: right;width: auto;margin-bottom: 10px">

</div>

<% List<Book> books = new ArrayList<Book>();
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

        <c:forEach var="book" items="${sessionScope.books}"
                   varStatus="state">
            <tr>
                <td>${book.name}</td>
                <td>${book.author}</td>
                <td>${book.sort}</td>
                <td>${book.description}</td>

                <td>
                    <a class="layui-btn layui-btn-primary layui-btn-xs"
                       lay-even="detail">查看</a>
                    <button class="layui-btn layui-btn-xs borrow store"
                            data-index="${book.id}" data-borrow-time="${book.borrow_date}">
                            ${book.store?"归还":"借阅"}
                    </button>
                    <button class="layui-btn layui-btn-xs borrow collect"
                            data-index="${book.id}">
                            ${book.collection_status?"已收藏":"收藏"}
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

            $(document).on('click', '#info', function () {
                var name = $(this).parents("tr").find("td").eq(0).text();
                layer.msg(name)
            })


            $(document).on('click', '.store', function () {
                var that = this;
                var name = $(this).parents("tr").find("td").eq(0).text();
                var bookid = $(this).attr("data-index");
                var store = ${(that).text=='借阅'?true:false};
                var borrowdate = $(this).attr("data-borrow-time");
                console.log(store)
                console.log(borrowdate)

                $.ajax({
                    type: 'POST',
                    url: "/book/store",
                    data: JSON.stringify({
                        user: ${sessionScope.id}+"",
                        book: bookid,
                        store: store,
                        borrowTime: borrowdate
                    }),
                    contentType: "application/json;charset=utf-8",
                    success: function (data) {
                        layer.msg(data)
                        if (data == '借阅成功') {
                            $(that).text("归还")

                            var frames = parent.document.getElementsByTagName("iframe");
                            for (let i = 0; i < frames.length; i++) {
                                console.log(frames[i])
                                if (frames[i].getAttribute("id") == '1') {
                                    console.log("执行刷新")
                                    var src = frames[i].getAttribute("src");
                                    frames[i].setAttribute("src", src + "?" + Math.random())
                                }
                            }
                            for (let i = 0; i < frames.length; i++) {
                                console.log(frames[i])
                                if (frames[i].getAttribute("id") == '3') {
                                    console.log("执行刷新")
                                    var src = frames[i].getAttribute("src");
                                    frames[i].setAttribute("src", src + "?" + Math.random())
                                }
                            }

                            for (let i = 0; i < frames.length; i++) {
                                console.log(frames[i])
                                if (frames[i].getAttribute("id") == '2') {
                                    console.log("执行刷新")
                                    var src = frames[i].getAttribute("src");
                                    frames[i].setAttribute("src", src + "?" + Math.random())
                                }
                            }


                        } else if (data == '归还成功') {
                            $(that).text("借阅")


                            var frames = parent.document.getElementsByTagName("iframe");
                            for (let i = 0; i < frames.length; i++) {
                                console.log(frames[i])
                                if (frames[i].getAttribute("id") == '1') {
                                    console.log("执行刷新")
                                    var src = frames[i].getAttribute("src");
                                    frames[i].setAttribute("src", src + "?" + Math.random())
                                }
                            }
                            for (let i = 0; i < frames.length; i++) {
                                console.log(frames[i])
                                if (frames[i].getAttribute("id") == '3') {
                                    console.log("执行刷新")
                                    var src = frames[i].getAttribute("src");
                                    frames[i].setAttribute("src", src + "?" + Math.random())
                                }
                            }

                            for (let i = 0; i < frames.length; i++) {
                                console.log(frames[i])
                                if (frames[i].getAttribute("id") == '2') {
                                    console.log("执行刷新")
                                    var src = frames[i].getAttribute("src");
                                    frames[i].setAttribute("src", src + "?" + Math.random())
                                }
                            }
                        }

                    }
                });
            })


            $(document).on('click', '.collect', function () {
                var that = this;
                //可以获取第一列的内容，也就是name的值
                var name = $(this).parents("tr").find("td").eq(0).text();
                var bookid = $(this).attr("data-index");

                var store = ${(that).text=='收藏'?true:false};
                $.ajax({
                    type: 'POST',
                    url: "/book/collection",
                    data: JSON.stringify({
                        user: ${sessionScope.id}+"",
                        book: bookid,
                        store: store
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
                        }

                    }
                });
            })

            function getContent(page, size) {
                $.ajax({
                    type: 'POST',
                    url: "/book/search",
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
