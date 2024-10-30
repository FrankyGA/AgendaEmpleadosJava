import javax.swing.SwingUtilities;

import view.EmpleadoView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EmpleadoView empleadoView = new EmpleadoView();
            empleadoView.setVisible(true);
        });
    }
}