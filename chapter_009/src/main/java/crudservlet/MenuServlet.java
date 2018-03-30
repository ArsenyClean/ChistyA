package crudservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MenuServlet extends HttpServlet {

    private UserStore instance;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>")
                .append("<head>")
                .append("<title></title>").append("</head>")
                .append("<body>").append(String.format("<form action = %s/create method = get>", req.getContextPath()))
                .append("<input type = 'submit' value = 'Add user'/>").append("</form>").append("<br/>")
                .append("<table border = '1'>")
                .append("<tr>")
                .append("<th>name</th>")
                .append("<th>login</th>")
                .append("<th>email</th>")
                .append("<th>create_date</th>")
                .append("<th>Edit</th>")
                .append("<th>Delete</th>");
        for (User user : instance.getUsers()) {
            stringBuilder.append("<tr>")
                    .append(String.format("<td>%s</td>", user.getName()))
                    .append(String.format("<td>%s</td>", user.getLogin()))
                    .append(String.format("<td>%s</td>", user.getEmail()))
                    .append(String.format("<td>%s</td>", user.getCreateDate()))
                    .append(String.format("<td>%s</td>", String.format("<form action=%s/edit method=get><input type='hidden' name='id' value='%s'/>%s",
                            req.getContextPath(), user.getId(), "<input type='submit' value='Edit'/></form>")))
                    .append(String.format("<td>%s</td>", String.format("<form action=%s/delete method=post><input type='hidden' name='id' value='%s'/>%s",
                            req.getContextPath(), user.getId(), "<input type='submit' value='Delete'/></form>")))
                    .append("</tr>");
        }
        stringBuilder.append("</table></body></html>");
        PrintWriter printWriter = resp.getWriter();
        printWriter.append(stringBuilder.toString());
        printWriter.flush();
    }


    @Override
    public void init() {
        DatePool datePool = new DatePool();
        instance = new UserStore(datePool.poolInit());
    }
}