import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Crear y mostrar el JFrame
        SwingUtilities.invokeLater(() -> {
            Main frame = new Main();
            frame.setVisible(true);
        });
    }

    private void setVisible(boolean b) {
    }
}