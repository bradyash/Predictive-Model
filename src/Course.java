public class Course {
    private String prefix;
    private String number;

    public Course() {
        prefix = "";
        number = "";
    }

    public Course(String n) {
        prefix = n;
        number = "";
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
}
