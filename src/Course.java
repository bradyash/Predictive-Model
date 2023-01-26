public class Course {
    private String name;
    private Integer number;

    public Course() {
        name = "";
        number = 0;
    }

    public Course(String n) {
        name = n;
        number = 0;
    }

    public Course(String n, int e) {
        name = n;
        number = e;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setName(String n) {
        name = n;
    }

    public void setNumber(int a) {
        number = a;
    }
}
