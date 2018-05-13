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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CreateUserController extends HttpServlet {

    private final String[] musicTypesArray = {"ROCK", "RAP", "JAZZ"};
    private final List<String> musicTypes = Arrays.asList(this.musicTypesArray);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("role") != null) {
            List<User> users = MusicStore.getInstance().getAllUsers();
            String json = new Gson().toJson(users);
            resp.setContentType("text/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(json);
            writer.flush();
        } else {
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Address userAddress = new Address(req.getParameter("country"),
                                        req.getParameter("city"), req.getParameter("street"),
                                        req.getParameter("house"), req.getParameter("flat"));
        List<MusicType> userMusicTypeList = new LinkedList<>();
        Map<String, String[]> parameters = req.getParameterMap();
        String key;
        for(Map.Entry entry: parameters.entrySet()) {
            key = (String)entry.getKey();
            if (this.musicTypes.contains(key.toUpperCase())) {
                userMusicTypeList.add(MusicType.valueOf(key.toUpperCase()));
            }
        }
        MusicStore.getInstance().createUser(req.getParameter("name"),
                req.getParameter("login"), req.getParameter("password"), userAddress,
                Role.valueOf(req.getParameter("role")), userMusicTypeList);
    }
}
