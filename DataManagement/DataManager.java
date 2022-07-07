package DataManagement;

import Logic.GradeManagementSystem;
import Logic.Module;
import Logic.StudyProgram;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DataManager {

    public static void saveStudyPrograms(GradeManagementSystem gms) throws IOException {
        Path workingDirectory = Paths.get("").toAbsolutePath();
        Path dataFile = Path.of(workingDirectory.toString(), "data");
        try (BufferedWriter writer = Files.newBufferedWriter(dataFile, StandardOpenOption.CREATE)) {
            writer.write(String.valueOf(gms.size()));
            writer.newLine();
            for (StudyProgram studyProgram : gms.getStudyPrograms()) {
                writer.write(String.valueOf(studyProgram.size()));
                writer.newLine();
            }
            for (StudyProgram  studyProgram : gms.getStudyPrograms()) {
                for (Module module : studyProgram.getModules()) {
                    writer.write(generateFileMetaData(module));
                    // TODO: coursesList
                    writer.newLine();
                }
            }


        }
    }

    private static String generateFileMetaData(Module module) {
        return module.getName() +
                module.getSemester() +
                module.getCredits() +
                module.getRoundedGrade() +
                module.getFloatingGrade();
    }

    public static void loadStudyPrograms() {

    }

}
