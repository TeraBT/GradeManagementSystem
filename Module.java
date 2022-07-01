import java.util.List;

public class Module {

    private String name;
    private int semester;
    private List<Course> courses;
    private double credits;
    private int grade;

    public Module(String name, int semester, List<Course> courses) {
        this.name = name;
        this.semester = semester;
        this.courses = courses;
        for (Course course : courses) {
            credits += course.getCredits();
            grade += course.getCredits() * course.getGrade();
        }
//        grade = grade / credits;
        // should we add two grades, one rounded one, one as a double?
    }
}
