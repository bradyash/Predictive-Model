import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Client {
    public static void main(String[] args) throws FileNotFoundException {
        HashMap<String, Major> majors = readEnrolled.readCsv();
        int total = 0;
        for (String s : majors.keySet()) {
            total += majors.get(s).getEnrolled();
        }
        System.out.println("Grand Total: " + total);

        ArrayList<Course> courses = ReadPlans.readPlan();
        for (Course cours : courses) {
            System.out.println(cours.getName() + " " + cours.getNumber());
        }
    }
}
