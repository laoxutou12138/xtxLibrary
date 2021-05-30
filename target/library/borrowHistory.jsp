<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cn.edu.niit.javabean.BorrowHistory" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: XTX
  Date: 2021/3/22
  Time: 21:38
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
    List<BorrowHistory> borrowHistories = new ArrayList<BorrowHistory>();
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
        <th style="margin: auto">归还时间</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="borrowHistory" items="${sessionScope.borrowHistories}"
               varStatus="state">
        <tr>
            <td>${borrowHistory.bookname}</td>
            <td>${borrowHistory.author}</td>
            <td>${borrowHistory.sort}</td>

            <td>${borrowHistory.borrow_date}</td>
            <td>${borrowHistory.end_date}</td>
            <td>${borrowHistory.return_date}</td>

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

            function getContent(page, size) {
                $.ajax({
                    type: 'POST',
                    url: "/book/borrowHistory",
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
