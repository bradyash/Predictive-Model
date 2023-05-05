package helpers;

import classes.Course;
import classes.Major;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class ProportionCalculation {

    public static HashMap<String, Major> proportionCalculation(HashMap<String, Major> m, File f) throws IOException {
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
        String[] majorsFromHeader = read.split(",");
        HashMap<Integer, Major> hashFromHeaders = new HashMap<>();

        //Key each major to an index
        for (int i = 0; i < majorsFromHeader.length; i++) {
            String s = majorsFromHeader[i];
            if (m.containsKey(s)) {
                Major temp = m.get(s);
                hashFromHeaders.put(i, temp);
            }
        }

        //Run through the file, checking for non-zero proportion, and adding classes if not already in the system.
        read = reader.readLine();
        while(read != null) {
            String[] classProportions = read.split(",");
            String courseName = classProportions[0];
            for (int i = 0; i < classProportions.length; i++) {
                String proportion = classProportions[i];
                Double parsedProportion = Double.parseDouble(proportion);
                if (parsedProportion != 0) {
                    Major temp = hashFromHeaders.get(i);
                    ArrayList<Course> coursesForIndexedMajor = temp.getCourses();

                    // Check if the course is already in the arraylist, and if so change the proportion
                    Boolean inArray = false;
                    for (Course course : coursesForIndexedMajor) {
                        String cName = course.getName() + " " + course.getNumber();
                        if (cName.equals(courseName)) {
                            inArray = true;
                            Integer nCT = (int) (course.getEnrolled() * parsedProportion);
                            course.setEnrolled(nCT);
                        }
                    }
                    if (!inArray) {
                        String[] newCourse = courseName.split(" ");
                        Course tempCourse = new Course(newCourse[0], newCourse[1]);
                        coursesForIndexedMajor.add(tempCourse);
                    }

                    temp.setCourses(coursesForIndexedMajor);
                    hashFromHeaders.replace(i, temp);
                }
            }
        }

        // Map the hashmaps together

        for (Integer integer : hashFromHeaders.keySet()) {
            Major temp = hashFromHeaders.get(integer);
            if (m.containsKey(temp.getName())) {
                m.replace(temp.getName(), temp);
            }
        }

        return m;
    }

}
