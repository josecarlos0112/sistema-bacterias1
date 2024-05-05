package data;

import logica.PoblacionBacterias;
import java.io.*;

public class PoblacionBacteriasData {
    public void guardarDatos(PoblacionBacterias poblacion, String filename) {
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

    public PoblacionBacterias cargarDatos(String filename) {
        PoblacionBacterias poblacion = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            poblacion = (PoblacionBacterias) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("PoblacionBacterias class not found");
            c.printStackTrace();
        }
        return poblacion;
    }
}