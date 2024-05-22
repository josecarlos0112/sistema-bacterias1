import data.ExperimentoData;
import logica.ExperimentoManager;
import logica.PoblacionBacteriasManager;
import ui.MenuPrincipalUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;

public class Main extends JFrame {
    private MenuPrincipalUI menuPrincipalUI;

    public Main() {
        // Configuración del JFrame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        setLocation(width / 2 - 500, height / 2 - 300);
        String iconPath = "src/img/uax-corto.png";
        ImageIcon icon = new ImageIcon(iconPath);
        setIconImage(icon.getImage());
        setTitle("Sistema de Gestión y Análisis de Datos Multidimensionales");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout del JFrame
        setLayout(new BorderLayout());

        // Panel izquierdo
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setPreferredSize(new Dimension(500, 600));
        panelIzquierdo.setBackground(Color.GRAY);

        // Imagen
        ImageIcon imagen = new ImageIcon("src/img/logouax(1).png"); // Ajusta la ruta de la imagen según tu ubicación
        Image logouax = imagen.getImage();
        Image newimg = logouax.getScaledInstance(443, 95, java.awt.Image.SCALE_SMOOTH);//Para cambiarle el tamaño a la imagen
        imagen = new ImageIcon(newimg);//Agrega la imagen al JLabel
        JLabel labelImagen = new JLabel(imagen);
        labelImagen.setAlignmentX(Component.CENTER_ALIGNMENT);//Centra la imagen en el panel
        panelIzquierdo.add(Box.createVerticalGlue());//Espacio en la parte superior
        panelIzquierdo.add(labelImagen);//Agrega la imagen al panel
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 10)));//Espacio entre la imagen y el texto debajo

        // Frase
        JLabel labelFrase = new JLabel("Aplicación para gestionar cultivos de bacterias");
        labelFrase.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelFrase.setFont(new Font("Arial", Font.BOLD, 20));
        panelIzquierdo.add(labelFrase);
        panelIzquierdo.add(Box.createVerticalGlue());

        // Panel derecho
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new GridBagLayout());
        panelDerecho.setBackground(Color.LIGHT_GRAY);

        // Campos de texto para el nombre de usuario y la contraseña
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField userField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JPasswordField passwordField = new JPasswordField(15);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Acceso al sistema");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setPreferredSize(new Dimension(200, 30));
        loginButton.addActionListener(e -> {
            // Aquí puedes agregar la lógica para verificar el nombre de usuario y la contraseña
            JOptionPane.showMessageDialog(this, "Acceso exitoso");
            this.setVisible(false);
            menuPrincipalUI.setVisible(true); // Muestra la interfaz de usuario principal después de un inicio de sesión exitoso
        });

        // Agregar componentes al panel derecho
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panelDerecho.add(userLabel, constraints);

        constraints.gridx = 1;
        panelDerecho.add(userField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panelDerecho.add(passwordLabel, constraints);

        constraints.gridx = 1;
        panelDerecho.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(loginButton, constraints);

        // Panel inferior
        JPanel panelInferior = new JPanel();
        JLabel labelAutor = new JLabel("Derechos Reservados © 2024 - José Zorrilla");
        panelInferior.add(labelAutor);

        // Agregar componentes al JFrame
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Centrar JFrame en la pantalla
        setLocationRelativeTo(null);

        // Inicializar la interfaz de usuario principal
        ExperimentoManager experimentoManager = new ExperimentoManager();
        PoblacionBacteriasManager poblacionBacteriasManager = new PoblacionBacteriasManager();
        menuPrincipalUI = new MenuPrincipalUI(experimentoManager, poblacionBacteriasManager);
    }

    public static void main(String[] args) {
        // Crear y mostrar el JFrame
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
        crearExperimentosPorDefecto();
    }
    private static void crearExperimentosPorDefecto() {
        File directory = new File("resources/experimentos");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        crearExperimentoSiNoExiste("resources/experimentos/experimento1.txt");
        crearExperimentoSiNoExiste("resources/experimentos/experimento2.txt");
    }
    private static void crearExperimentoSiNoExiste(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            ExperimentoManager experimento = new ExperimentoManager();
            PoblacionBacteriasManager poblacion1 = new PoblacionBacteriasManager();
            poblacion1.setName("Poblacion 1");
            poblacion1.setStartDate(LocalDate.now());
            poblacion1.setEndDate(LocalDate.now().plusDays(10));
            poblacion1.setInitialBacteriaCount(100);
            poblacion1.setTemperature(37.0);
            poblacion1.setLightCondition(String.valueOf(true));
            poblacion1.setInitialFood(100);
            poblacion1.setIncrementDay(1);
            poblacion1.setIncrementFood(10);
            poblacion1.setFinalFood(200);
            experimento.addPopulation(poblacion1);

            PoblacionBacteriasManager poblacion2 = new PoblacionBacteriasManager();
            poblacion2.setName("Poblacion 2");
            poblacion2.setStartDate(LocalDate.now());
            poblacion2.setEndDate(LocalDate.now().plusDays(10));
            poblacion2.setInitialBacteriaCount(200);
            poblacion2.setTemperature(37.0);
            poblacion2.setLightCondition(String.valueOf(true));
            poblacion2.setInitialFood(200);
            poblacion2.setIncrementDay(2);
            poblacion2.setIncrementFood(20);
            poblacion2.setFinalFood(400);
            experimento.addPopulation(poblacion2);

           ExperimentoData data = new ExperimentoData();
           data.guardarDatos(poblacion1, filename);
           data.guardarDatos(poblacion2, filename);
        }
    }
}