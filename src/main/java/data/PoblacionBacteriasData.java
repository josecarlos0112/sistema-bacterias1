package data;

import logica.PoblacionBacteriasManager;

import java.io.*;
import java.time.LocalDate;
import java.util.StringJoiner;

public class PoblacionBacteriasData {
    public void guardarDatos(PoblacionBacteriasManager poblacion, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
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
        }
    }

    public PoblacionBacteriasManager cargarDatos(String filename) {
        PoblacionBacteriasManager poblacion = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                poblacion = new PoblacionBacteriasManager();
                poblacion.setName(parts[0]);
                poblacion.setStartDate(LocalDate.ofEpochDay(Integer.parseInt(parts[1])));
                poblacion.setEndDate(LocalDate.ofEpochDay(Integer.parseInt(parts[2])));
                poblacion.setInitialBacteriaCount(Integer.parseInt(parts[3]));
                poblacion.setTemperature(Double.parseDouble(parts[4]));
                poblacion.setLightCondition(String.valueOf(Boolean.parseBoolean(parts[5])));
                poblacion.setInitialFood(Integer.parseInt(parts[6]));
                poblacion.setIncrementDay(Integer.parseInt(parts[7]));
                poblacion.setIncrementFood(Integer.parseInt(parts[8]));
                poblacion.setFinalFood(Integer.parseInt(parts[9]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return poblacion;
    }
}