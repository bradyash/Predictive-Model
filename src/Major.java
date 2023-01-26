import java.util.ArrayList;

public class Major {
    private String name;
    private ArrayList<Course> courses = new ArrayList<>();
    private Integer enrolled;

    public Major() {
        name = "";
        enrolled = 0;
    }

    public Major(String n) {
        name = n;
        enrolled = 0;
    }

    public Major(String n, int e) {
        name = n;
        enrolled = e;
    }

    public String getName() {
        return name;
    }

    public Integer getEnrolled() {
        return enrolled;
    }

    public void setName(String s) {
        name = s;
    }

    public void setEnrolled(int e) {
        enrolled = e;
    }
}
