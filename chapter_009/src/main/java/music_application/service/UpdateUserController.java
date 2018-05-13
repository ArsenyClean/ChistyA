package music_application.service;

import music_application.control.MusicStore;
import music_application.model.Address;
import music_application.model.MusicType;
import music_application.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UpdateUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Address userAddress = new Address(req.getParameter("country"),
                req.getParameter("city"), req.getParameter("street"),
                req.getParameter("house"), req.getParameter("flat"));
        List<MusicType> userMusicTypeList = new LinkedList<>();
        String[] musicTypes = {"ROCK", "RAP", "JAZZ"};
        Map<String, String[]> parameters = req.getParameterMap();
        String key;
        for(Map.Entry entry: parameters.entrySet()) {
            key = (String)entry.getKey();
            if (Arrays.asList(musicTypes).contains(key.toUpperCase())) {
                userMusicTypeList.add(MusicType.valueOf(key.toUpperCase()));
            }
        }
        MusicStore.getInstance().updateUser(req.getParameter("id"), req.getParameter("name"),
                req.getParameter("login"),
                req.getParameter("password"), userAddress, Role.valueOf(req.getParameter("role")),
                userMusicTypeList);
    }
}
