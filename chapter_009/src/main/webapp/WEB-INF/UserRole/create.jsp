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
<form action="${pageContext.servletContext.contextPath}/create" method="post">
    <label>Name </label><input type="text" name="name" class=""><br/>
    <label>Login </label><input type="text" name="login"><br/>
    <label>Email </label><input type="text" name="email"><br/>
    <label>Password </label><input type="text" name="password"><br/>
    <label>Role </label><input type="text" name="role"><br/>
    <br/>
    <input type="submit" value="Create ! " class="btn btn-success"/>
</form>
</div>
</body>
</html>