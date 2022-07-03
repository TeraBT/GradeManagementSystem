package Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StudyProgram {

    private String programName;
    private final List<Module> modules = new ArrayList<>();
    private final List<Module> finishedModules = new ArrayList<>();
    private int totalCredits;
    private double currentCredits;
    private double currentModuleAverage;
    private double currentCourseAverage;

    public StudyProgram(String programName, int totalCredits) {
        this.programName = programName;;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public double getCurrentCredits() {
        return currentCredits;
    }

    public double getCurrentModuleAverage() {
        return currentModuleAverage;
    }

    public double getCurrentCourseAverage() {
        return currentCourseAverage;
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

    public void addModuleToFinished(Module module) throws NoSuchElementException {
        if (modules.contains(module)) {
            finishedModules.add(module);
            currentCredits += module.getCredits();
            currentModuleAverage = (module.getCredits() * module.getRoundedGrade()
                    + (currentCredits - module.getCredits()) * currentModuleAverage) / currentCredits;
            currentCourseAverage = (module.getCredits() * module.getFloatingGrade()
                    + (currentCredits - module.getCredits()) * currentCourseAverage) / currentCredits;
        } else {
            throw new NoSuchElementException(module + " does not exits!");
        }
    }

    public void removeModuleFromFinished(Module module) throws NoSuchElementException {
        if (!finishedModules.remove(module)) {
            throw new NoSuchElementException(module + " does not exits!");
        }
        currentCredits -= module.getCredits();
        currentModuleAverage = (currentModuleAverage * (currentCredits + module.getCredits())
                - module.getCredits() * module.getRoundedGrade()) / currentCredits;
        currentCourseAverage = (currentCourseAverage * (currentCredits + module.getCredits())
                - module.getCredits() * module.getFloatingGrade()) / currentCredits;
    }

    public void updateAllData() {
        double cumulativeCredits = 0d;
        double moduleSum = 0d;
        double courseSum = 0d;
        for (Module module : finishedModules) {
            double moduleCredits = module.getCredits();
            cumulativeCredits += moduleCredits;
            moduleSum += moduleCredits * module.getRoundedGrade();
            courseSum += moduleCredits * module.getFloatingGrade();
        }
        this.currentCredits = cumulativeCredits;
        this.currentModuleAverage = moduleSum / cumulativeCredits;
        this.currentCourseAverage = courseSum / cumulativeCredits;
    }

    public double[] calculateNeededComplementaryAverage(double wantedAverage) {
        double complementaryModuleAverage = (wantedAverage * totalCredits
                - currentCredits * currentModuleAverage) / (totalCredits - currentCredits);
        double complementaryCourseAverage = (wantedAverage * totalCredits
                - currentCredits * currentCourseAverage) / (totalCredits - currentCredits);
        return new double[]{complementaryModuleAverage, complementaryCourseAverage};
    }

    public void modifyCourseGrade(String courseName, CourseType type, int newGrade) throws NoSuchElementException {
        for (Module module : modules) {
            try {
                module.modifyCourseGrade(courseName, type, newGrade);
                if (finishedModules.contains(module)) {
                    updateAllData();
                }
                return;
            } catch (NoSuchElementException ignored) {}
        }
        throw new NoSuchElementException(type + courseName + " does not exits!");
    }

    public void modifyCourseCredits(String courseName, CourseType type, double updatedCredits) throws NoSuchElementException {
        for (Module module : modules) {
            try {
                module.modifyCourseCredits(courseName, type, updatedCredits);
                if (finishedModules.contains(module)) {
                    updateAllData();
                }
                return;
            } catch (NoSuchElementException ignored) {}
        }
        throw new NoSuchElementException(type + courseName + " does not exits!");
    }
}
