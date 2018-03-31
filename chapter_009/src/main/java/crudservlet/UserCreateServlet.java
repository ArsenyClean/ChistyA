package crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCreateServlet extends HttpServlet {

    private UserStore instance;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        User user = new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"));
        instance.add(user);
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }

    @Override
    public void init() {
        DatePool datePool = new DatePool();
        instance = new UserStore(datePool.poolInit());
    }
}