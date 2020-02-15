<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "br.com.demoServlet.models.User" %>
<%@ page import = "java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users</title>
</head>
<body>
	<h3>Users</h3>
	<a href="userServlet?action=store">Add User</a>
	<table border="1px solid">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Email</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		<%
		List<User> users = (List<User>)request.getAttribute("users");
		for(User user : users) {
		%>
		<tr>
			<td><%= user.getId() %></td>
			<td><%= user.getName() %></td>
			<td><%= user.getEmail() %></td>
			<td><a href="userServlet?action=update&id=<%= user.getId() %>">Update</a></td>
			<td><a href="userServlet?action=remove&id=<%= user.getId() %>">Delete</a></td>
		</tr>
		<%
		}	
		%>
	</table>
</body>
</html>