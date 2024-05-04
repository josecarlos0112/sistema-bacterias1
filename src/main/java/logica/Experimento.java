package logica;

import java.util.ArrayList;
import java.util.List;

public class Experimento {
    private List<PoblacionBacterias> populations;

    public Experimento() {
        this.populations = new ArrayList<>();
    }

    public void addPopulation(PoblacionBacterias population) {
        this.populations.add(population);
    }

    public void removePopulation(PoblacionBacterias population) {
        this.populations.remove(population);
    }

    public PoblacionBacterias getPopulation(String name) {
        for (PoblacionBacterias population : populations) {
            if (population.getName().equals(name)) {
                return population;
            }
        }
        return null;
    }

    public List<PoblacionBacterias> getPopulations() {
        return populations;
    }

    public void setPopulations(List<PoblacionBacterias> populations) {
        this.populations = populations;
    }

    public void clearPopulations() {
        this.populations.clear();
    }

    public int getPopulationCount() {
        return this.populations.size();
    }

    public boolean isEmpty() {
        return this.populations.isEmpty();
    }

    public boolean containsPopulation(String name) {
        for (PoblacionBacterias population : populations) {
            if (population.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

public void updatePopulation(PoblacionBacterias population) {
        for (PoblacionBacterias p : populations) {
            if (p.getName().equals(population.getName())) {
                p.setStartDate(population.getStartDate());
                p.setEndDate(population.getEndDate());
                p.setInitialBacteriaCount(population.getInitialBacteriaCount());
                p.setTemperature(population.getTemperature());
                p.setLightCondition(population.getLightCondition());
                p.setInitialFood(population.getInitialFood());
                p.setIncrementDay(population.getIncrementDay());
                p.setIncrementFood(population.getIncrementFood());
                p.setFinalFood(population.getFinalFood());
            }
        }
    }

    public void removePopulation(String name) {
        for (PoblacionBacterias population : populations) {
            if (population.getName().equals(name)) {
                populations.remove(population);
                break;
            }
        }
    }

    public void clear() {
        this.populations.clear();
    }

    public void print() {
        for (PoblacionBacterias population : populations) {
            System.out.println(population);
        }
    }

    @Override
    public String toString() {
        return "Experimento{" + "populations=" + populations + '}';
    }

}