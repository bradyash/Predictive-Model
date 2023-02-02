package helpers;

import classes.Course;
import classes.Major;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReadPlans {
    public static ArrayList<Course> readPlan(HashMap<String, Major> majors, File f) throws IOException {
        // Initialize ArrayList, set up file and scanner.
        ArrayList<Course> courses = new ArrayList<>();
        File file = null;
        try {
            file = f;
        }
        catch (Exception e) {
            System.out.println("ERROR: File does not exist");
            System.exit(1);
        }
        //Desktop.getDesktop().open(file);
        Scanner read = new Scanner(file);

        // Get the name of the classes.Major TODO: Why won't it open some files??
        if(!read.hasNextLine()) {
            System.out.println("ERROR: Empty .csv file. Path: " + file.getPath());
            System.out.println();
            return null;
        }

        // TODO: Create a dictionary for majors??
        String[] line = read.nextLine().split(",");
        String[]temp = line[1].split("-"); // this line is an issue
        String majorName = temp[0].strip();
        if ('"' == majorName.charAt(0)) {
            majorName = majorName.substring(1);
        }
        if("K".equals(majorName.substring(majorName.length()-1))) {
            char[] secondString = temp[1].strip().toCharArray();
            String number = "";
            if(Character.isDigit(secondString[0])) {
                number = number + secondString[0];
                if(secondString.length > 1 && Character.isDigit(secondString[1])) {
                    number = number + secondString[1];
                }
            }
            if(!number.equals("")) {
                majorName = majorName + "-" + number;
            }
        }
        if(majorName.contains("Computer Science")) {
            majorName = "Computer Science";
        }
        if(majorName.contains("Cell Biology and Neuroscience")) {
            majorName = "Cell Biology and Neuroscience";
        }
        if(majorName.contains("Integrated Lens")) {
            majorName = "Film and Photography";
        }
        majorName = majorName.replaceAll("[^a-zA-Z0-9& -]", "");
        //System.out.println(majorName);

        // Initialize the major, and find it in our Hashmap
        Major major = null;
        if(majors.containsKey(majorName)) {
            major = majors.get(majorName);
        }
        // If not, there must be an error, and exit. -- THIS MIGHT BE WRONG
        else{
            System.out.println("ERROR: classes.Major \"" + majorName + "\" not in system");
            System.out.println("------------PATH : " + file.getPath());
            System.out.println();
            return null;
        }

        // removes trash lines
        while(!"Course ID".equals(line[0]) && read.hasNextLine()) {
            line = read.nextLine().split(",");
        }

        // iterates through the remaining file
        while(read.hasNextLine()) {
            line = read.nextLine().split(",");
            // if it is a valid course
            if(line.length > 10) {

                // if it is a term 1 course, add it to courses
                if("1".equals(line[10])) {
                    Course course = new Course(line[2], line[3]);
                    courses.add(course);
                }
            }
        }
        major.setCourses(courses);
        read.close();

        return courses;
    }
}
