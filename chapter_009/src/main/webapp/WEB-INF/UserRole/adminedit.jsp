<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<form action="${pageContext.servletContext.contextPath}/admin_edit" method="post">
    <c:set var="us" value="${user}"></c:set>
    <input type="hidden" name="id" value="${us.id}">
    <label>Name </label><input type="text" name="name" value="${us.name}"><br/>
    <label>Login </label><input type="text" name="login" value="${us.login}"><br/>
    <label>Email </label><input type="text" name="email" value="${us.email}"><br/>
    <label>Password </label><input type="text" name="password" value="${us.password}"><br/>
    <label>Role </label><input type="text" name="role" value="${us.role}"><br/>
    <br/>
    <input type="submit" value="Edit it " class="btn btn-success"/>
</form>
</div>
</body>
</html>