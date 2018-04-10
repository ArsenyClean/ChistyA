package BaltInfoComApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    String filePath;

    public FileParser(String filePath) {
        this.filePath = filePath;
    }

    private Logger LOG = LoggerFactory.getLogger(FileParser.class);

    public List<List<String>> fileparse() {
        List<List<String>>lists = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            Integer endFlag = 1000000;
            Long counter = new Long(0);
            while ((line = reader.readLine()) != null && counter < endFlag) {
                counter++;
                line = line.replace("\"", "");
                List<String> numbers = lineParser(line);
                if (numbers != null) {
                    lists.add(numbers);
                }
                if (counter % 100000 == 0) {
                    System.out.println(counter + " lines of file were cheked.. ");
                }
            }
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
// showData(lists);
        return lists;
}

    private List<String> lineParser(String line) {
        String[] words = line.split(";");
        List<String> numbers = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            if (i >= words.length) {
                numbers.add(null);
            } else {
                if (!words[i].equals("")) {
                    try {
                        String string = words[i];
                        numbers.add(string);
                    } catch (NumberFormatException e) {
                        LOG.info("Some NumberFormatException was catched!" + e.getMessage());
                        numbers.add(null);
                    }
                } else {
                    numbers.add(null);
                }
            }
        }
        Integer i = 0;
        for (int j = 0; j < 3; j++) {
            if (numbers.get(j) == null) {
                i++;
            }
        }
        if (i == 3) {
            numbers = null;
        }
        return numbers;
    }

    public void showData(List<List<String>> data) {
        for (List<String> dataInt : data) {
            for (int j = 0; j < dataInt.size(); j++) {
                String integer = dataInt.get(j);
                System.out.print(integer + " ");
            }
            System.out.println("");
        }
    }
}