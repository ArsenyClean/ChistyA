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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        instance.update(Integer.parseInt(req.getParameter("id")), req.getParameter("name"),
                req.getParameter("login"), req.getParameter("email"));
        resp.sendRedirect(String.format("%s/index.jsp", req.getContextPath()));
    }

    @Override
    public void init() {
        DatePool datePool = new DatePool();
        instance = new UserStore(datePool.poolInit());
    }
}