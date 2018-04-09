<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="post">
    <label>Name </label><input type="text" name="name"><br/>
    <label>Login </label><input type="text" name="login"><br/>
    <label>Email </label><input type="text" name="email"><br/>
    <label>Password </label><input type="text" name="password"><br/>
    <label>Role </label><input type="text" name="role"><br/>
    <br/>
    <input type="submit" value="Create ! "/>
</form>
</body>
</html>