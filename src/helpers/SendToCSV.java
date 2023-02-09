package helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class SendToCSV {
    public static void writeToCsv(HashMap<String,Integer> courses) throws IOException {
        File file = null;
        FileWriter csvWriter = null;
        try {
            file = new File("src/output/out.csv");

            csvWriter = new FileWriter(file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //builds .csv file from hashmap of courses
        csvWriter.write("COURSE NAME & NUMBER,ESTIMATED LOAD\n");
        for (String s : courses.keySet()) {
            StringBuilder line = new StringBuilder();
            line.append(s);
            line.append(",");
            line.append(courses.get(s));
            line.append("\n");
            csvWriter.write(line.toString());
        }
        csvWriter.close();
    }
}
