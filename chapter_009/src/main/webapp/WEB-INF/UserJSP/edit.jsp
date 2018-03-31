<%@ page import="crudservlet.UserStore" %>
<%@ page import="crudservlet.DatePool" %>
<%@ page import="crudservlet.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Java-developer
  Date: 30.03.2018
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/edit" method="post">
    <c:set var="user" value="${user}"></c:set>
    <input type='hidden' name="id" value="${user.id}">
    <label>Name </label><input type="text" name="name" value="${user.name}"><br/>
    <label>Login </label><input type="text" name="login" value="${user.login}"><br/>
    <label>Email </label><input type="text" name="email" value="${user.email}"><br/>
    <br/>
    <input type="submit" value="Edit it "/>

</form>
</body>
</html>