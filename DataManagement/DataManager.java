package DataManagement;

import Logic.*;
import Logic.Module;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    public static void saveStudyPrograms(GradeManagementSystem gms) throws IOException {
        Path workingDirectory = Paths.get("").toAbsolutePath();
        Path dataFile = Path.of(workingDirectory.toString(), "data");
        try (BufferedWriter writer = Files.newBufferedWriter(dataFile, StandardOpenOption.CREATE)) {
            writer.write(String.valueOf(gms.size()));
            writer.newLine();
            for (StudyProgram studyProgram : gms.getStudyPrograms()) {
                writer.write(studyProgram.getName());
                writer.newLine();
                writer.write(studyProgram.getTotalCredits());
                writer.newLine();
                writer.write(String.valueOf(studyProgram.size()));
                writer.newLine();
            }
            for (StudyProgram  studyProgram : gms.getStudyPrograms()) {
                for (Module module : studyProgram.getModules()) {
                    writer.write(generateModuleMetaData(module));
                    writer.write(",");
                    writer.write(generateModuleCourseData(module));
                    writer.newLine();
                }
            }
        }
    }

    private static String generateModuleMetaData(Module module) {
        return "%s,%s,%s,%s,%s,%s". formatted(
                module.getName(),
                module.getSemester(),
                module.getCredits(),
                module.getRoundedGrade(),
                module.getFloatingGrade(),
                module.isFinished());
    }

    private static String generateModuleCourseData(Module module) {
        StringBuilder data = new StringBuilder();
        for (Course course : module.getCourseList()) {
            String dataForEachCourse =  "%s,%s,%s,%s,".formatted(
                    course.getName(),
                    course.getType().name(),
                    course.getCredits(),
                    course.getGrade());
            data.append(dataForEachCourse);
        }
        data.deleteCharAt(data.length());
        return data.toString();
    }

    public static GradeManagementSystem loadStudyPrograms() throws IOException {
        GradeManagementSystem gms = new GradeManagementSystem();
        Path workingDirectory = Paths.get("").toAbsolutePath();
        Path dataFile = Path.of(workingDirectory.toString(), "data");
        try (BufferedReader reader = Files.newBufferedReader(dataFile)) {
            int studyProgramCount = Integer.parseInt(reader.readLine());
            String[] nameForEachProgram = new String[studyProgramCount];
            int[] totalCreditsForEachProgram = new int[studyProgramCount];
            int[] moduleCountForEachProgram = new int[studyProgramCount];
            for (int i = 0; i < studyProgramCount; ++i) {
                nameForEachProgram[i] = reader.readLine();
                totalCreditsForEachProgram[i] = Integer.parseInt(reader.readLine());
                moduleCountForEachProgram[i] = Integer.parseInt(reader.readLine());
            }
            for (int i = 0; i < studyProgramCount; ++i) {
                StudyProgram loadedProgram = new StudyProgram(nameForEachProgram[i], totalCreditsForEachProgram[i]);
                gms.addStudyProgram(loadedProgram);
                for (int j = 0; j < moduleCountForEachProgram[j]; ++j) {
                    Module loadedModule = parseModule(reader.readLine());
                    loadedProgram.addModule(loadedModule);
                    if (loadedModule.isFinished()) {
                        loadedProgram.addModuleToFinished(loadedModule);
                    }
                }
            }
        }
        return gms;
    }

    private static Module parseModule(String str) {
        // parse courses first then module afterwards
//        Module parsedModule = new Module()
        List<Course> courseList = new ArrayList<>();
        String[] arrayedData = str.split(",");
        for (int i = 6; i+3 < arrayedData.length; ++i) {
            String courseName = arrayedData[i];
            CourseType courseType = CourseType.valueOf(arrayedData[i+1]);
            double courseCredits = Double.parseDouble(arrayedData[i+2]);
            int courseGrade = Integer.parseInt(arrayedData[i+3]);

            Course generatedCourse = new Course(courseName, courseType, courseCredits, courseGrade);
            courseList.add(generatedCourse);
        }
        String moduleName = arrayedData[0];
        int moduleSemester = Integer.parseInt(arrayedData[1]);
        // this might be useful in the future
//        double moduleCredits = Double.parseDouble(arrayedData[2]);
//        int moduleRoundedGrade = Integer.parseInt(arrayedData[3]);
//        double moduleFloatingGrade = Double.parseDouble(arrayedData[4]);
        boolean moduleIsFinished = Boolean.parseBoolean(arrayedData[5]);

        return new Module(moduleName, moduleSemester, courseList, moduleIsFinished);
    }
}
