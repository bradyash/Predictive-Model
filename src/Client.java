import classes.Course;
import classes.Major;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Client {
    public static void main(String[] args) throws IOException {
        HashMap<String, Major> majors = ReadEnrolled.readCsv();

        ArrayList<File> allFiles = collectFiles.collectFiles("C:\\Users\\brady\\Downloads\\Degree plan files-20230202T170512Z-001\\Degree plan files");

        for (File file : allFiles) {
            ArrayList<Course> courses1 = ReadPlans.readPlan(majors, file);
        }

        int total = 0;
        for (String s : majors.keySet()) {
            total += majors.get(s).getEnrolled();
        }
        System.out.println("Grand Total: " + total);

        HashMap<String,Integer> totals = AggregateClassTotals.AggregateClassTotals(majors);

        SendToCSV.writeToCsv("", totals);
        //gui.gui();
    }
}
