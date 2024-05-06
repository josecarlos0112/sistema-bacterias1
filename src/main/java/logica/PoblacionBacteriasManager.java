package logica;

import java.time.LocalDate;
import java.util.List;


public class PoblacionBacteriasManager {
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

    public PoblacionBacteriasManager(String name, LocalDate startDate, LocalDate endDate, int initialBacteriaCount, double temperature, String lightCondition, int initialFood, int incrementDay, int incrementFood, int finalFood) {
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

    private List<PoblacionBacteriasManager> poblaciones;

    public PoblacionBacteriasManager() {
        // Implement the logic to create a bacteria population

    }

    public void addPoblacion(PoblacionBacteriasManager poblacion) {

    }

    public void removePoblacion(PoblacionBacteriasManager poblacion) {
        // Implement the logic to remove a bacteria population
    }

    public void updatePoblacion(PoblacionBacteriasManager poblacion) {
        // Implement the logic to update a bacteria population
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setInitialBacteriaCount(int initialBacteriaCount) {
        this.initialBacteriaCount = initialBacteriaCount;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setLightCondition(String lightCondition) {
        this.lightCondition = lightCondition;
    }

    public void setInitialFood(int initialFood) {
        this.initialFood = initialFood;
    }

    public void setFinalFood(int finalFood) {
        this.finalFood = finalFood;
    }

    public void setIncrementDay(int incrementDay) {
        this.incrementDay = incrementDay;
    }

    public void setIncrementFood(int incrementFood) {
        this.incrementFood = incrementFood;
    }

    public int calculateBacteriaForDay(int day) {

        return day;
    }

    public int calculateFoodForDay(int day) {
        // Implement the logic to calculate the food for a specific day
        return day;
    }
}