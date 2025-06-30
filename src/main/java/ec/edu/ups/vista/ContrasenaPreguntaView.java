package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;

public class ContrasenaPreguntaView extends JInternalFrame {

    private JLabel lblPregunta1;
    private JLabel lblPregunta2;
    private JTextField txtRespuesta1;
    private JTextField txtRespuesta2;
    private JButton btnGuardar;
    private String username; // guardamos internamente el nombre

    public ContrasenaPreguntaView() {
        super("Preguntas de Seguridad", true, true, false, true);
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        lblPregunta1 = new JLabel("¿Cuál es el nombre de tu madre?");
        lblPregunta2 = new JLabel("¿Cuál es tu equipo favorito de fútbol?");
        txtRespuesta1 = new JTextField();
        txtRespuesta2 = new JTextField();
        btnGuardar = new JButton("Guardar");

        add(lblPregunta1);
        add(txtRespuesta1);
        add(lblPregunta2);
        add(txtRespuesta2);
        add(new JLabel()); // espacio
        add(btnGuardar);
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

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }
}
