package store;

import store.DatePool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignIn extends HttpServlet {

    private UserStore instance;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/UserRole/signin.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = instance.findUserLogPassw(login, password);
        HttpSession session = req.getSession();
        if (user != null) {
            if (user.getRole().equals("admin")) {
                session.setAttribute("user", user);
                resp.sendRedirect(String.format("%s/admin", req.getContextPath()));
            } else {
                if (user.getRole().equals("user")) {
                    session.setAttribute("user", user);
                    resp.sendRedirect(String.format("%s/user", req.getContextPath()));
                }
            }
        } else {
            req.setAttribute("error", "Credential invalid!");
            doGet(req, resp);
        }
    }

    @Override
    public void init() {
        Settings settings = new Settings();
        String url = settings.getValue("url");
        String name = settings.getValue("name");
        String password = settings.getValue("password");
        DatePool datePool = new DatePool(settings.getValue("url"), settings.getValue("name"), settings.getValue("password"));
        instance = new UserStore(datePool.poolInit());
    }
}