// MenuPrincipalUI.java
package ui;

import data.ExperimentoData;
import logica.ExperimentoManager;
import logica.PoblacionBacteriasManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class MenuPrincipalUI extends JFrame {
    private ExperimentoManager experimentoManager;
    private PoblacionBacteriasManager poblacionBacteriasManager;
    private JList<PoblacionBacteriasManager> experimentoList;
    private JList<PoblacionBacteriasManager> poblacionList;
    private JTextArea poblacionDetails;

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
                        PoblacionBacteriasManager.updatePoblacion(selectedExperimento);
                    }
                }
            }
        });

        // Crear la lista de poblaciones
        poblacionList = new JList<>();
        poblacionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        poblacionList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    PoblacionBacteriasManager selectedPoblacion = poblacionList.getSelectedValue();
                    if (selectedPoblacion != null) {
                        PoblacionBacteriasManager.showPoblacionDetails(selectedPoblacion);
                    }
                }
            }
        });

        // Crear el área de texto para los detalles de la población
        poblacionDetails = new JTextArea();
        poblacionDetails.setEditable(false);

        // Crear los paneles para la lista de experimentos y los detalles del experimento
        JPanel experimentoListPanel = new JPanel(new BorderLayout());
        experimentoListPanel.add(new JScrollPane(experimentoList), BorderLayout.CENTER);
        experimentoListPanel.setPreferredSize(new Dimension(300, 300)); // Establecer un tamaño preferido

        JPanel poblacionListPanel = new JPanel(new BorderLayout());
        poblacionListPanel.add(new JScrollPane(poblacionList), BorderLayout.CENTER);
        poblacionListPanel.setPreferredSize(new Dimension(500, 300)); // Establecer un tamaño preferido

        JPanel poblacionDetailsPanel = new JPanel(new BorderLayout());
        poblacionDetailsPanel.add(new JScrollPane(poblacionDetails), BorderLayout.CENTER);


        // Crear el JSplitPane y agregar los paneles a él
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, poblacionListPanel, poblacionDetailsPanel);
        splitPane.setResizeWeight(0.5); // Esto hace que ambos paneles tengan el mismo tamaño

        // Agregar los paneles al JFrame
        add(experimentoListPanel, BorderLayout.WEST);
        add(splitPane, BorderLayout.CENTER);
    }

    private void updateExperimentoList() {
        List<PoblacionBacteriasManager> experimentos = experimentoManager.getPopulations();
        DefaultListModel<PoblacionBacteriasManager> model = new DefaultListModel<>();
        for (PoblacionBacteriasManager experimento : experimentos) {
            model.addElement(experimento);
        }
        experimentoList.setModel(model);
    }

    private void crearNuevoExperimento() {
        String nombre = JOptionPane.showInputDialog(this, "Introduce el nombre del experimento:");
        if (nombre != null && !nombre.isEmpty()) {
            PoblacionBacteriasManager experimento = new PoblacionBacteriasManager();
            experimento.setName(nombre);
            experimentoManager.addPopulation(experimento);
            updateExperimentoList();

            // Crear un directorio para el experimento
            new File("./experimentos/" + nombre).mkdirs();
        }
    }

    private void abrirExperimento() {
        String nombre = JOptionPane.showInputDialog(this, "Introduce el nombre del experimento que quieres abrir:");
        if (nombre != null && !nombre.isEmpty()) {
            // Cargar los datos del experimento
            ExperimentoManager experimento = new ExperimentoData().cargarDatos("./experimentos/" + nombre + "/experimento.txt");
            if (experimento != null) {
                experimentoManager = experimento;
                updateExperimentoList();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo abrir el experimento.");
            }
        }
    }

    private void guardarExperimento() {
        PoblacionBacteriasManager selectedExperimento = experimentoList.getSelectedValue();
        if (selectedExperimento != null) {
            // Guardar los datos del experimento
            new ExperimentoData().guardarDatos(experimentoManager, "./experimentos/" + selectedExperimento.getName() + "/experimento.dat");
        } else {
            JOptionPane.showMessageDialog(this, "No hay ningún experimento seleccionado.");
        }
    }

    private void guardarExperimentoComo() {
        String nombre = JOptionPane.showInputDialog(this, "Introduce el nuevo nombre para el experimento:");
        if (nombre != null && !nombre.isEmpty()) {
            PoblacionBacteriasManager selectedExperimento = experimentoList.getSelectedValue();
            if (selectedExperimento != null) {
                // Cambiar el nombre del experimento
                selectedExperimento.setName(nombre);
                updateExperimentoList();

                // Guardar los datos del experimento
                new ExperimentoData().guardarDatos(experimentoManager, "./experimentos/" + nombre + "/experimento.txt");
            } else {
                JOptionPane.showMessageDialog(this, "No hay ningún experimento seleccionado.");
            }
        }
    }

    private void verPoblaciones() {
        // Implementa la lógica para ver las poblaciones de bacterias
    }

    private void crearNuevaPoblacion() {
    PoblacionBacteriasManager selectedExperimento = experimentoList.getSelectedValue();
    if (selectedExperimento != null) {
        String nombre = JOptionPane.showInputDialog(this, "Introduce el nombre de la población:");
        if (nombre != null && !nombre.isEmpty()) {
            // Aquí puedes solicitar al usuario que introduzca más detalles sobre la población de bacterias
            // Por ejemplo, la fecha de inicio, la fecha de fin, la cantidad inicial de bacterias, etc.
            // Por ahora, solo vamos a solicitar el nombre de la población

            PoblacionBacteriasManager nuevaPoblacion = new PoblacionBacteriasManager();
            nuevaPoblacion.setName(nombre);
            selectedExperimento.addPoblacion(nuevaPoblacion);

            // Actualizar la lista de poblaciones
            updatePoblacionList(selectedExperimento);
        }
    } else {
        JOptionPane.showMessageDialog(this, "No hay ningún experimento seleccionado.");
    }
}

private void updatePoblacionList(PoblacionBacteriasManager experimento) {
    List<PoblacionBacteriasManager> poblaciones = experimento.getPoblaciones();
    DefaultListModel<PoblacionBacteriasManager> model = new DefaultListModel<>();
    for (PoblacionBacteriasManager poblacion : poblaciones) {
        model.addElement(poblacion);
    }
    poblacionList.setModel(model);
}

    private void borrarPoblacion() {
        // Implementa la lógica para borrar una población de bacterias
    }

    private void verDetallesPoblacion() {
        // Implementa la lógica para ver los detalles de una población de bacterias
    }
}