package ec.edu.ups.vista.Contrasena;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ContrasenaPreguntaView extends JInternalFrame {

    private JLabel lblPregunta1;
    private JLabel lblPregunta2;
    private JTextField txtRespuesta1;
    private JTextField txtRespuesta2;
    private JButton btnGuardar;
    private JTextField txtRespuesta3;
    private JTextField txtRespuesta4;
    private JTextField txtRespuesta5;
    private JTextField txtRespuesta6;
    private JTextField txtRespuesta7;
    private JTextField txtRespuesta8;
    private JTextField txtRespuesta9;
    private JTextField txtRespuesta10;
    private JLabel lblPregunta3;
    private JLabel lblPregunta4;
    private JLabel lblPregunta5;
    private JLabel lblPregunta6;
    private JLabel lblPregunta7;
    private JLabel lblPregunta8;
    private JLabel lblPregunta9;
    private JLabel lblPregunta10;
    private String username;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public ContrasenaPreguntaView(MensajeInternacionalizacionHandler handler) {
        super("Preguntas de Seguridad", true, true, false, true);
        this.mensajeInternacionalizacionHandler=handler;
        setSize(400, 300);
        setLayout(new GridLayout(11, 2));

        lblPregunta1 = new JLabel("¿Cuál es el nombre de tu madre?");
        lblPregunta2 = new JLabel("¿Cuál es tu equipo favorito de fútbol?");
        lblPregunta3=new JLabel("¿Cuál es su color favorito?");
        lblPregunta4=new JLabel("¿Cuál es el segundo nombre de tu padre?");
        lblPregunta5=new JLabel("¿Cómo se llama tu mascota?");
        lblPregunta6=new JLabel("¿En qué ciudad naciste?");
        lblPregunta7=new JLabel("¿Cuál es tu película favorita?");
        lblPregunta8=new JLabel("¿Cuál es tu comida favorita?");
        lblPregunta9=new JLabel("¿Cuál es tu canción favorita?");
        lblPregunta10=new JLabel("¿Cómo se llama tu abuela?");

        txtRespuesta1 = new JTextField();
        txtRespuesta2 = new JTextField();
        txtRespuesta3 = new JTextField();
        txtRespuesta4 = new JTextField();
        txtRespuesta5 = new JTextField();
        txtRespuesta6 = new JTextField();
        txtRespuesta7 = new JTextField();
        txtRespuesta8 = new JTextField();
        txtRespuesta9 = new JTextField();
        txtRespuesta10 = new JTextField();
        btnGuardar = new JButton("Guardar");

        add(lblPregunta1);
        add(txtRespuesta1);
        add(lblPregunta2);
        add(txtRespuesta2);
        add(lblPregunta3);
        add(txtRespuesta3);
        add(lblPregunta4);
        add(txtRespuesta4);
        add(lblPregunta5);
        add(txtRespuesta5);
        add(lblPregunta6);
        add(txtRespuesta6);
        add(lblPregunta7);
        add(txtRespuesta7);
        add(lblPregunta8);
        add(txtRespuesta8);
        add(lblPregunta9);
        add(txtRespuesta9);
        add(lblPregunta10);
        add(txtRespuesta10);
        add(new JLabel());
        add(btnGuardar);

        actualizarTextos();

        URL iconoURL = ContrasenaPreguntaView.class.getClassLoader().getResource("imagenes/preguntamensajes.png");
        if (iconoURL != null) {
            ImageIcon iconoPregunta = new ImageIcon(iconoURL);
            Image img = iconoPregunta.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            Icon icono = new ImageIcon(img);

            JLabel[] etiquetas = {
                    lblPregunta1, lblPregunta2, lblPregunta3, lblPregunta4, lblPregunta5,
                    lblPregunta6, lblPregunta7, lblPregunta8, lblPregunta9, lblPregunta10
            };

            for (JLabel label : etiquetas) {
                label.setIcon(icono);
                label.setHorizontalTextPosition(SwingConstants.RIGHT);
                label.setVerticalTextPosition(SwingConstants.CENTER);
            }
        } else {
            System.err.println("No se encontró el icono preguntamensajes.png");
        }

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

    public JTextField getTxtRespuesta3() {
        return txtRespuesta3;
    }

    public JTextField getTxtRespuesta4() {
        return txtRespuesta4;
    }

    public JTextField getTxtRespuesta5() {
        return txtRespuesta5;
    }

    public JTextField getTxtRespuesta6() {
        return txtRespuesta6;
    }

    public JTextField getTxtRespuesta7() {
        return txtRespuesta7;
    }

    public JTextField getTxtRespuesta8() {
        return txtRespuesta8;
    }

    public JTextField getTxtRespuesta9() {
        return txtRespuesta9;
    }

    public JTextField getTxtRespuesta10() {
        return txtRespuesta10;
    }

    public JLabel getLblPregunta3() {
        return lblPregunta3;
    }

    public JLabel getLblPregunta4() {
        return lblPregunta4;
    }

    public JLabel getLblPregunta5() {
        return lblPregunta5;
    }

    public JLabel getLblPregunta6() {
        return lblPregunta6;
    }

    public JLabel getLblPregunta7() {
        return lblPregunta7;
    }

    public JLabel getLblPregunta8() {
        return lblPregunta8;
    }

    public JLabel getLblPregunta9() {
        return lblPregunta9;
    }

    public JLabel getLblPregunta10() {
        return lblPregunta10;
    }

    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("contrasena.pregunta.titulo"));
        lblPregunta1.setText(mensajeInternacionalizacionHandler.get("pregunta.1"));
        lblPregunta2.setText(mensajeInternacionalizacionHandler.get("pregunta.2"));
        lblPregunta3.setText(mensajeInternacionalizacionHandler.get("pregunta.3"));
        lblPregunta4.setText(mensajeInternacionalizacionHandler.get("pregunta.4"));
        lblPregunta5.setText(mensajeInternacionalizacionHandler.get("pregunta.5"));
        lblPregunta6.setText(mensajeInternacionalizacionHandler.get("pregunta.6"));
        lblPregunta7.setText(mensajeInternacionalizacionHandler.get("pregunta.7"));
        lblPregunta8.setText(mensajeInternacionalizacionHandler.get("pregunta.8"));
        lblPregunta9.setText(mensajeInternacionalizacionHandler.get("pregunta.9"));
        lblPregunta10.setText(mensajeInternacionalizacionHandler.get("pregunta.10"));
        btnGuardar.setText(mensajeInternacionalizacionHandler.get("boton.guardar"));
    }
}
