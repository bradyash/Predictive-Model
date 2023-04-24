package helpers;

import classes.Course;
import classes.Major;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CSV_Output {
    public static File outputByClass(HashMap<String,Integer> courses) throws IOException {
        File file = null;
        FileWriter csvWriter = null;
        try {
            String localDir = System.getProperty("user.dir");
            file = new File(localDir + "\\out.csv");

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
        return file;
    }


    public static File outputByMajor(HashMap<String, Major> m) throws IOException {
        FileWriter csvWriter = null;
        File file = null;
        try {
            String localDir = System.getProperty("user.dir");
            file = new File(localDir + "\\out.csv");

            csvWriter = new FileWriter(file);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //builds .csv file from hashmap of Majors
        csvWriter.write("Class Totals by Major\n");
        for (String s : m.keySet()) {
            Major major = m.get(s);
            StringBuilder line = new StringBuilder();
            line.append("Major: " + s + "\n");
            for (Course c : major.getCourses()) {
                line.append(c.getName() + c.getNumber());
                line.append(',');
                line.append(c.getEnrolled() + "\n");
            }
            line.append("\n");
            csvWriter.write(line.toString());
        }
        csvWriter.close();
        return file;
    }
}
