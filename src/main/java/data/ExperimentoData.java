package data;

import logica.ExperimentoManager;
import java.io.*;

public class ExperimentoData {
    public void guardarDatos(ExperimentoManager experimento, String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(experimento);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public ExperimentoManager cargarDatos(String filename) {
        ExperimentoManager experimento = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            experimento = (ExperimentoManager) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Experimento no encontrado");
            c.printStackTrace();
        }
        return experimento;
    }
}