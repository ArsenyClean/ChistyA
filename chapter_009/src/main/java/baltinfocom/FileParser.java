package baltinfocom;

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

        List<List<Long>> lists = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int endFlag = 1000000;
            int i = 0;
            while ((line = reader.readLine()) != null && i <= endFlag) {
                List<Long> numbers = lineParser(line);
                lists.add(numbers);
                i++;
            }
        } catch (FileNotFoundException e) {
            LOG.error(e.getMessage(), e);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
//        showData(lists);
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
                        Long longNumber = Double.valueOf(words[i]).longValue();
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