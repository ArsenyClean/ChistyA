<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title></title>
</head>
<body>
<c:if test="${not empty requestScope.error}"/>
    <div style="background-color:red">
    <c:out value="${error}"/>
    </div>
<div class="container">
<form action="${pageContext.servletContext.contextPath}/signin" method="post" style="width: 300px">
    <label>Login: </label><br/>
    <input type="text" class="form-control" id="login" name="login" autofocus/><br/>
    <label>Password: </label><br/>
    <input type="password" class="form-control" id="pasword" name="password" autofocus/><br/>
    <input type="submit" value="Sign In" class="btn btn-success"/><br/>
</form>
</div>
</body>
</html>