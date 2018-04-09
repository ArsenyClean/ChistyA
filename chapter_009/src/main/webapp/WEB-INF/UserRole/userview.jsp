<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<table border="1">
    <tr>
        <th>name</th>
        <th>email</th>
        <th>role</th>
        <th>create date</th>
        <th>login</th>
        <th>password</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${users}" var="users">
    <tr>
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
                    <input type="submit" value="Edit"/>
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/delete" method=post>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="hidden" name="role" value="${user.role}"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </c:if>
    </tr>
    </c:forEach>
</table>
<form action="${pageContext.servletContext.contextPath}/logout" method="get">
    <input type="submit" value="LogOut">
</form>
</body>
</html>
