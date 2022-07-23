package Logic;

public class Course {

    private String name;
    private CourseType type;
    private double credits;
    private int grade;

    public Course(String name, CourseType type, double credits, int grade) {
        this.name = name;
        this.type = type;
        this.credits = credits;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public CourseType getType() {
        return type;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
