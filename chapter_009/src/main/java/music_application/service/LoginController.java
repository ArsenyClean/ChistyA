package music_application.service;

import com.google.gson.Gson;
import music_application.control.MusicStore;
import music_application.model.Address;
import music_application.model.MusicType;
import music_application.model.Role;
import music_application.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class LoginController extends HttpServlet {

    private final MusicStore manager = MusicStore.getInstance();

    private boolean initialUsersAreCreated = false;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!this.initialUsersAreCreated) {
            this.createInitialUsers();
            this.initialUsersAreCreated = true;
        }
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        User user = this.manager.getUserIdByLoginAndPassword(req.getParameter("login"),
                req.getParameter("password"));
        if (user != null) {
            HttpSession session = req.getSession();
            if (user.getRole().equals(Role.USER)){
                writer.append(new Gson().toJson("user"));
                session.setAttribute("role", "user");
            } else if (user.getRole().equals(Role.MANDATOR)) {
                writer.append(new Gson().toJson("mandator"));
                session.setAttribute("role", "mandator");
            } else {
                writer.append(new Gson().toJson("admin"));
                session.setAttribute("role", "admin");
            }
        } else {
            writer.append(new Gson().toJson("Wrong user! Try again."));
        }
        writer.flush();
    }

    private void createInitialUsers() {
        Address user1Address = new Address( "Russia",
                "Moskow", "Pushkina", "15", "176");
        Address user2Address = new Address( "Belorus",
                "Minsk", "Lukashenko", "17/7", "88");
        Address user3Address = new Address( "Russia",
                "Smolensk", "Putina", "1", "8");
        List<MusicType> user1MusicTypeList = new LinkedList<>();
        List<MusicType> user2MusicTypeList = new LinkedList<>();
        List<MusicType> user3MusicTypeList = new LinkedList<>();
        user1MusicTypeList.add(MusicType.JAZZ);
        user1MusicTypeList.add(MusicType.ROCK);
        user1MusicTypeList.add(MusicType.RAP);
        this.manager.createUser("Ivan",  "1",
                "1", user1Address, Role.ADMIN, user1MusicTypeList);
        this.manager.createUser("Sergey",  "2",
                "2", user2Address, Role.MANDATOR, user2MusicTypeList);
        this.manager.createUser("Yuriy",  "3",
                "3", user3Address, Role.USER, user3MusicTypeList);
    }
}
