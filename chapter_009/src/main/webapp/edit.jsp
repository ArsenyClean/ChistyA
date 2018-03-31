<%@ page import="crudservlet.UserStore" %>
<%@ page import="crudservlet.DatePool" %>
<%@ page import="crudservlet.User" %><%--
  Created by IntelliJ IDEA.
  User: Java-developer
  Date: 30.03.2018
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user in the database</title>
</head>
<body>
<% UserStore instance;
    DatePool datePool = new DatePool();
    instance = new UserStore(datePool.poolInit());
    User user = instance.getById(Integer.parseInt(request.getParameter("id")));
%>
<form action="<%=request.getContextPath()%>/edit" method="post">
    <input type='hidden' name="id" value="<%=user.getId()%>">
    <label>Name </label><input type="text" name="name" value="<%=user.getName()%>"><br/>
    <label>Login </label><input type="text" name="login" value="<%=user.getLogin()%>"><br/>
    <label>Email </label><input type="text" name="email" value="<%=user.getEmail()%>"><br/>
    <br/>
    <input type="submit" value="Edit it "/>
</form>
</body>
</html>