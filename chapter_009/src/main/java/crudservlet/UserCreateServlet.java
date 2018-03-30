package crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserCreateServlet extends HttpServlet {

    private UserStore instance;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>")
                .append("<head>")
                .append("<title></title>")
                .append("<meta charset = utf-8>")
                .append("<h1>Add new user to database</h1>")
                .append("</head>")
                .append("<body>")
                .append(String.format("<form action = %s/create method = post>", req.getContextPath()))
                .append("Name: <input type = 'text' name = 'name'/>").append("<br />")
                .append("Login: <input type = 'text' name = 'login'/>").append("<br />")
                .append("Email: <input type = 'text' name = 'email'/>").append("<br />").append("<br/>")
                .append("<input type = 'submit' value = 'Add user'/>")
                .append("</form>")
                .append("</body>")
                .append("</html>");
        PrintWriter writer = resp.getWriter();
        writer.append(stringBuilder.toString());
        writer.flush();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int id = instance.add(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email")));
        resp.sendRedirect(req.getContextPath() + "/list");
    }

    @Override
    public void init() {
        DatePool datePool = new DatePool();
        instance = new UserStore(datePool.poolInit());
    }
}