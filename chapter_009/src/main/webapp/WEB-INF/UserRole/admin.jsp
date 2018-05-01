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
<form action="${pageContext.servletContext.contextPath}/create" method="get" class="">
    <input type="submit" value="Create user! " class="btn btn-success"/>
</form>
<table class="table table-bordered">
    <tr>
        <th class="table-td">name</th>
        <th class="table-td">login</th>
        <th class="table-td">email</th>
        <th class="table-td">password</th>
        <th class="table-td">role</th>
        <th class="table-td">create date</th>
        <th class="table-td">Edit</th>
        <th class="table-td">Delete</th>
    </tr>
    <c:forEach items="${users}" var="us">
        <tr>
            <td><c:out value="${us.name}"></c:out>
            </td>
            <td><c:out value="${us.login}"></c:out>
            </td>
            <td><c:out value="${us.email}"></c:out>
            </td>
            <td><c:out value="${us.password}"></c:out>
            </td>
            <td><c:out value="${us.role}"></c:out>
            </td>
            <td><c:out value="${us.createDate}"></c:out>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/admin_edit" method=get>
                    <input type="hidden" name="id" value="${us.id}" class="btn btn-success"/>
                    <input type="submit"  class="btn btn-success" value="Edit "/>
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/delete" method=post>
                    <input type="hidden" name="id" value="${us.id}" class="btn btn-success"/>
                    <input type="hidden" name="role" value="${us.role}" class="btn btn-success"/>
                    <input type="submit" class="btn btn-success" value="Delete "/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="${pageContext.servletContext.contextPath}/logout" method="get">
    <input type="submit" class ="btn btn-success" value="LogOut"/>
</form>
</div>
</body>
</html>