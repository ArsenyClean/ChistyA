package crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

public class UserUpdateServlet extends HttpServlet {

    private UserStore instance;

    User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.user = instance.getById(Integer.parseInt(req.getParameter("id")));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>")
                .append("<head>")
                .append("<title></title>")
                .append("</head>")
                .append("<body>")
                .append(String.format("<form action = %s/edit method = post>", req.getContextPath()))
                .append(String.format("Name: <input type = 'text' name = 'name' value = '%s'/>", user.getName())).append("<br />")
                .append(String.format("Login: <input type = 'text' name = 'login' value = '%s'/>", user.getLogin())).append("<br />")
                .append(String.format("Email: <input type = 'text' name = 'email' value = '%s'/>", user.getEmail())).append("<br />").append("<br />")
                .append("<input type = 'submit' value = 'Edit'/>")
                .append("</form>")
                .append("</body>")
                .append("</html>");
        PrintWriter writer = resp.getWriter();
        writer.append(stringBuilder.toString());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        instance.update(user.getId(), req.getParameter("name"),
                req.getParameter("login"), req.getParameter("email"));
        resp.sendRedirect(req.getContextPath() + "/list");
    }

    @Override
    public void init() {
        DatePool datePool = new DatePool();
        instance = new UserStore(datePool.poolInit());
    }
}