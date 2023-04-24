package helpers;

import classes.Course;
import classes.Major;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReadPlans {
    public static String readPlan(HashMap<String, Major> majors, File f, Dictionary dictionary) throws IOException {
        // Initialize ArrayList, set up file and scanner.
        ArrayList<Course> courses = new ArrayList<>();
        String error = "";
        File file = null;
        try {
            file = f;
        }
        catch (Exception e) {
            System.out.println("ERROR: File does not exist");
            System.exit(1);
        }
        BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
        String read = reader.readLine();


        // Get the name of the Major
        if(read.equals(null)) {
            error = "ERROR: Empty .csv file. Path: " + file.getPath();
                return error;
        }

        // Grabs the file name, splitting on either a '-', '.', or ' '
        String[] splitPathName = file.getName().split("[-. ]"); // degree-plan - ESGL
        String name = "";

        // Grab the major code
        name = splitPathName[4];

        //Check that the name is contained within our Major codes hashmap, and add if it is. Otherwise, return a non-fatal error message.
        Major major = null;
        if(majors.containsKey(name)) {
            major = majors.get(name);
        }
        else{
            error = ("ERROR: Major code \"" + name + "\" not in system");
            System.out.println(error);
            return error;
        }

        /// Iterates through the remainder of the file, pulling valid courses associated with each major.
        String[] line = read.split(",");

        // removes trash lines
        while(!"Course ID".equals(line[0]) && read != null) {
            read = reader.readLine();
            line = read.split(",");
        }

        // iterates through the remaining file
        read = reader.readLine();
        while(read != null) {
            line = read.split(",");
            // if it is a valid course
            if(line.length > 10) {

                // if it is a term 1 course, add it to courses
                if("1".equals(line[10])) {
                    Course course = new Course(line[2], line[3]);
                    courses.add(course);
                }
            }
            read = reader.readLine();
        }
        major.setCourses(courses);
        reader.close();

        return error;
    }
}
