package store;

import store.DatePool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserDeleteServlet extends HttpServlet {

    private UserStore instance;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        if (role.equals("admin")) {
            instance.delete(Integer.valueOf(req.getParameter("id")));
            resp.sendRedirect(String.format("%s/admin", req.getContextPath()));
        } else {
            instance.delete(Integer.valueOf(req.getParameter("id")));
            resp.sendRedirect(String.format("%s/logout", req.getContextPath()));
        }
    }

    @Override
    public void init() {
        Settings settings = new Settings();
        DatePool datePool = new DatePool( settings.getValue("url"), settings.getValue("name"), settings.getValue("password"));
        instance = new UserStore(datePool.poolInit());
    }
}