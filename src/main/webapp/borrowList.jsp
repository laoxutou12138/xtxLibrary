<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cn.edu.niit.javabean.BorrowInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: XTX
  Date: 2021/3/15
  Time: 16:04
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
    List<BorrowInfo> borrowInfos = new ArrayList<BorrowInfo>();
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
            <th style="margin: auto">借阅时间</th>
            <th style="margin: auto">截止时间</th>
            <th style="margin: auto">借阅状态</th>
            <th style="margin: auto">操作</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="borrowInfo" items="${sessionScope.borrowInfos}"
                   varStatus="state">
            <tr>
                <td>${borrowInfo.bookname}</td>
                <td>${borrowInfo.author}</td>
                <td>${borrowInfo.sort}</td>

                <td>${borrowInfo.borrow_date}</td>
                <td>${borrowInfo.end_date}</td>
                <td>${borrowInfo.illegal}</td>
                <td>
                    <button class="layui-btn layui-btn-xs borrow store"
                            data-index="${borrowInfo.book_id}">
                            ${borrowInfo.store?"归还":"借阅"}
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




        $(document).on('click', '.store',function () {
            var that = this;
            var name = $(this).parents("tr").find("td").eq(0).text();
            var borrow_date= $(this).parents("tr").find("td").eq(3).text();
            var bookid = $(this).attr("data-index");
            var store= ${(that).text=='借阅'?true:false};
            console.log(store)
            console.log(borrow_date)
            $.ajax({
                type: 'POST',
                url: "/book/store",
                data: JSON.stringify({
                    user: ${sessionScope.id}+"",
                    borrowTime:borrow_date,
                    book: bookid,
                    store:store
                }),
                contentType: "application/json;charset=utf-8",
                success: function (data) {
                    layer.msg(data)
                    if (data == '借阅成功') {
                        $(that).text("归还")

                        var frames = parent.document.getElementsByTagName("iframe");
                        for (let i = 0; i < frames.length; i++) {
                            console.log(frames[i])
                            if (frames[i].getAttribute("id") == '3'){
                                console.log("执行刷新")
                                var src = frames[i].getAttribute("src");
                                frames[i].setAttribute("src", src+"?"+Math.random())
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
                        for (let i = 0; i < frames.length; i++) {
                            console.log(frames[i])
                            if (frames[i].getAttribute("id") == '2'){
                                console.log("执行刷新")
                                var src = frames[i].getAttribute("src");
                                frames[i].setAttribute("src", src+"?"+Math.random())
                            }
                        }


                    }else if (data == '归还成功'){
                        $(that).text("借阅")

                        var frames = parent.document.getElementsByTagName("iframe");
                        for (let i = 0; i < frames.length; i++) {
                            console.log(frames[i])
                            if (frames[i].getAttribute("id") == '3'){
                                console.log("执行刷新")
                                var src = frames[i].getAttribute("src");
                                frames[i].setAttribute("src", src+"?"+Math.random())
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

                        for (let i = 0; i < frames.length; i++) {
                            console.log(frames[i])
                            if (frames[i].getAttribute("id") == '2'){
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
                url: "/book/borrowInfo",
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
