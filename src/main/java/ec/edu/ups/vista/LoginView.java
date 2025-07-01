package ec.edu.ups.vista;

import javax.swing.*;

public class LoginView extends JFrame {

    // Estos componentes son generados automáticamente por IntelliJ IDEA a partir del .form
    private JPanel panelPrincipal;
    private JTextField txtUsername;
    private JPasswordField txtContrasenia;
    private JButton btnIniciarSesion;
    private JButton btnRegistrarse;
    private JButton btnOlvidoContrasena;
    private JPanel panelCredenciales;
    private JPanel panelBotones;
    private JPanel panelSecundario;
    private JLabel nombreLabel;
    private JLabel contraseñaLabel;

    public LoginView() {
        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Establece el panel principal diseñado en el .form
        setContentPane(panelPrincipal);

        // Ajusta el tamaño automáticamente según los componentes
        pack();

        // Centra la ventana en la pantalla
        setLocationRelativeTo(null);

        // Opcional: visible solo cuando lo invoques desde tu Main
        setVisible(false); // o true si quieres mostrarlo aquí
    }

    // Métodos getters para que tus controladores puedan acceder a los botones y campos

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public JButton getBtnOlvidoContrasena() {
        return btnOlvidoContrasena;
    }

    // Método para mostrar mensajes en ventana
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Método opcional para limpiar campos
    public void limpiarCampos() {
        txtUsername.setText("");
        txtContrasenia.setText("");
    }
}
