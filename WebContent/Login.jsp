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
<title>Login</title>
</head>
<body style="text-align: center;">
<p style="text-align: center;">
    <font size="6">
        Login
    </font>
</p>

<form action="Login" method="post">
    Full name: <input type="text" name="fname" style="margin: 10px"><br>
    <input type="submit" value="Login"/>    
    <input type="button" value="Sign Up" onClick="JavaScript:window.location='Signup.jsp';"/><br>
    <br><span class="failure" style="color: red">${failure}</span>
</form>

</body>
</html>
