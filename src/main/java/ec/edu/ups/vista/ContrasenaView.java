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
    private JPanel panelUsuarioValidar;
    private JPanel panelPreguntas;
    private JPanel panelContrasenas;
    private JPanel panelBoton;
    private JTextField txtRespuesta3;
    private JLabel lblPregunta3;

    public ContrasenaView() {
        super("Recuperar Contraseña", true, true, false, true);
        setSize(400, 350);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Usuario:"));
        txtUsername = new JTextField();
        add(txtUsername);

        btnValidarUsuario = new JButton("Validar Usuario");
        add(btnValidarUsuario);
        add(new JLabel());

        lblPregunta1 = new JLabel("Pregunta 1:");
        add(lblPregunta1);
        txtRespuesta1 = new JTextField();
        add(txtRespuesta1);

        lblPregunta2 = new JLabel("Pregunta 2:");
        add(lblPregunta2);
        txtRespuesta2 = new JTextField();
        add(txtRespuesta2);

        lblPregunta3 = new JLabel("Pregunta 3:");
        add(lblPregunta3);
        txtRespuesta3 = new JTextField();
        add(txtRespuesta3);

        add(new JLabel("Nueva Contraseña:"));
        txtNuevaContrasena = new JPasswordField();
        add(txtNuevaContrasena);

        btnGuardar = new JButton("Guardar Contraseña");
        add(btnGuardar);
        add(new JLabel());
    }

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

    public JLabel getLblPregunta3() {return lblPregunta3;}

    public JTextField getTxtRespuesta1() {
        return txtRespuesta1;
    }

    public JTextField getTxtRespuesta2() {
        return txtRespuesta2;
    }

    public JTextField getTxtRespuesta3() {return txtRespuesta3;}

    public JPasswordField getTxtNuevaContrasena() {
        return txtNuevaContrasena;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void setPreguntas(String p1, String p2, String p3) {
        lblPregunta1.setText(p1);
        lblPregunta2.setText(p2);
        lblPregunta3.setText(p3);
    }
    public void setUsername(String username) {
        txtUsername.setText(username);
    }

}
