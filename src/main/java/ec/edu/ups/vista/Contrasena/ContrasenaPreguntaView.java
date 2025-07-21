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

/**
 * Vista interna que muestra preguntas de seguridad al usuario
 * para que las responda como parte del proceso de registro.
 */
public class ContrasenaPreguntaView extends JInternalFrame {

    /** Etiquetas para mostrar las preguntas. */
    private List<JLabel> etiquetas;

    /** Campos de texto para ingresar las respuestas. */
    private List<JTextField> respuestas;

    /** Botón para guardar las respuestas. */
    private JButton btnGuardar;

    /** Nombre de usuario asociado a las respuestas. */
    private String username;

    /** Manejador de mensajes internacionalizados. */
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    /**
     * Constructor que inicializa la vista con preguntas obtenidas del DAO en memoria.
     *
     * @param handler el manejador de internacionalización.
     */
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

    /**
     * Aplica un ícono decorativo a las etiquetas de las preguntas.
     */
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

    /**
     * Actualiza los textos de la interfaz según el idioma actual.
     */
    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("contrasena.pregunta.titulo"));
        for (int i = 0; i < etiquetas.size(); i++) {
            String clave = "pregunta." + (i + 1); // clave tipo: pregunta.1
            etiquetas.get(i).setText(mensajeInternacionalizacionHandler.get(clave));
        }
        btnGuardar.setText(mensajeInternacionalizacionHandler.get("boton.guardar"));
    }

    /**
     * Retorna el botón para guardar las respuestas.
     * @return botón guardar.
     */
    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    /**
     * Muestra un mensaje emergente al usuario.
     * @param mensaje el texto del mensaje.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Establece el nombre de usuario actual.
     * @param username nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene el nombre de usuario actual.
     * @return nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Devuelve una lista con las respuestas escritas por el usuario.
     * @return lista de respuestas.
     */
    public List<String> getRespuestas() {
        List<String> respuestasTexto = new ArrayList<>();
        for (JTextField campo : respuestas) {
            respuestasTexto.add(campo.getText().trim());
        }
        return respuestasTexto;
    }

    /**
     * Limpia todos los campos de respuesta.
     */
    public void limpiarCampos() {
        respuestas.forEach(t -> t.setText(""));
    }

}
