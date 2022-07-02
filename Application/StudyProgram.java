package Application;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StudyProgram {

    private String programName;
    private final List<Module> modules = new ArrayList<>();
    private final List<Module> finishedModules = new ArrayList<>();
    private int totalCredits;
    private int currentCredits;
    private double currentModuleAverage;
    private double currentCourseAverage;

    public StudyProgram(String programName, int totalCredits) {
        this.programName = programName;
        this.currentCredits = totalCredits;
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public void removeModule(Module module) throws NoSuchElementException {
        if (!modules.remove(module)) {
            throw new NoSuchElementException(module + " does not exits!");
        } else {
            if (finishedModules.remove(module)) {
                currentCredits -= module.getCredits();
            }
        }
    }

    public void setModuleToFinished(Module module) throws NoSuchElementException {
        if (modules.contains(module)) {
            finishedModules.add(module);
            currentCredits += module.getCredits();
            updateAverages(module);
        } else {
            throw new NoSuchElementException(module + " does not exits!");
        }
    }

    public void setModuleToUnfinished(Module module) throws NoSuchElementException {
        if (!finishedModules.remove(module)) {
            throw new NoSuchElementException(module + " does not exits!");
        }
        currentCredits -= module.getCredits();
        updateAverages(module); // wrong behavior after minus!
        // this is bad in general, method should work in every environment
    }

    public double[] getAverages() {
        double cumulativeCredits = 0d;
        double moduleSum = 0;
        double courseSum = 0d;
        for (Module module : finishedModules) {
            double moduleCredits = module.getCredits();
            cumulativeCredits += moduleCredits;
            moduleSum += moduleCredits * module.getRoundedGrade();
            courseSum += moduleSum * module.getFloatingGrade();
        }
        this.currentModuleAverage = moduleSum / cumulativeCredits;
        this.currentCourseAverage = courseSum / cumulativeCredits;
        return new double[]{currentModuleAverage, currentCourseAverage};
    }

    public void updateAverages(Module module) {
        currentModuleAverage += (module.getCredits() * module.getRoundedGrade()
                + (currentCredits - module.getCredits()) * currentModuleAverage) / currentCredits;
        currentCourseAverage += (module.getCredits() * module.getFloatingGrade()
                + (currentCredits - module.getCredits()) * currentCourseAverage) / currentCredits;
    }
}
