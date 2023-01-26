import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadPlans {
    public static ArrayList<Course> readPlan() throws FileNotFoundException {
        // Initialize ArrayList, set up file and scanner.
        ArrayList<Course> courses = new ArrayList<>();
        File file = new File("C:\\Users\\brady\\IdeaProjects\\Provost Office\\src\\degree-plan - BUAC.csv");
        Scanner read = new Scanner(file);
        String[] line = read.nextLine().split(",");

        // removes trash lines
        while(!"Course ID".equals(line[0]) && read.hasNextLine()) {
            line = read.nextLine().split(",");
        }

        // iterates through the remaining file
        while(read.hasNext()) {
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

        return courses;
    }
}
