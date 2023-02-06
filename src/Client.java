import classes.Course;
import classes.Major;
import helpers.*;
import gui.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Client {
    public static void main(String[] args) throws IOException {
        HashMap<String, Major> majors = ReadEnrolled.readCsv();

        ArrayList<File> allFiles = CollectFiles.collectFiles("C:\\Users\\brady\\Downloads\\OneDrive_2023-01-25");

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

        gui.user_gui();
    }
}
