package Logic;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class Module {

    private String name;
    private int semester;
    private List<Course> courses;
    private double credits;
    private int roundedGrade;
    private double floatingGrade;

    public Module(String name, int semester, List<Course> courses) {
        this.name = name;
        this.semester = semester;
        this.courses = courses;
        updateModuleData();
    }

    public void setCourseGrade(String courseName, CourseType type, int grade) throws NoSuchElementException {
        Course foundCourse = findCourse(courseName, type);
        foundCourse.setGrade(grade);
        updateModuleData();
    }

    public void setCourseCredits(String courseName, CourseType type, int credits) throws NoSuchElementException {
        Course foundCourse = findCourse(courseName, type);
        foundCourse.setCredits(credits);
        updateModuleData();
    }

    private Course findCourse(String courseName, CourseType type) throws NoSuchElementException {
        for (Course course : courses) {
            if (course.getType() == type && course.getName().equals(courseName)) {
                return course;
            }
        }
        throw new NoSuchElementException(type + courseName + " does not exits!");
    }

    private void updateModuleData() {
        credits = 0d;
        floatingGrade = 0d;
        roundedGrade = 0;

        for (Course course : courses) {
            credits += course.getCredits();
            floatingGrade += course.getCredits() * course.getGrade();
        }
        floatingGrade = floatingGrade / credits;
        roundedGrade = (int) Math.round(floatingGrade);
    }

    public String getName() {
        return name;
    }

    public int getSemester() {
        return semester;
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public double getCredits() {
        return credits;
    }

    public int getRoundedGrade() {
        return roundedGrade;
    }

    public double getFloatingGrade() {
        return floatingGrade;
    }

    public void modifyCourseGrade(String courseName, CourseType type, int newGrade) throws NoSuchElementException {
        Course course = findCourse(courseName, type);
        course.setGrade(newGrade);
        updateModuleData();
    }

    public void modifyCourseCredits(String courseName, CourseType type, double updatedCredits) throws NoSuchElementException {
        Course course = findCourse(courseName, type);
        course.setCredits(updatedCredits);
        updateModuleData();
    }
}
