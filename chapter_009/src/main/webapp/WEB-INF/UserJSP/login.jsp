<%--
  Created by IntelliJ IDEA.
  User: Java-developer
  Date: 01.04.2018
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    <label>Login </label><input type="text" name="login"><br/>
    <label>Password </label><input type="password" name="password"><br/>
    <br/>
    <input type="submit" value="Log in ! "/>
</form>
</body>
</html>
