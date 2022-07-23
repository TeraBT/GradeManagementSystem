package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class StudyProgram {

    private String name;
    private final List<Module> modules = new ArrayList<>();
    private final List<Module> finishedModules = new ArrayList<>();
    private int totalCredits;
    private double currentCredits;
    private double currentModuleAverage;
    private double currentCourseAverage;

    public StudyProgram(String programName) {
        this.name = programName;
    }

    public StudyProgram(String programName, int totalCredits) {
        this.name = programName;
        this.totalCredits = totalCredits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Module> getModules() {
        return Collections.unmodifiableList(modules);
    }

    public int size() {
        return modules.size();
    }

    public void addModule(Module module) throws IllegalStateException {
        if (modules.contains(module)) {
            throw new IllegalStateException(module + " cannot be added twice!");
        }
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
            module.setFinished(true);
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
        module.setFinished(false);
        currentCredits -= module.getCredits();
        currentModuleAverage = (currentModuleAverage * (currentCredits + module.getCredits())
                - module.getCredits() * module.getRoundedGrade()) / currentCredits;
        currentCourseAverage = (currentCourseAverage * (currentCredits + module.getCredits())
                - module.getCredits() * module.getFloatingGrade()) / currentCredits;
    }

    public boolean isFinished(Module module) {
        return module.isFinished();
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
            if (module.modifyCourseGrade(courseName, type, newGrade)) {
                if (finishedModules.contains(module)) {
                    updateAllData();
                }
                return;
            }
        }
        throw new NoSuchElementException(type + courseName + " does not exits!");
    }

    public void modifyCourseCredits (String courseName, CourseType type,double updatedCredits) throws NoSuchElementException {
        for (Module module : modules) {
            if (module.modifyCourseCredits(courseName, type, updatedCredits)) {
                if (finishedModules.contains(module)) {
                    updateAllData();
                }
                return;
            }
        }
        throw new NoSuchElementException(type + courseName + " does not exits!");
    }



}