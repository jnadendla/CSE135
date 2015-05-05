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
		<font size="6"> Products </font>
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
					page="/menu.html" />
			</td>
		</tr>
		<%@ page import="db.UserDB"%>
		<%@ page import="db.User"%>
		<%
			UserDB udb = new UserDB();
			User user = udb.getUser(userName);

			// only owners can see product page 
			if (user.getRole() == 1) {
		%>
		<tr>
			<%
				CategoriesDB cdb = new CategoriesDB();
					ResultSet cs = cdb.getCategories();

					String searchTerm = (String) request.getParameter("searchTerm");
					String categoryFilter = (String) request
							.getParameter("categoryFilter");
			%>
			<table>
				<tr>
					<td>
						<form action="Products" method="GET">
							<input type="hidden" name="action" value="search" />
							<td><select name="categoryFilter" onchange="this.form.submit()">
									<option value="">Category</option>
									<%
										while (cs.next()) {
									%>
									<option
										<%if (categoryFilter != null
							&& categoryFilter.equals(cs.getString("id"))) {%>
										selected="selected" <%}%> value=<%=cs.getString("id")%>>
										<%=cs.getString("name")%>
									</option>
									<%
										}
									%>
							</select></td>
							<td><input value="" name="searchTerm" size="15" /></td>
							<td><input type="submit" value="Search" /></td>
						</form>
					</td>
				</tr>
			</table>
			<!-- Products Table -->
			<%-- Import the java.sql package --%>
			<%@ page import="java.sql.*"%>
			<%@ page import="db.ProductsDB"%>
			<%@ page import="db.CategoriesDB"%>
			<%-- -------- SELECT Statement Code -------- --%>
			<%
					// Create the statement
					ProductsDB pdb = new ProductsDB();

					// Use the created statement to SELECT
					// the student attributes FROM the Student table.
					ResultSet rs = pdb.getProducts(categoryFilter, searchTerm);
					cs = cdb.getCategories();
			%>
			<!-- Add an HTML table header row to format the results -->
			<span class="error" style="color: red">${error}</span>
			<span class="success" style="color: green">${success}</span>
			<table border="1">
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>SKU</th>
					<th>Price</th>
					<th>Category</th>
				</tr>

				<tr>
					<form action="Products" method="POST">
						<input type="hidden" name="action" value="insert" />
						<th>&nbsp;</th>
						<th><input value="" name="name" size="15" /></th>
						<th><input value="" name="sku" size="15" /></th>
						<th><input value="" name="price" size="15" /></th>
						<th><select name="category">
								<%
									while (cs.next()) {
								%>
								<option value=<%=cs.getString("id")%>>
									<%=cs.getString("name")%>
								</option>
								<%
									}
								%>
						</select></th>
						<th><input type="submit" value="Insert" /></th>
					</form>
				</tr>

				<%-- -------- Iteration Code -------- --%>
				<%
					// Iterate over the ResultSet
						while (rs.next()) {
				%>

				<tr>
					<form action="Products" method="POST">
						<input type="hidden" name="action" value="update" /> <input
							type="hidden" name="id" value="<%=rs.getInt("id")%>" />

						<%-- Get the id --%>
						<td><%=rs.getInt("id")%></td>

						<%-- Get the name --%>
						<td><input value="<%=rs.getString("name")%>" name="name"
							size="15" /></td>

						<%-- Get the sku --%>
						<td><input value="<%=rs.getString("sku")%>" name="sku"
							size="15" /></td>
						<%-- Get the price --%>
						<td><input value="<%=rs.getString("price")%>" name="price"
							size="15" /></td>
						<%-- Get the category --%>
						<td><select name="category">
								<%
									cs = cdb.getCategories();
											while (cs.next()) {
								%>
								<option value=<%=cs.getString("id")%>
									<%if (rs.getString("category").equals(cs.getString("id"))) {%>
									selected="selected" <%}%>>
									<%=cs.getString("name")%>
								</option>
								<%
									}
								%>
						</select></td>

						<%-- Button --%>
						<td><input type="submit" value="Update"></td>
					</form>
					<form action="Products" method="POST">
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
