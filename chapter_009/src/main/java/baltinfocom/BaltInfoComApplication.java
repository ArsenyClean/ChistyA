package baltinfocom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BaltInfoComApplication {

    String firstFilePath;
    String finalFilePath;

    private Logger LOG = LoggerFactory.getLogger(BaltInfoComApplication.class);

    public BaltInfoComApplication(String firstFilePath, String finalFilePath) {
        this.firstFilePath = firstFilePath;
        this.finalFilePath = finalFilePath;
    }

    public void start() {
        FileParser fileParser = new FileParser(firstFilePath);
        List<List<Long>> lists = fileParser.fileparse();
        Groups groups = new Groups(lists);
        String string = groups.makeGroups();
        System.out.println(string);
        try {
            FileWriter printWriter = new FileWriter(finalFilePath);
            printWriter.append(string);
            printWriter.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}