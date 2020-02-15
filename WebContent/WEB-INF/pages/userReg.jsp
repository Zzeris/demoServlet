<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "br.com.demoServlet.models.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
</head>
<body>
	<%
	String id = "";
	String name = "";
	String email = "";
	if (request.getAttribute("user") != null) {
		User user = new User();
		user = (User)request.getAttribute("user");
		id = user.getId().toString();
		name = (String)user.getName();
		email = (String)user.getEmail();
	}
	%>
	<form method="post" action="userServlet">
		<input type="hidden" name="id" value="<%= id %>"/>
		Name.: <input type="text" name="name" value="<%= name %>"/> <br />
		Email.: <input type="text" name="email" value="<%= email %>"/>
		<input type="submit" value="register"/>
	</form>
</body>
</html>