<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<table class="table table-bordered">
    <tr>
        <th class="table-td">name</th>
        <th class="table-td">email</th>
        <th class="table-td">role</th>
        <th class="table-td">create date</th>
        <th class="table-td">login</th>
        <th class="table-td">password</th>
        <th class="table-td">Edit</th>
        <th class="table-td">Delete</th>
    </tr>
    <c:forEach items="${users}" var="users">
    <tr>
        <c:set value="${user}" var="user"/>
        <td><c:out value="${users.name}"></c:out></td>
        <td><c:out value="${users.email}"></c:out></td>
        <td><c:out value="${users.role}"></c:out></td>
        <td><c:out value="${users.createDate}"></c:out></td>
        <c:if test="${user.id == users.id}">
            <td><c:out value="${users.login}"></c:out></td>
            <td><c:out value="${users.password}"></c:out></td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/user_edit" method=get>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="Edit" class="bn btn-success"/>
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/delete" method=post>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="hidden" name="role" value="${user.role}"/>
                    <input type="submit" value="Delete" class="bn btn-success"/>
                </form>
            </td>
        </c:if>
    </tr>
    </c:forEach>
</table>
<form action="${pageContext.servletContext.contextPath}/logout" method="get">
    <input type="submit" value="LogOut" class="bn btn-success">
</form>
</div>
</body>
</html>
