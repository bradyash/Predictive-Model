public class Course {
    private String prefix;
    private String number;

    private int enrolled;

    public Course() {
        prefix = "";
        number = "";
        enrolled = 0;
    }

    public Course(String n) {
        prefix = n;
        number = "";
        enrolled = 0;
    }

    public Course(String n, String e) {
        prefix = n;
        number = e;
    }

    public String getName() {
        return prefix;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String n) {
        prefix = n;
    }

    public void setNumber(String a) {
        number = a;
    }

    public void setEnrolled(int a) {
        enrolled = a;
    }
    public Integer getEnrolled() {
        return enrolled;
    }
}
