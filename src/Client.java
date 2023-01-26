import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Client {
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, Major> majors = readEnrolled.readCsv();
        ArrayList<Course> courses = ReadPlans.readPlan(majors);


        int total = 0;
        for (String s : majors.keySet()) {
            total += majors.get(s).getEnrolled();
            System.out.println(majors.get(s).getName());
            for (Course cours : majors.get(s).getCourses()) {
                System.out.println("   " + cours.getName() + " " + cours.getNumber());
            }
        }
        System.out.println("Grand Total: " + total);

//        for (Course cours : courses) {
//            System.out.println(cours.getName() + " " + cours.getNumber());
//        }


    }
}
