import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class SendToCSV {
    public static void writeToCsv(String filePath, HashMap<String,Integer> courses) throws IOException {
        File file = null;
        FileWriter csvWriter = null;
        try {
            file = new File("src/out.csv");

            csvWriter = new FileWriter(file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // TODO: Add headers
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
