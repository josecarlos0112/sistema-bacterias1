// Main.java
import logica.ExperimentoManager;
import logica.PoblacionBacteriasManager;
import ui.MenuPrincipalUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ExperimentoManager experimentoManager = new ExperimentoManager();
            PoblacionBacteriasManager poblacionBacteriasManager = new PoblacionBacteriasManager();
            MenuPrincipalUI menuPrincipalUI = new MenuPrincipalUI(experimentoManager, poblacionBacteriasManager);
            menuPrincipalUI.setVisible(true);
        });
    }
}