package store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Admin extends HttpServlet {

    private UserStore instance;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", instance.getUsers());
        req.getRequestDispatcher("WEB-INF/UserRole/admin.jsp").forward(req, resp);
    }

    @Override
    public void init() {
        Settings settings = new Settings();
        DatePool datePool = new DatePool( settings.getValue("url"), settings.getValue("name"), settings.getValue("password"));
        instance = new UserStore(datePool.poolInit());
    }
}