package store;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminEditUser  extends HttpServlet {

    private UserStore instance;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", instance.getById(Integer.parseInt(req.getParameter("id"))));
        req.getRequestDispatcher("WEB-INF/UserRole/adminedit.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer integer = Integer.parseInt(req.getParameter("id"));
        instance.update(integer, req.getParameter("name"), req.getParameter("login"),
                req.getParameter("email"), req.getParameter("password"), req.getParameter("role"));
        resp.sendRedirect(req.getContextPath() + "/admin");
    }

    @Override
    public void init() {
        Settings settings = new Settings();
        DatePool datePool = new DatePool( settings.getValue("url"), settings.getValue("name"), settings.getValue("password"));
        instance = new UserStore(datePool.poolInit());
    }
}