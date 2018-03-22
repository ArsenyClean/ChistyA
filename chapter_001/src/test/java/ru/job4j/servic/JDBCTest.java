package ru.job4j.servic;

import org.junit.Test;
import ru.job4j.sql.JavaBeanDemo;

public class JDBCTest {

    @Test
    public void testJDBC() {
        String url = "jdbc:postgresql://localhost:5432/userstore";
        String username = "postgres";
        String password = "1234";
        JavaBeanDemo jdSql = new JavaBeanDemo(url, username, password, 100);
        jdSql.starter();
    }
}