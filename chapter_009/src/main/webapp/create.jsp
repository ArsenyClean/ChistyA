<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="<%=request.getContextPath()%>/create" method="post">
    <label>Name </label><input type="text" name="name"><br/>
    <label>Login </label><input type="text" name="login"><br/>
    <label>Email </label><input type="text" name="email"><br/>
    <br/>
    <input type="submit" value="Create ! "/>
</form>
</body>
</html>