package BaltInfoComApplication;

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
        long time = System.currentTimeMillis();
        System.out.println("Start parsing file" );
        FileParser fileParser = new FileParser(firstFilePath);
        List<List<Long>> lists = fileParser.fileparse();
        System.out.println("End parsing file with time= " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        Groups groups = new Groups(lists);
        System.out.println("Start delete dublicates. Start size of list= " + lists.size());
        lists = groups.deleteDublic();
        System.out.println("End delete dublicates with time= " + (System.currentTimeMillis() - time) + " Final list size= "  + lists.size());
        System.out.println("Start grouping file" );
        time = System.currentTimeMillis();
        String string = groups.makeGroups();
        System.out.println("End grouping with time= " + (System.currentTimeMillis() - time));
//        System.out.println(string);
        try {
            FileWriter printWriter = new FileWriter(finalFilePath);
            printWriter.append(string);
            printWriter.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}