package ru.job4j.sql;

import org.postgresql.util.PSQLState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class XMLConvert {

    private File file1 = new File(new File("").getAbsolutePath() + "\\1.xml");
    private File file2 = new File(new File("").getAbsolutePath() + "\\2.xml");
    private File fileXsl = new File(new File("").getAbsolutePath() + "\\xsl.xsl");
    private Logger log = LoggerFactory.getLogger(PSQLState.class);
    long summ = 0;

    public void convertXML() {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Source xslt = new StreamSource(fileXsl);
            Transformer transformer = tf.newTransformer(xslt);
            transformer.transform(new StreamSource(file1), new StreamResult(file2));
            try {
                file2.createNewFile();
                parse();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        } catch (TransformerException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void parse() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file2));
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                if (string.indexOf("<entry") >= 0) {
                    summ += getNumber(string);
                }
            }
            System.out.println(summ);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumber(String string) {
        char[] chars = string.toCharArray();
        boolean valueHere = false;
        String result = "";
        for (int i = 0; i < chars.length; i++) {
            if (valueHere) {
                int startInd = i;
                while (chars[i] != '\"') {
                    i++;
                }
                result = string.substring(startInd, i);
                break;
            }
            if (chars[i] == '\"') {
                valueHere = true;
            }
        }
        return Integer.parseInt(result);
    }
}