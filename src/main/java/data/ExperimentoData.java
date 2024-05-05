package data;

import logica.Experimento;
import java.io.*;

public class ExperimentoData {
    public void guardarDatos(Experimento experimento, String filename) {
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

    public Experimento cargarDatos(String filename) {
        Experimento experimento = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            experimento = (Experimento) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Experimento class not found");
            c.printStackTrace();
        }
        return experimento;
    }
}