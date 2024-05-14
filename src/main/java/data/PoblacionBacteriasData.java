package data;

import logica.PoblacionBacteriasManager;
import java.io.*;

public class PoblacionBacteriasData {
    public void guardarDatos(PoblacionBacteriasManager poblacion, String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(poblacion);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public PoblacionBacteriasManager cargarDatos(String filename) {
        PoblacionBacteriasManager poblacion = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            poblacion = (PoblacionBacteriasManager) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Poblacion de Bacterias no encontrada");
            c.printStackTrace();
        }
        return poblacion;
    }
}