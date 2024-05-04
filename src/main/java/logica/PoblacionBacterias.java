package logica;

import java.time.LocalDate;


public class PoblacionBacterias {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int initialBacteriaCount;
    private double temperature;
    private String lightCondition;
    private int initialFood;
    private int finalFood;
    private int incrementDay;
    private int incrementFood;

    public PoblacionBacterias(String name, LocalDate startDate, LocalDate endDate, int initialBacteriaCount, double temperature, String lightCondition, int initialFood, int incrementDay, int incrementFood, int finalFood) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.initialBacteriaCount = initialBacteriaCount;
        this.temperature = temperature;
        this.lightCondition = lightCondition;
        this.initialFood = initialFood;
        this.incrementDay = incrementDay;
        this.incrementFood = incrementFood;
        this.finalFood = finalFood;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getInitialBacteriaCount() {
        return initialBacteriaCount;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getLightCondition() {
        return lightCondition;
    }

    public int getInitialFood() {
        return initialFood;
    }

    public int getFinalFood() {
        return finalFood;
    }

    public int getIncrementDay() {
        return incrementDay;
    }

    public int getIncrementFood() {
        return incrementFood;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int calculateFoodForDay(int day) {
        // Implement the logic to calculate the food for a specific day
        return day;
    }
}