package ru.job4j.vacancyparser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class VacanciesToBase {

    public VacanciesToBase(List<Vacancy> list) {
        this.list = list;
    }

    private List<Vacancy> list;
    private String url = "jdbc:postgresql://localhost:5432/userstore";
    private String username = "postgres";
    private String password = "1234";

    private Logger log = LoggerFactory.getLogger(VacanciesToBase.class);

    public void makeBase() {

        Connection conn = null;
        PreparedStatement st;
        ResultSet rs;
        try {
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);
            rs = conn.getMetaData().getTables(null, null, "vacancies", null);
            if (rs.next()) {
                st = conn.prepareStatement("TRUNCATE TABLE vacancies");
                st.executeUpdate();
            } else {
                st = conn.prepareStatement("create table vacancies (id serial PRIMARY KEY, textOfVacancy text, body text, dateOf text);");
                st.execute();
            }
            this.insertVacancies(conn);
            conn.commit();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private void insertVacancies(Connection conn) {
        try {
            PreparedStatement pr = conn.prepareStatement(" insert into vacancies (id, textOfVacancy, body, dateOf) values (?,?,?,?);");
            for (int i = 1; i <= list.size(); i++) {
                pr.setInt(1, i);
                pr.setString(2, list.get(i - 1).text);
                pr.setString(3, list.get(i - 1).body);
                pr.setString(4, list.get(i - 1).date.toString());
                pr.addBatch();
            }
            pr.executeBatch();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}