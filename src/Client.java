import classes.Course;
import classes.Major;
import gui.*;
import helpers.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Client {
    public static void main(String[] args) throws IOException {
        //gui app = new gui();

        /*
        *
        * TODO: Change how major names are checked and stored
        * TODO: Majors not found issues
        *
        * */

        //Test Suite
        File majorCodes = new File("C:\\Users\\j11q863\\IdeaProjects\\Predictive-Model\\src\\files\\MajorsCodesNewStudentCounts202270 - MajorsCodes.csv");
        File directory = new File("C:\\Users\\j11q863\\IdeaProjects\\Predictive-Model\\src\\files\\Degree plan files");

        System.out.println("======================================");
        System.out.println("Dictionary Test");
        System.out.println("======================================");

        //Testing Dictionary Functionality and Output
        Dictionary dict = new Dictionary();
        dict.importDictionary(majorCodes);
        HashMap<String, ArrayList<String>> dictHash = dict.getDictionary();

        //      print out dict contents
        for (String s : dictHash.keySet()) {
            ArrayList<String> vals = dictHash.get(s);
            System.out.print("Key: " + s + " Value: ");
            for (String val : vals) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        System.out.println("======================================");
        System.out.println("ReadEnrolled.readCsv");
        System.out.println("======================================");

        //Testing importing majors based on the Dictionary
        HashMap<String, Major> majors = ReadEnrolled.readCsv(dict);
        for (String s : majors.keySet()) {
            Major temp = majors.get(s);
            System.out.println("Major Name: [" + temp.getName() + "] Enrolled: [" + temp.getEnrolled() + "]"    );
        }

        System.out.println("======================================");
        System.out.println("CollectFiles.java");
        System.out.println("======================================");

        //Testing CollectFiles.java
        ArrayList<File> plans = CollectFiles.collectFiles(directory);
        System.out.println("Size: " + plans.size());
        for (File plan : plans) {
            System.out.println(plan.getPath());
        }

        System.out.println("======================================");
        System.out.println("ReadPlans.java");
        System.out.println("======================================");

        //Testing ReadPlans.java
        for (File plan : plans) {
            ReadPlans.readPlan(majors, plan, dict);
        }
        ArrayList<Course> c = new ArrayList<>();
        for (String s : majors.keySet()) {
            c = majors.get(s).getCourses();
            System.out.println("Major: " + s + " Course count: " + c.size());
            for (int x = 0; x < c.size(); x++) {
                System.out.println("                     " + c.get(x).getName());
            }
        }

        //Testing aggregation of .csv and output
        HashMap<String,Integer> totals = AggregateClassTotals.AggregateClassTotals(majors);
        File csv = SendToCSV.outputByMajor(majors);

        if (csv.isFile()) {
            Desktop.getDesktop().open(csv);
        }

    }
}
