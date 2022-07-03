package Logic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

public class UnitTesting {

    @Test
    @DisplayName("Creating module grade and modifying grade and credits in module works")
    void changeGrade() {
        Course c1 = new Course(CourseType.VO, "c1", 2, 4);
        Course c2 = new Course(CourseType.PS, "c2", 2,1);
        Module m = new Module("module", 1, Arrays.asList(c1, c2));

        assertEquals(2.5, m.getFloatingGrade());
        assertEquals(3, m.getRoundedGrade());

        m.modifyCourseGrade("c1", CourseType.VO, 2);

        assertEquals(1.5, m.getFloatingGrade());
        assertEquals(2, m.getRoundedGrade());

        m.modifyCourseCredits("c2", CourseType.PS, 4);

        assertEquals(4/3d, m.getFloatingGrade());
        assertEquals(1, m.getRoundedGrade());
    }

    @Test
    @DisplayName("Adjusting new averages and other functions work in study program (also changing course values)")
    void testStudyProgram() {
        Course c1 = new Course(CourseType.VO, "c1", 2, 4);
        Course c2 = new Course(CourseType.PS, "c2", 2,1);
        Module m1 = new Module("module1", 1, Arrays.asList(c1, c2));

        Course c3 = new Course(CourseType.VO, "c3", 2, 2);
        Course c4 = new Course(CourseType.PS, "c4", 2,1);
        Module m2 = new Module("module2", 1, Arrays.asList(c3, c4));

        StudyProgram p1 = new StudyProgram("program", 10);

        p1.addModule(m1);
        p1.addModule(m2);
        p1.addModuleToFinished(m1);

        assertEquals(3, p1.getCurrentModuleAverage());
        assertEquals(2.5, p1.getCurrentCourseAverage());

        p1.updateAllData();

        assertEquals(3, p1.getCurrentModuleAverage());
        assertEquals(2.5, p1.getCurrentCourseAverage());

        p1.addModuleToFinished(m2);

        assertEquals(2.5, p1.getCurrentModuleAverage());
        assertEquals(2, p1.getCurrentCourseAverage());

        p1.removeModuleFromFinished((m2));

        assertEquals(3, p1.getCurrentModuleAverage());
        assertEquals(2.5, p1.getCurrentCourseAverage());

        p1.updateAllData();

        assertEquals(3, p1.getCurrentModuleAverage());
        assertEquals(2.5, p1.getCurrentCourseAverage());

        p1.addModuleToFinished(m2);
        p1.modifyCourseGrade("c1", CourseType.VO, 5);

        assertEquals(2.5, p1.getCurrentModuleAverage());
        assertEquals(2.25, p1.getCurrentCourseAverage());

        p1.modifyCourseCredits("c1", CourseType.VO, 4);

        assertEquals(3.2, p1.getCurrentModuleAverage());
        assertEquals(2.8, p1.getCurrentCourseAverage());

        p1.setTotalCredits(20);
        assertEquals(-1.2, p1.calculateNeededComplementaryAverage(1)[0]);
        assertEquals(-0.8, p1.calculateNeededComplementaryAverage(1)[1]);
    }




}
