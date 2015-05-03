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
        <font size="6"> Browse Products </font>
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
                        <form action="ProductBrowser" method="post">
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
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>SKU</th>
                    <th>Price</th>
                    <th>Category</th>
                    <th>Quantity</th>
                </tr>

                <%-- -------- Iteration Code -------- --%>
                <%
                    // Iterate over the ResultSet
                        while (rs.next()) {
                %>

                <tr>
                    <form action="ProductBrowser" method="POST">
                        <input type="hidden" name="action" value="update" /> 
                        <input type="hidden" name="id" value="<%=rs.getInt("id")%>" />
                        <input type="hidden" name="pname" value="<%=rs.getString("name")%>" />
                        <input type="hidden" name="sku" value="<%=rs.getString("sku")%>" />
                        <input type="hidden" name="price" value="<%=rs.getString("price")%>" />

                        <%-- Get the id --%>
                        <td><%=rs.getInt("id")%></td>
                        <%-- Get the name --%>
                        <td><%=rs.getString("name")%></td>
                        <%-- Get the sku --%>
                        <td><%=rs.getString("sku")%></td>
                        <%-- Get the price --%>
                        <td><%=rs.getString("price")%></td>
                        <%-- Get the category --%>
                        <td>
                                <%
                                    cs = cdb.getCategories();
                                    while (cs.next()) {
                                       if(rs.getString("category").equals(cs.getString("id"))) {
                                %>
                                    <input type="hidden" name="category" value="<%=cs.getString("name")%>" />
                                    <%=cs.getString("name")%>
                                <%
                                       }
                                    }
                                %>
                        </td>
                        <%-- Button --%>
                        <td><input type="submit" value="Order"></td>
                    </form>
                </tr>
                <%
                    }
                %>
            </table>

        </tr>
    </table>
</body>
</html>
