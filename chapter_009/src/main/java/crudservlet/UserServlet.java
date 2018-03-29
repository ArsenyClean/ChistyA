package crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserServlet extends HttpServlet {

    private final UserStore instance = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        String login = req.getParameter("login");
        User user = this.instance.get(login);
        if (user != null) {
            writer.append(user.toString());
        } else {
            writer.append("User not found ");
        }
        writer.flush();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        User user = new User(req.getParameter("name"), req.getParameter("login"),
                req.getParameter("email"));
        if (this.instance.add(user)) {
            writer.append("User added.. ");
        } else {
            writer.append("Added failed ");
        }
        writer.flush();
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (this.instance.update(new User(req.getParameter("name"), req.getParameter("login"),
                req.getParameter("email")))) {
            writer.append("User update.. ");
        } else {
            writer.append("Update failed ");
        }
        writer.flush();
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        if (this.instance.delete((req.getParameter("login")))) {
            writer.append("User deleted.. ");
        } else {
            writer.append("Del failed ");
        }
        writer.flush();
    }
}