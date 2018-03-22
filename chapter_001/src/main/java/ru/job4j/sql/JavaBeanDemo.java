package ru.job4j.sql;

import org.postgresql.util.PSQLState;
import java.io.IOException;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * XSL Stylesheet
 * <xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:template match="/">
 <entries>\n
 <xsl:apply-templates select="/entries/entry"/>
 </entries>
 </xsl:template>
 <xsl:template match="entry">
 <xsl:element name="entry">
 <xsl:attribute name="field">
 <xsl:value-of select="field"/>
 </xsl:attribute>
 </xsl:element>\n
 </xsl:template>
 </xsl:stylesheet>
 */

/**
 * JavaDemoBean заание  XML XSLT JDBC Оптимизация [#20459][#45366]
 * @author Chisty Arseny
 * @since 22.03.2018
 * @version 1.0
 */
public class JavaBeanDemo {

    public JavaBeanDemo(String url, String username, String password, int n) {
        this.n = n;
        this.url = url;
        this.password = password;
        this.username = username;
    }

    private int n;
    private String url;
    private String username;
    private String password;
    private static final Logger Log = LoggerFactory.getLogger(PSQLState.class);

    public void starter() {
        Connection conn = null;
        PreparedStatement st;
        ResultSet rs;
        try {
            conn = DriverManager.getConnection(url, username, password);
            conn.setAutoCommit(false);
            rs = conn.getMetaData().getTables(null, null, "test", null);
            if (rs.next()) {
                st = conn.prepareStatement("TRUNCATE TABLE test");
                st.executeUpdate();
            } else {
                st = conn.prepareStatement("create table test (field integer)");
                st.execute();
            }
            this.insertN(conn);
            conn.commit();
            XmlMarshal xmlMarshal = new XmlMarshal();
            xmlMarshal.marshalXml(conn);
            XMLConvert convert = new XMLConvert();
            convert.convertXML();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
            }
        }
    }

    private void insertN(Connection conn) throws SQLException {
        try {
            PreparedStatement pr = conn.prepareStatement("INSERT INTO test(field) VALUES (?);");
            for (int i = 1; i <= n; i++) {
                pr.setInt(1, i);
                pr.addBatch();
            }
            pr.executeBatch();
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}