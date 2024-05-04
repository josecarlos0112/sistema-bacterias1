import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private Main mainFrame;
    public LoginFrame(Main mainFrame) {
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
            JOptionPane.showMessageDialog(this, "Acceso exitoso");
            this.setVisible(false);
            mainFrame.setVisible(true);
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
            LoginFrame frame = new LoginFrame(  new Main());
            frame.setVisible(true);
        });
    }
}