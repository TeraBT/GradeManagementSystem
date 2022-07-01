public class Course {

    private CourseType type;
    private String name;
    private double credits;
    private int grade;

    public Course(CourseType type, String name, int grade) {
        this.type = type;
        this.name = name;
        this.credits = credits;
        this.grade = grade;
    }

    public double getCredits() {
        return credits;
    }

    public int getGrade() {
        return grade;
    }
}
