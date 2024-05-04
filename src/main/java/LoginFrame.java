import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        // Configuración del JFrame
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout del JFrame
        setLayout(new GridLayout(3, 2));

        // Etiquetas y campos de texto
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> {
            // Aquí puedes verificar las credenciales del usuario si es necesario
            // Como no necesitamos verificar, simplemente mostramos un mensaje
            JOptionPane.showMessageDialog(this, "Login Successful");
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
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }
}