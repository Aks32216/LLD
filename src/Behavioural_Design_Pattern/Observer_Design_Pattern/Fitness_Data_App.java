package Behavioural_Design_Pattern.Observer_Design_Pattern;

import java.util.ArrayList;
import java.util.List;

interface FitnessDataObserver {
    void update(FitnessData data);
}

interface FitnessDataSubject {
    void registerObserver(FitnessDataObserver observer);
    void removeObserver(FitnessDataObserver observer);
    void notifyObservers();
}

class FitnessData implements FitnessDataSubject {
    private int steps;
    private int activeMinutes;
    private int calories;

    private final List<FitnessDataObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(FitnessDataObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(FitnessDataObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (FitnessDataObserver observer : observers) {
            observer.update(this);
        }
    }

    public void newFitnessDataPushed(int steps, int activeMinutes, int calories) {
        this.steps = steps;
        this.activeMinutes = activeMinutes;
        this.calories = calories;

        System.out.println("\nFitnessData: New data received – Steps: " + steps +
                ", Active Minutes: " + activeMinutes + ", Calories: " + calories);

        notifyObservers();
    }

    public void dailyReset() {
        this.steps = 0;
        this.activeMinutes = 0;
        this.calories = 0;

        System.out.println("\nFitnessData: Daily reset performed.");
        notifyObservers();
    }

    // Getters
    public int getSteps() { return steps; }
    public int getActiveMinutes() { return activeMinutes; }
    public int getCalories() { return calories; }
}

class LiveActivityDisplay implements FitnessDataObserver {
    @Override
    public void update(FitnessData data) {
        System.out.println("Live Display → Steps: " + data.getSteps() +
                " | Active Minutes: " + data.getActiveMinutes() +
                " | Calories: " + data.getCalories());
    }
}

class ProgressLogger implements FitnessDataObserver {
    @Override
    public void update(FitnessData data) {
        System.out.println("Logger → Saving to DB: Steps=" + data.getSteps() +
                ", ActiveMinutes=" + data.getActiveMinutes() +
                ", Calories=" + data.getCalories());
        // Simulated DB/file write...
    }
}

class GoalNotifier implements FitnessDataObserver {
    private final int stepGoal = 10000;
    private boolean goalReached = false;

    @Override
    public void update(FitnessData data) {
        if (data.getSteps() >= stepGoal && !goalReached) {
            System.out.println("Notifier → 🎉 Goal Reached! You've hit " + stepGoal + " steps!");
            goalReached = true;
        }
    }

    public void reset() {
        goalReached = false;
    }
}

class WeeklySummaryGenerator implements FitnessDataObserver {
    private int totalSteps = 0;
    private int totalActiveMinutes = 0;
    private int totalCalories = 0;
    private int updateCount = 0;

    @Override
    public void update(FitnessData data) {
        totalSteps += data.getSteps();
        totalActiveMinutes += data.getActiveMinutes();
        totalCalories += data.getCalories();
        updateCount++;

        System.out.println("Weekly Summary -> Accumulated " + updateCount +
                " updates. Total Steps: " + totalSteps);
    }

    public void generateReport() {
        System.out.println("\n=== Weekly Summary Report ===");
        System.out.println("Total Steps: " + totalSteps);
        System.out.println("Total Active Minutes: " + totalActiveMinutes);
        System.out.println("Total Calories: " + totalCalories);
        System.out.println("Data Points Collected: " + updateCount);
        System.out.println("Avg Steps/Update: " + (updateCount > 0 ? totalSteps / updateCount : 0));
    }

    public void reset() {
        totalSteps = 0;
        totalActiveMinutes = 0;
        totalCalories = 0;
        updateCount = 0;
    }
}

public class Fitness_Data_App {
    public static void main(String[] args) {
        FitnessData fitnessData = new FitnessData();

        WeeklySummaryGenerator summary = new WeeklySummaryGenerator();
        LiveActivityDisplay display = new LiveActivityDisplay();
        ProgressLogger logger = new ProgressLogger();
        GoalNotifier notifier = new GoalNotifier();

        // Register observers
        fitnessData.registerObserver(display);
        fitnessData.registerObserver(logger);
        fitnessData.registerObserver(notifier);
        fitnessData.registerObserver(summary);

        // Simulate updates
        fitnessData.newFitnessDataPushed(500, 5, 20);
        fitnessData.newFitnessDataPushed(9800, 85, 350);
        fitnessData.newFitnessDataPushed(10100, 90, 380);


        // ... after a week of updates ...
        summary.generateReport();
        summary.reset();

        // Remove logger and reset notifier
        fitnessData.removeObserver(logger);
        notifier.reset();
        fitnessData.dailyReset();
    }
}
