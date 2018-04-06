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

    public List<List<Long>> fileparse() {
        List<List<Long>>lists = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            Integer endFlag = 200000000;
            Long counter = new Long(0);
            while ((line = reader.readLine()) != null && counter < endFlag) {
                counter++;
                line = line.replace("\"", "");
                List<Long> numbers = lineParser(line);
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

    private List<Long> lineParser(String line) {
        String[] words = line.split(";");
        List<Long> numbers = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            if (i >= words.length) {
                numbers.add(null);
            } else {
                if (!words[i].equals("")) {
                    try {
                        Double doible = Double.valueOf(words[i]) * 100;
                        Long longNumber = doible.longValue();
                        numbers.add(longNumber);
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

    public void showData(List<List<Long>> data) {
        for (List<Long> dataInt : data) {
            for (int j = 0; j < dataInt.size(); j++) {
                Long integer = dataInt.get(j);
                System.out.print(integer + " ");
            }
            System.out.println("");
        }
    }
}