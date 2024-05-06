import logica.ExperimentoManager;
import logica.PoblacionBacteriasManager;
import ui.MenuPrincipalUI;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private Main mainFrame;
    private MenuPrincipalUI menuPrincipalUI;

    public LoginFrame(Main mainFrame, ExperimentoManager experimentoManager, PoblacionBacteriasManager poblacionBacteriasManager) {
        this.mainFrame = mainFrame;
        this.menuPrincipalUI = new MenuPrincipalUI(experimentoManager, poblacionBacteriasManager);

        // Configuración del JFrame
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout del JFrame
        setLayout(new GridLayout(3, 2));

        // Etiquetas y campos de texto
        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField();
        JLabel passwordLabel = new JLabel("Contraseña:");
        JPasswordField passwordField = new JPasswordField();

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Acceso al sistema");
        loginButton.addActionListener(e -> {
            // Aquí puedes agregar la lógica para verificar el nombre de usuario y la contraseña
            JOptionPane.showMessageDialog(this, "Acceso exitoso");
            this.setVisible(false);
            menuPrincipalUI.setVisible(true); // Muestra la interfaz de usuario principal después de un inicio de sesión exitoso
        });

        // Agregar componentes al JFrame
        add(userLabel);
        add(userField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Espacio vacío
        add(loginButton);
    }

    public static void main(String[] args) {
        // Crear y mostrar el JFrame
        SwingUtilities.invokeLater(() -> {
            ExperimentoManager experimentoManager = new ExperimentoManager();
            PoblacionBacteriasManager poblacionBacteriasManager = new PoblacionBacteriasManager();
            LoginFrame frame = new LoginFrame(new Main(), experimentoManager, poblacionBacteriasManager);
            frame.setVisible(true);
        });
    }
}