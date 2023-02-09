package helpers;

import classes.Course;
import classes.Major;

import java.util.HashMap;

public class AggregateClassTotals {

    public static HashMap<String,Integer> AggregateClassTotals(HashMap<String, Major> majors) {
        HashMap<String,Integer> allCourses = new HashMap<>();
        //TODO: Errors with aggregation -- Derived from ELEC courses, unsure if can fix

        //look at each major
        for (String s : majors.keySet()) {
            Major major = majors.get(s);

            //for each course in the major, add to the enroll count
            major.setCourseTotals();

            //for each course in the major
            for (Course c : major.getCourses()) {
                // aggregate the name
                String name = c.getName() + " " + c.getNumber();
                if (allCourses.containsKey(name)) {
                    Integer temp = allCourses.get(name);
                    temp = temp + c.getEnrolled();
                    allCourses.replace(name, temp);
                }
                else {
                    allCourses.put(name, c.getEnrolled());
                }
            }
        }
        return allCourses;
    }
}
