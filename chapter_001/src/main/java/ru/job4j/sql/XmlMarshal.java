package ru.job4j.sql;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class XmlMarshal {

    public void marshalXml(Connection conn) throws IOException {
        try {
            File file1 = new File(new File("").getAbsolutePath() + "\\1.xml");
            PreparedStatement pr = conn.prepareStatement("select field from test");
            ResultSet rs = pr.executeQuery();
            Entries entries = new Entries();
            List<Entry> list = new ArrayList<>();
            while (rs.next()) {
                Entry entry = new Entry();
                entry.setField(rs.getInt(1));
                list.add(entry);
            }
            entries.setField(list);
            JAXBContext context = JAXBContext.newInstance(Entries.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(entries, file1);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}