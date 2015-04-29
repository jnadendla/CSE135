<%@page import="db.StatesDB"%>
<%@page import="db.RolesDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="javax.swing.text.html.Option"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style type="text/css">
html, body, #header {
    margin: 10px !important;
    padding: 10px !important;
}
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
</head>
<body style="text-align: center;">
<p style="text-align: center;">
    <font size="6">
        Account Information
    </font>
</p>

<form name="form1" action="Signup" method="post">
    Full name: <input type="text" name="fname" style="margin: 10px"><br>
    Age: <input type="text" name="age" style="margin: 10px"><br>  
    User Role: <select name="role" style="margin: 10px">
       <%  
       RolesDB rdb = new RolesDB();
       ResultSet rs = rdb.getRoles();
       while(rs.next()) {
          String option = rs.getString("role");
	   %>
	   <option value="<%= option %>"><%= option %></option>
	   <% } %>
    </select><br>
    State: <select name="state" style="margin: 10px">
       <%  
       StatesDB sdb = new StatesDB();
       ResultSet states = sdb.getStates();
       while(states.next()) {
          String option = states.getString("name");
       %>
       <option value="<%= option %>"><%= option %></option>
       <% } %>
    </select><br>   
    <input type="submit" value="Create Account"/>
</form>

</body>
</html>
