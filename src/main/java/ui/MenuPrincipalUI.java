// MenuPrincipalUI.java
package ui;

import logica.ExperimentoManager;
import logica.PoblacionBacteriasManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MenuPrincipalUI extends JFrame {
    private ExperimentoManager experimentoManager;
    private PoblacionBacteriasManager poblacionBacteriasManager;
    private JList<PoblacionBacteriasManager> experimentoList;
    private JTextArea experimentoDetails;

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

        // Crear la lista de experimentos
        experimentoList = new JList<>();
        experimentoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        experimentoList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    PoblacionBacteriasManager selectedExperimento = experimentoList.getSelectedValue();
                    if (selectedExperimento != null) {
                        showExperimentoDetails(selectedExperimento);
                    }
                }
            }
        });

        // Crear el área de texto para los detalles del experimento
        experimentoDetails = new JTextArea();
        experimentoDetails.setEditable(false);

        // Crear los paneles para la lista de experimentos y los detalles del experimento
        JPanel experimentoListPanel = new JPanel(new BorderLayout());
        experimentoListPanel.add(new JScrollPane(experimentoList), BorderLayout.CENTER);

        JPanel experimentoDetailsPanel = new JPanel(new BorderLayout());
        experimentoDetailsPanel.add(new JScrollPane(experimentoDetails), BorderLayout.CENTER);

        // Agregar los paneles al JFrame
        add(experimentoListPanel, BorderLayout.WEST);
        add(experimentoDetailsPanel, BorderLayout.CENTER);
    }

    // Método para mostrar los detalles de un experimento
    private void showExperimentoDetails(PoblacionBacteriasManager experimento) {
        StringBuilder details = new StringBuilder();
        details.append("Nombre del experimento: ").append(experimento.getName()).append("\n");
        details.append("Fecha de inicio: ").append(experimento.getStartDate()).append("\n");
        details.append("Fecha de fin: ").append(experimento.getEndDate()).append("\n");
        details.append("Cantidad inicial de bacterias: ").append(experimento.getInitialBacteriaCount()).append("\n");
        details.append("Temperatura: ").append(experimento.getTemperature()).append("\n");
        details.append("Condición de luz: ").append(experimento.getLightCondition()).append("\n");
        details.append("Comida inicial: ").append(experimento.getInitialFood()).append("\n");
        details.append("Comida final: ").append(experimento.getFinalFood()).append("\n");
        experimentoDetails.setText(details.toString());
    }

    private void updateExperimentoList() {
        List<PoblacionBacteriasManager> experimentos = experimentoManager.getPopulations();
        DefaultListModel<PoblacionBacteriasManager> model = new DefaultListModel<>();
        for (PoblacionBacteriasManager experimento : experimentos) {
            model.addElement(experimento);
        }
        experimentoList.setModel(model);
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
        String nombre = JOptionPane.showInputDialog(this, "Introduce el nombre del experimento:");
        if (nombre != null && !nombre.isEmpty()) {
            PoblacionBacteriasManager experimento = new PoblacionBacteriasManager();
            experimento.setName(nombre);
            experimentoManager.addPopulation(experimento);
            updateExperimentoList();
        }
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