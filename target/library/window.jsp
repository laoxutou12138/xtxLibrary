<%--
  Created by IntelliJ IDEA.
  User: XTX
  Date: 2021/3/22
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page language="java" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
 String message=request.getParameter("message");
 if (message!=null&&!message.equals("")){

     String message1 = new String(message.getBytes(), "utf-8");
%>
<script src="./layui/layui.all.js"></script>
<script>
    layer.msg("<%=message%>")
</script>
<%
    }
%>
</body>
</html>
