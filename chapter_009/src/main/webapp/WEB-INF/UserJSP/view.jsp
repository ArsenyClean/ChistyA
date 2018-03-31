<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="crudservlet.UserStore" %>
<%@ page import="crudservlet.DatePool" %>
<%@ page import="crudservlet.User" %>

<%@ page contentType="text/html" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="get">
    <input type="submit" value="Create user! "/>
</form>
<table border="1">
    <tr>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>create date</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.name}"></c:out>
        </td>
        <td><c:out value="${user.login}"></c:out>
        </td>
        <td><c:out value="${user.email}"></c:out>
        </td>
        <td><c:out value="${user.createDate}"></c:out>
        </td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/edit" method=get>
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" value="Edit "/>
            </form>
        </td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/delete" method=post>
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" value="Delete "/>
            </form>
        </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
