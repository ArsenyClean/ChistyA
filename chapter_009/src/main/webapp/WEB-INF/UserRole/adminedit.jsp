<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/admin_edit" method="post">
    <c:set var="us" value="${user}"></c:set>
    <input type="hidden" name="id" value="${us.id}">
    <label>Name </label><input type="text" name="name" value="${us.name}"><br/>
    <label>Login </label><input type="text" name="login" value="${us.login}"><br/>
    <label>Email </label><input type="text" name="email" value="${us.email}"><br/>
    <label>Password </label><input type="text" name="password" value="${us.password}"><br/>
    <label>Role </label><input type="text" name="role" value="${us.role}"><br/>
    <br/>
    <input type="submit" value="Edit it "/>
</form>
</body>
</html>