<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: XTX
  Date: 2021/3/15
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>Title</title>
</head>
<body>

<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	Class.forName("com.mysql.cj.jdbc.Driver");
	try (Connection connection =DriverManager.getConnection("jdbc:mysql://192.168.150.1:3306/xtxlibrary?serverTimezone=UTC&characterEncoding=utf-8","root", "123456")) {
		String sql = "select * from borrow_card where username=?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, username);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					if (password.equals(resultSet.getString("password"))) {
						response.sendRedirect("./main.jsp");
					} else {
						response.sendRedirect("./index.jsp");
					}
				}
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
%>

</body>
</html>
