<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String userName = (String) session.getAttribute("userName");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<p>
		<font size="6"> Categories </font>
	</p>
	<p style="color: green;">
		<font size="3"> Hello </font> <font size="3"> <%
 	out.print(userName);
 %>
		</font>
	</p>
	<table>
		<tr>
			<td valign="top">
				<%-- -------- Include menu HTML code -------- --%> <jsp:include
					page="/menu.jsp" />
			</td>
		</tr>
		<%@ page import="db.UserDB"%>
		<%@ page import="db.User"%>
		<%
			UserDB udb = new UserDB();
			User user = udb.getUser(userName);

			// only owners can see categories page 
			if (user.getRole() == 1) {
		%>
		<tr>
			<!-- Categories Table -->
			<%-- Import the java.sql package --%>
			<%@ page import="java.sql.*"%>
			<%@ page import="db.CategoriesDB"%>
			<%-- -------- SELECT Statement Code -------- --%>
			<%
				// Create the statement
					CategoriesDB cdb = new CategoriesDB();

					// Use the created statement to SELECT
					// the student attributes FROM the Student table.
					ResultSet rs = cdb.getCategories();
			%>
			<!-- Add an HTML table header row to format the results -->
			<span class="error" style="color: red">${error}</span>
			<table border="1">
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Description</th>
				</tr>

				<tr>
					<form action="Categories" method="POST">
						<input type="hidden" name="action" value="insert" />
						<th>&nbsp;</th>
						<th><input value="" name="name" size="15" /></th>
						<th><input value="" name="description" size="15" /></th>
						<th><input type="submit" value="Insert" /></th>
					</form>
				</tr>

				<%-- -------- Iteration Code -------- --%>
				<%
					// Iterate over the ResultSet
						while (rs.next()) {
				%>

				<tr>
					<form action="Categories" method="POST">
						<input type="hidden" name="action" value="update" /> <input
							type="hidden" name="id" value="<%=rs.getInt("id")%>" />

						<%-- Get the id --%>
						<td><%=rs.getInt("id")%></td>

						<%-- Get the pid --%>
						<td><input value="<%=rs.getString("name")%>" name="name"
							size="15" /></td>

						<%-- Get the first name --%>
						<td><input value="<%=rs.getString("description")%>"
							name="description" size="15" /></td>

						<%-- Button --%>
						<td><input type="submit" value="Update"></td>
					</form>
					<form action="Categories" method="POST">
						<input type="hidden" name="action" value="delete" /> <input
							type="hidden" value="<%=rs.getInt("id")%>" name="id" />
						<%-- Button --%>
						<td><input type="submit" value="Delete" /></td>
					</form>
				</tr>
				<%
					}
				%>
			</table>

		</tr>
		<%
			} else {
		%>
		<tr>
			<td>
				<p style="color: red">
					<font size="4"> This page is available to owners only. 
			</td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>
