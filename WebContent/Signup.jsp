<%@page import="javax.swing.text.html.Option"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String[] roleList = {"Customer", "Owner"};
String[] stateList = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware",
      "District of Columbia", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
      "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota",
      "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
      "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
      "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
      "West Virginia", "Wisconsin", "Wyoming"};
%>
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

<form name="form1" action="Signup.jsp" method="GET">
    Full name: <input type="text" name="fname" style="margin: 10px"><br>
    Age: <input type="text" name="age" style="margin: 10px"><br>  
    User Role: <select name="role" style="margin: 10px">
       <%  for(int i = 0; i < roleList.length; i++) {
       String option = roleList[i];
	   %>
	   <option value="<%= option %>"><%= option %></option>
	   <% } %>
    </select><br>
    State: <select name="state" style="margin: 10px">
       <%  for(int i = 0; i < stateList.length; i++) {
       String option = stateList[i];
       %>
       <option value="<%= option %>"><%= option %></option>
       <% } %>
    </select><br>   
    <input type="submit" value="Create Account"/>
</form>

</body>
</html>