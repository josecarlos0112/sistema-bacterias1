package data;

import logica.PoblacionBacteriasManager;
import logica.ExperimentoManager;

import java.io.*;
import java.time.LocalDate;
import java.util.StringJoiner;

public class ExperimentoData {
    public void guardarDatos(PoblacionBacteriasManager poblacion, String filename) {
    BufferedWriter writer = null;
    try {
        writer = new BufferedWriter(new FileWriter(filename));
        StringJoiner joiner = new StringJoiner("|");
        joiner.add(poblacion.getName())
                .add(String.valueOf(poblacion.getStartDate()))
                .add(String.valueOf(poblacion.getEndDate()))
                .add(String.valueOf(poblacion.getInitialBacteriaCount()))
                .add(String.valueOf(poblacion.getTemperature()))
                .add(String.valueOf(poblacion.getLightCondition()))
                .add(String.valueOf(poblacion.getInitialFood()))
                .add(String.valueOf(poblacion.getIncrementDay()))
                .add(String.valueOf(poblacion.getIncrementFood()))
                .add(String.valueOf(poblacion.getFinalFood()));
        writer.write(joiner.toString());
        writer.newLine();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

    public ExperimentoManager cargarDatos(String filename) {
    ExperimentoManager experimento = new ExperimentoManager();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            PoblacionBacteriasManager poblacion = new PoblacionBacteriasManager();
            poblacion.setName(parts[0]);
            poblacion.setStartDate(LocalDate.parse(parts[1]));
            poblacion.setEndDate(LocalDate.parse(parts[2]));
            poblacion.setInitialBacteriaCount(Integer.parseInt(parts[3]));
            poblacion.setTemperature(Double.parseDouble(parts[4]));
            poblacion.setLightCondition(String.valueOf(Boolean.parseBoolean(parts[5])));
            poblacion.setInitialFood(Integer.parseInt(parts[6]));
            poblacion.setIncrementDay(Integer.parseInt(parts[7]));
            poblacion.setIncrementFood(Integer.parseInt(parts[8]));
            poblacion.setFinalFood(Integer.parseInt(parts[9]));
            experimento.addPopulation(poblacion);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return experimento;
}
}