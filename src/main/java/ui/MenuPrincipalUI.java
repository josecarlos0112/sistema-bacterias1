// MenuPrincipalUI.java
package ui;

import logica.ExperimentoManager;
import logica.PoblacionBacteriasManager;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipalUI extends JFrame {
    private ExperimentoManager experimentoManager;
    private PoblacionBacteriasManager poblacionBacteriasManager;

    public MenuPrincipalUI(ExperimentoManager experimentoManager, PoblacionBacteriasManager poblacionBacteriasManager) {
        this.experimentoManager = experimentoManager;
        this.poblacionBacteriasManager = poblacionBacteriasManager;

        // Configuración del JFrame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        setLocation(width / 2 - 500, height / 2 - 300);
        String iconPath = "src/resources/uax-corto.png";
        ImageIcon icon = new ImageIcon(iconPath);
        setIconImage(icon.getImage());
        setTitle("Aplicación para gestionar cultivos de bacterias");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout del JFrame
        setLayout(new BorderLayout());

        // ...

        // Crear el menú principal
        JMenuBar menuBar = new JMenuBar();

        // Crear los submenús
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem abrir = new JMenuItem("Abrir Experimento");
        JMenuItem guardar = new JMenuItem("Guardar Experimento");
        JMenuItem guardarComo = new JMenuItem("Guardar Experimento Como");
        menuArchivo.add(abrir);
        menuArchivo.add(guardar);
        menuArchivo.add(guardarComo);

        JMenu menuExperimento = new JMenu("Experimento");
        JMenuItem nuevoExperimento = new JMenuItem("Crear Nuevo Experimento");
        JMenuItem verPoblaciones = new JMenuItem("Ver Poblaciones de Bacterias");
        menuExperimento.add(nuevoExperimento);
        menuExperimento.add(verPoblaciones);

        JMenu menuPoblacion = new JMenu("Población de Bacterias");
        JMenuItem nuevaPoblacion = new JMenuItem("Crear Nueva Población de Bacterias");
        JMenuItem borrarPoblacion = new JMenuItem("Borrar Población de Bacterias");
        JMenuItem verDetallesPoblacion = new JMenuItem("Ver Detalles de Población de Bacterias");
        menuPoblacion.add(nuevaPoblacion);
        menuPoblacion.add(borrarPoblacion);
        menuPoblacion.add(verDetallesPoblacion);

        // Agregar los submenús al menú principal
        menuBar.add(menuArchivo);
        menuBar.add(menuExperimento);
        menuBar.add(menuPoblacion);

        // Agregar el menú principal al JFrame
        setJMenuBar(menuBar);

        // Agregar los listeners a los items del menú
        abrir.addActionListener(e -> abrirExperimento());
        guardar.addActionListener(e -> guardarExperimento());
        guardarComo.addActionListener(e -> guardarExperimentoComo());
        nuevoExperimento.addActionListener(e -> crearNuevoExperimento());
        verPoblaciones.addActionListener(e -> verPoblaciones());
        nuevaPoblacion.addActionListener(e -> crearNuevaPoblacion());
        borrarPoblacion.addActionListener(e -> borrarPoblacion());
        verDetallesPoblacion.addActionListener(e -> verDetallesPoblacion());

        // Resto de la configuración del JFrame
        // ...
    }

    // Métodos para manejar las acciones del menú
    private void abrirExperimento() {
        // Implementa la lógica para abrir un experimento
    }

    private void guardarExperimento() {
        // Implementa la lógica para guardar un experimento
    }

    private void guardarExperimentoComo() {
        // Implementa la lógica para guardar un experimento con un nuevo nombre
    }

    private void crearNuevoExperimento() {
        // Implementa la lógica para crear un nuevo experimento
    }

    private void verPoblaciones() {
        // Implementa la lógica para ver las poblaciones de bacterias
    }

    private void crearNuevaPoblacion() {
        // Implementa la lógica para crear una nueva población de bacterias
    }

    private void borrarPoblacion() {
        // Implementa la lógica para borrar una población de bacterias
    }

    private void verDetallesPoblacion() {
        // Implementa la lógica para ver los detalles de una población de bacterias
    }
}