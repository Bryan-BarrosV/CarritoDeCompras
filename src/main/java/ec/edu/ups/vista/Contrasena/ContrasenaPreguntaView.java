package ec.edu.ups.vista.Contrasena;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.dao.impl.Memoria.PreguntaDAOMemoria;
import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ContrasenaPreguntaView extends JInternalFrame {

    private List<JLabel> etiquetas;
    private List<JTextField> respuestas;
    private JButton btnGuardar;
    private String username;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public ContrasenaPreguntaView(MensajeInternacionalizacionHandler handler) {
        super("Preguntas de Seguridad", true, true, false, true);
        this.mensajeInternacionalizacionHandler = handler;

        this.etiquetas = new ArrayList<>();
        this.respuestas = new ArrayList<>();

        PreguntaDAO preguntaDAO = new PreguntaDAOMemoria(mensajeInternacionalizacionHandler);
        List<Pregunta> preguntas = preguntaDAO.obtenerTodas();

        setLayout(new GridLayout(preguntas.size() + 1, 2));
        setSize(450, 350);

        for (Pregunta pregunta : preguntas) {
            JLabel lbl = new JLabel();
            JTextField txt = new JTextField();
            etiquetas.add(lbl);
            respuestas.add(txt);
            add(lbl);
            add(txt);
        }

        btnGuardar = new JButton();
        add(new JLabel());
        add(btnGuardar);

        aplicarIcono();
        actualizarTextos();
    }

    private void aplicarIcono() {
        URL iconoURL = ContrasenaPreguntaView.class.getClassLoader().getResource("imagenes/preguntamensajes.png");
        if (iconoURL != null) {
            ImageIcon iconoPregunta = new ImageIcon(iconoURL);
            Image img = iconoPregunta.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            Icon icono = new ImageIcon(img);

            for (JLabel label : etiquetas) {
                label.setIcon(icono);
                label.setHorizontalTextPosition(SwingConstants.RIGHT);
                label.setVerticalTextPosition(SwingConstants.CENTER);
            }
        } else {
            System.err.println("No se encontró el icono preguntamensajes.png");
        }
    }

    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("contrasena.pregunta.titulo"));
        for (int i = 0; i < etiquetas.size(); i++) {
            String clave = "pregunta." + (i + 1); // clave tipo: pregunta.1
            etiquetas.get(i).setText(mensajeInternacionalizacionHandler.get(clave));
        }
        btnGuardar.setText(mensajeInternacionalizacionHandler.get("boton.guardar"));
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
        return username;
    }

    public List<String> getRespuestas() {
        List<String> respuestasTexto = new ArrayList<>();
        for (JTextField campo : respuestas) {
            respuestasTexto.add(campo.getText().trim());
        }
        return respuestasTexto;
    }


    public void limpiarCampos() {
        respuestas.forEach(t -> t.setText(""));
    }

}
