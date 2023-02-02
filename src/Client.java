import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Client {
    public static void main(String[] args) throws IOException {
        //long time1 = System.currentTimeMillis();
        HashMap<String, Major> majors = ReadEnrolled.readCsv();
        //System.out.println("Run Time of readCsv: " + (System.currentTimeMillis() - time1));
//        long time2 = System.currentTimeMillis();
//        ArrayList<Course> courses = ReadPlans.readPlan(majors);
//        System.out.println("Run Time of readPlan: " + (System.currentTimeMillis() - time2));

        //System.out.println("   ---   ---   ---   ---   ---   ---   ---   ---");
        //long time3 = System.currentTimeMillis();
        ArrayList<File> allFiles = collectFiles.collectFiles("C:\\Users\\brady\\Downloads\\Degree plan files-20230202T170512Z-001\\Degree plan files");
        //System.out.println("Run time of collectFiles: " + (System.currentTimeMillis() - time3));

        for (File file : allFiles) {
            ArrayList<Course> courses1 = ReadPlans.readPlan(majors, file);
        }

        int total = 0;
        for (String s : majors.keySet()) {
            total += majors.get(s).getEnrolled();
            //System.out.println(majors.get(s).getName() + " " + majors.get(s).getEnrolled());
//            for (Course cours : majors.get(s).getCourses()) {
//                //System.out.println("   " + cours.getName() + " " + cours.getNumber());
//            }
        }
        System.out.println("Grand Total: " + total);
        //System.out.println(majors.size());

        HashMap<String,Integer> totals = AggregateClassTotals.AggregateClassTotals(majors);
//        for (String s : totals.keySet()) {
//            Integer enrollCount = totals.get(s);
//            System.out.println(s + " " + enrollCount);
//        }
        SendToCSV.writeToCsv("", totals);
    }
}
