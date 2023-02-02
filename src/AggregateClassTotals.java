import java.util.ArrayList;
import java.util.HashMap;

public class AggregateClassTotals {

    public static HashMap<String,Integer> AggregateClassTotals(HashMap<String,Major> majors) {

        //System.out.println(major.size());

        String[][] totals = new String[89][2];
        HashMap<String,Integer> allCourses = new HashMap<>();
        //System.out.println(totals.length + " " + totals[0].length);

//        for (String[] total : totals) {
//            total[0] = "yo";
//            System.out.println();
//        }
        //TODO: Hashmap of courses

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
                    //System.out.println("-----------Temp: " + temp + " c.getEnrolled(): " + c.getEnrolled() );
                    temp = temp + c.getEnrolled();
                    //System.out.println("-----------NEW Temp: " + temp);
                    allCourses.replace(name, temp);
                }
                allCourses.put(name, c.getEnrolled());
            }
        }
        return allCourses;
    }
}
