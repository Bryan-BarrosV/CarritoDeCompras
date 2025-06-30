package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;

public class ContrasenaView extends JInternalFrame {

    private JTextField txtUsername;
    private JButton btnValidarUsuario;
    private JLabel lblPregunta1;
    private JLabel lblPregunta2;
    private JTextField txtRespuesta1;
    private JTextField txtRespuesta2;
    private JPasswordField txtNuevaContrasena;
    private JButton btnGuardar;
    private JPasswordField txtConfirmarContrasena;

    public ContrasenaView() {
        super("Recuperar Contraseña", true, true, false, true);
        setSize(400, 350);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Usuario:"));
        txtUsername = new JTextField();
        add(txtUsername);

        btnValidarUsuario = new JButton("Validar Usuario");
        add(btnValidarUsuario);
        add(new JLabel()); // espacio vacío para balancear

        lblPregunta1 = new JLabel("Pregunta 1:");
        add(lblPregunta1);
        txtRespuesta1 = new JTextField();
        add(txtRespuesta1);

        lblPregunta2 = new JLabel("Pregunta 2:");
        add(lblPregunta2);
        txtRespuesta2 = new JTextField();
        add(txtRespuesta2);

        add(new JLabel("Nueva Contraseña:"));
        txtNuevaContrasena = new JPasswordField();
        add(txtNuevaContrasena);

        btnGuardar = new JButton("Guardar Contraseña");
        add(btnGuardar);
        add(new JLabel());
    }

    // Getters
    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JButton getBtnValidarUsuario() {
        return btnValidarUsuario;
    }

    public JLabel getLblPregunta1() {
        return lblPregunta1;
    }

    public JLabel getLblPregunta2() {
        return lblPregunta2;
    }

    public JTextField getTxtRespuesta1() {
        return txtRespuesta1;
    }

    public JTextField getTxtRespuesta2() {
        return txtRespuesta2;
    }

    public JPasswordField getTxtNuevaContrasena() {
        return txtNuevaContrasena;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void setPreguntas(String p1, String p2) {
        lblPregunta1.setText(p1);
        lblPregunta2.setText(p2);
    }
    public void setUsername(String username) {
        txtUsername.setText(username);
    }

}
