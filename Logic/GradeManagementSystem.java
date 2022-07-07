package Logic;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class GradeManagementSystem {

    private final List<StudyProgram> studyPrograms = new ArrayList<>();

    public int size() {
        return studyPrograms.size();
    }

    public List<StudyProgram> getStudyPrograms() {
        return Collections.unmodifiableList(studyPrograms);
    }


}
