package logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExperimentoManager extends PoblacionBacteriasManager implements Serializable {
    private List<PoblacionBacteriasManager> populations;

    public ExperimentoManager() {
        this.populations = new ArrayList<>();
    }

    public void addPopulation(PoblacionBacteriasManager population) {
        this.populations.add(population);
    }

    public void removePopulation(PoblacionBacteriasManager population) {
        this.populations.remove(population);
    }

    public PoblacionBacteriasManager getPopulation(String name) {
        for (PoblacionBacteriasManager population : populations) {
            if (population.getName().equals(name)) {
                return population;
            }
        }
        return null;
    }



    public void setPopulations(List<PoblacionBacteriasManager> populations) {
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
        for (PoblacionBacteriasManager population : populations) {
            if (population.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void updatePopulation(PoblacionBacteriasManager population) {
        for (PoblacionBacteriasManager p : populations) {
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
        for (PoblacionBacteriasManager population : populations) {
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
        for (PoblacionBacteriasManager population : populations) {
            System.out.println(population);
        }
    }

    @Override
    public String toString() {
        return "Experimento{" + "poblaciones=" + populations + '}';
    }

    public List<PoblacionBacteriasManager> getPopulations() {
        return populations;
    }
}