<%--
  Created by IntelliJ IDEA.
  User: Java-developer
  Date: 01.04.2018
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title></title>
</head>
<body>
<div class="container">
<form action="${pageContext.servletContext.contextPath}/user_edit" method="post">
    <c:set var="us" value="${user}"/>
    <input type='hidden' name="id" value="${us.id}">
    <label>Name </label><input type="text" name="name" value="${us.name}"><br/>
    <label>Login </label><input type="text" name="login" value="${us.login}"><br/>
    <label>Email </label><input type="text" name="email" value="${us.email}"><br/>
    <label>Password </label><input type="text" name="password" value="${us.password}"><br/>
    <input type='hidden' name="role" value="${us.role}"><br/>
<input type="submit" value="Edit it " class="btn btn-success"/>
</form>
</div>
</body>
</html>