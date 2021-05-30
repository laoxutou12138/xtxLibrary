<%--
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
    <script src="layui/jquery.js"></script>
</head>
<body>
借阅历史22
<input id="text"  />
<button id="button">发送</button>
<script>
    $('#button').click(() => {
        var values = $('#text').val();
        console.log($('#text').val());
        $.post(
            "/testServlet",
            {
                value: values
            },
            function (res) {
                console.log(res)
            }
        )
    });
</script>
</body>
</html>
