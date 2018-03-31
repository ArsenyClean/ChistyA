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
<form action="<%=request.getContextPath()%>/create.jsp">
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
    <%  UserStore instance;
        DatePool datePool = new DatePool();
        instance = new UserStore(datePool.poolInit());
        for (User user : instance.getUsers()) {
    %>
    <tr>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getLogin()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getCreateDate()%>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/edit.jsp">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="Edit "/>
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/delete" method=post>
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="Delete "/>
            </form>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
