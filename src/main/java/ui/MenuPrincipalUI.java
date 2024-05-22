// MenuPrincipalUI.java
package ui;

import data.ExperimentoData;
import data.PoblacionBacteriasData;
import logica.ExperimentoManager;
import logica.PoblacionBacteriasManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
        String iconPath = "src/img/uax-corto.png";
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
        JMenu menuExperimento = new JMenu("Experimento");
        JMenuItem nuevoExperimento = new JMenuItem("Crear Nuevo Experimento");
        JMenuItem abrir = new JMenuItem("Abrir Experimento");
        JMenuItem guardar = new JMenuItem("Guardar Experimento");
        JMenuItem guardarComo = new JMenuItem("Guardar Experimento Como");
        menuExperimento.add(nuevoExperimento);
        menuExperimento.add(abrir);
        menuExperimento.add(guardar);
        menuExperimento.add(guardarComo);

        JMenu menuPoblacion = new JMenu("Población de Bacterias");
        JMenuItem nuevaPoblacion = new JMenuItem("Crear Nueva Población de Bacterias");
        JMenuItem borrarPoblacion = new JMenuItem("Borrar Población de Bacterias");
        JMenuItem editarPoblacion = new JMenuItem("Editar Población de Bacterias");
        menuPoblacion.add(nuevaPoblacion);
        menuPoblacion.add(borrarPoblacion);
        menuPoblacion.add(editarPoblacion);

        // Agregar los submenús al menú principal
        menuBar.add(menuExperimento);
        menuBar.add(menuPoblacion);

        // Agregar el menú principal al JFrame
        setJMenuBar(menuBar);

        // Agregar los listeners a los items del menú
        abrir.addActionListener(e -> abrirExperimento());
        guardar.addActionListener(e -> guardarExperimento());
        guardarComo.addActionListener(e -> guardarExperimentoComo());
        nuevoExperimento.addActionListener(e -> crearNuevoExperimento());
        nuevaPoblacion.addActionListener(e -> crearNuevaPoblacion());
        borrarPoblacion.addActionListener(e ->eliminarPoblacion());

        // Crear la lista de experimentos
        experimentoList = new JList<>();
        experimentoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        experimentoList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    PoblacionBacteriasManager selectedExperimento = experimentoList.getSelectedValue();
                    if (selectedExperimento != null) {
                        updateExperimentoList();
                    }
                }
            }
        });

        // Crear la lista de poblaciones
        poblacionList = new JList<>();
        poblacionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        poblacionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    PoblacionBacteriasManager selectedPoblacion = poblacionList.getSelectedValue();
                    if (selectedPoblacion != null) {
                        poblacionDetails.setText(selectedPoblacion.toString());
                    }
                }
            }
        });

        // Crear el área de texto para los detalles de la población
        poblacionDetails = new JTextArea();
        poblacionDetails.setEditable(false);

        // Crear los paneles para la lista de experimentos y los detalles del experimento
        JPanel experimentoListPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton crearExperimentoButton = new JButton("Crear Experimento");
        JButton borrarExperimentoButton = new JButton("Borrar Experimento");
        buttonPanel.add(crearExperimentoButton);
        buttonPanel.add(borrarExperimentoButton);
        experimentoListPanel.add(buttonPanel, BorderLayout.SOUTH);
        experimentoListPanel.add(new JScrollPane(experimentoList), BorderLayout.CENTER);
        experimentoListPanel.setPreferredSize(new Dimension(300, 300)); // Establecer un tamaño preferido
        crearExperimentoButton.addActionListener(e -> crearNuevoExperimento());
        borrarExperimentoButton.addActionListener(e -> borrarExperimento());

        JPanel poblacionListPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // Crear los botones para crear, editar y eliminar poblaciones
        JButton crearPoblacionButton = new JButton("Crear Población");
        JButton editarPoblacionButton = new JButton("Editar Población");
        JButton eliminarPoblacionButton = new JButton("Eliminar Población");
        // Agregar los botones al panel
        buttonPanel1.add(crearPoblacionButton);
        buttonPanel1.add(editarPoblacionButton);
        buttonPanel1.add(eliminarPoblacionButton);
        poblacionListPanel.add(new JScrollPane(poblacionList), BorderLayout.CENTER);
        poblacionListPanel.add(buttonPanel1, BorderLayout.SOUTH);
        // Establecer un tamaño preferido
        //poblacionListPanel.setPreferredSize(new Dimension(500, 300)); // Establecer un tamaño preferido
        // Agregar los listeners a los botones
        crearPoblacionButton.addActionListener(e -> crearNuevaPoblacion());
        editarPoblacionButton.addActionListener(e -> editarPoblacion(experimentoList.getSelectedValue()));
        eliminarPoblacionButton.addActionListener(e -> eliminarPoblacion());

        JPanel poblacionDetailsPanel = new JPanel(new BorderLayout());
        poblacionDetailsPanel.add(new JScrollPane(poblacionDetails), BorderLayout.CENTER);


        // Crear el panel que contendrá los otros dos paneles
        JPanel panelDerecho = new JPanel(new GridLayout(2, 1));

        // Agregar los paneles al panelDerecho
        panelDerecho.add(poblacionListPanel);
        panelDerecho.add(poblacionDetailsPanel);

        // Agregar el panelDerecho al JFrame
        add(panelDerecho, BorderLayout.CENTER);

        // Agregar los paneles al JFrame
        add(experimentoListPanel, BorderLayout.WEST);
    }


    ////////////METODOS PARA EXPERIMENTO////////////////////////
    private void crearNuevoExperimento() {
        String nombre = JOptionPane.showInputDialog(this, "Introduce el nombre del experimento:");
        if (nombre != null && !nombre.isEmpty()) {
            PoblacionBacteriasManager nuevoExperimento = new PoblacionBacteriasManager();
            nuevoExperimento.setName(nombre);
            experimentoManager.addPopulation(nuevoExperimento);
            updateExperimentoList();
        }
    }
    private void abrirExperimento() {
        String nombre = JOptionPane.showInputDialog(this, "Introduce el nombre del experimento que quieres abrir:");
        if (nombre != null && !nombre.isEmpty()) {
            // Cargar los datos del experimento
            ExperimentoManager experimento = new ExperimentoData().cargarDatos("./experimentos/" + nombre + "/experimento.dat");
            if (experimento != null) {
                experimentoManager = experimento;
                updateExperimentoList();

                // Cargar los datos de las poblaciones de bacterias
                File experimentoDir = new File("./experimentos/" + nombre);
                for (File file : experimentoDir.listFiles()) {
                    if (file.isFile() && file.getName().endsWith(".dat") && !file.getName().equals("experimento.dat")) {
                        PoblacionBacteriasManager poblacion = new PoblacionBacteriasData().cargarDatos(file.getPath());
                        if (poblacion != null) {
                            experimento.addPopulation(poblacion);
                        }
                    }
                }
                PoblacionBacteriasManager selectedExperimento = experimentoList.getSelectedValue();
                updateExperimentoList();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo abrir el experimento.");
            }
        }
    }
    private void borrarExperimento() {
        PoblacionBacteriasManager experimentoSeleccionado = experimentoList.getSelectedValue();
        if (experimentoSeleccionado != null) {
            experimentoManager.removePopulation(experimentoSeleccionado);
            updateExperimentoList();
        }
    }
    private void guardarExperimento() {
        PoblacionBacteriasManager experimentoSeleccionado = experimentoList.getSelectedValue();
        if (experimentoSeleccionado != null) {
            new ExperimentoData().guardarDatos(experimentoSeleccionado, "./experimentos/" + experimentoSeleccionado.toString() + ".dat");
        }
    }
    private void guardarExperimentoComo() {
        String nombre = JOptionPane.showInputDialog(this, "Introduce el nuevo nombre para el experimento:");
        if (nombre != null && !nombre.isEmpty()) {
            PoblacionBacteriasManager selectedExperimento = experimentoList.getSelectedValue();
            if (selectedExperimento != null) {
                // Cambiar el nombre del experimento en la lista de experimentos
                selectedExperimento.setName(nombre);
                updateExperimentoList();

                // Guardar los datos del experimento
                new ExperimentoData().guardarDatos(selectedExperimento, "./experimentos/" + nombre + "/experimento.dat");

                // Guardar los datos de las poblaciones de bacterias
                for (PoblacionBacteriasManager poblacion : selectedExperimento.getPoblaciones()) {
                    new PoblacionBacteriasData().guardarDatos(poblacion, "./experimentos/" + nombre + "/" + poblacion.getName() + ".dat");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No hay ningún experimento seleccionado.");
            }
        }
    }
    private void updateExperimentoList() {
        List<PoblacionBacteriasManager> experimentos = experimentoManager.getPopulations();
        DefaultListModel<PoblacionBacteriasManager> model = new DefaultListModel<>();
        for (PoblacionBacteriasManager experimento : experimentos) {
            model.addElement(experimento);
        }
        experimentoList.setModel(model);
    }

    //////////////////METODOS PARA POBLACION////////////////////////

    private void verPoblaciones() {
        // Implementa la lógica para ver las poblaciones de bacterias
    }
    private void crearNuevaPoblacion() {
        String nombre = JOptionPane.showInputDialog(this, "Introduce el nombre de la población:");
        if (nombre != null && !nombre.isEmpty()) {
            PoblacionBacteriasManager nuevaPoblacion = new PoblacionBacteriasManager();
            nuevaPoblacion.setName(nombre);
            poblacionBacteriasManager.addPoblacion(nuevaPoblacion);
            updatePoblacionList();
        }
    }
    private void editarPoblacion(PoblacionBacteriasManager selectedValue) {
        PoblacionBacteriasManager poblacionSeleccionada = poblacionList.getSelectedValue();
        if (poblacionSeleccionada != null) {
            String nuevoNombre = JOptionPane.showInputDialog(this, "Introduce el nuevo nombre de la población:");
            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                poblacionSeleccionada.setName(nuevoNombre);
                poblacionBacteriasManager.updatePoblacion(poblacionSeleccionada);
                updatePoblacionList();
            }
        }
    }
    private void eliminarPoblacion() {
        PoblacionBacteriasManager poblacionSeleccionada = poblacionList.getSelectedValue();
        if (poblacionSeleccionada != null) {
            poblacionBacteriasManager.removePoblacion(poblacionSeleccionada);
            updatePoblacionList();
        }
    }
    private void updatePoblacionList() {
        List<PoblacionBacteriasManager> poblaciones = poblacionBacteriasManager.getPoblaciones();
        DefaultListModel<PoblacionBacteriasManager> model = new DefaultListModel<>();
        for (PoblacionBacteriasManager poblacion : poblaciones) {
            model.addElement(poblacion);
        }
        poblacionList.setModel(model);
    }
    private void verDetallesPoblacion() {
        // Implementa la lógica para ver los detalles de una población de bacterias
    }
}