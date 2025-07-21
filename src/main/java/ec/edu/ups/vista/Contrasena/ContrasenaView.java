package ec.edu.ups.vista.Contrasena;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Vista interna que permite al usuario recuperar su contraseña mediante
 * validación de preguntas de seguridad.
 */
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
    private JLabel lblUsuario;
    private JLabel lblContrasenaNueva;
    private JLabel lblConfirmarContraseba;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    /**
     * Constructor que inicializa todos los componentes gráficos y configura los iconos.
     * @param handler manejador de internacionalización para los textos.
     */
    public ContrasenaView(MensajeInternacionalizacionHandler handler) {
        super("Recuperar Contraseña", true, true, false, true);
        this.mensajeInternacionalizacionHandler = handler;
        setSize(400, 350);
        setLayout(new GridLayout(8, 2));

        // Inicialización de componentes
        lblUsuario = new JLabel("Usuario:");
        txtUsername = new JTextField();
        btnValidarUsuario = new JButton("Validar Usuario");

        lblPregunta1 = new JLabel("Pregunta 1:");
        txtRespuesta1 = new JTextField();
        lblPregunta2 = new JLabel("Pregunta 2:");
        txtRespuesta2 = new JTextField();
        lblPregunta3 = new JLabel("Pregunta 3:");
        txtRespuesta3 = new JTextField();

        lblContrasenaNueva = new JLabel("Nueva Contraseña:");
        txtNuevaContrasena = new JPasswordField();

        lblConfirmarContraseba = new JLabel("Confirmar Contraseña:");
        txtConfirmarContrasena = new JPasswordField();

        btnGuardar = new JButton("Guardar Contraseña");

        // Agregado de componentes
        add(lblUsuario);
        add(txtUsername);
        add(btnValidarUsuario);
        add(new JLabel());

        add(lblPregunta1);
        add(txtRespuesta1);
        add(lblPregunta2);
        add(txtRespuesta2);
        add(lblPregunta3);
        add(txtRespuesta3);

        add(lblContrasenaNueva);
        add(txtNuevaContrasena);
        add(lblConfirmarContraseba);
        add(txtConfirmarContrasena);

        add(btnGuardar);
        add(new JLabel());

        // Configuración de textos e íconos
        actualizarTextos();
        aplicarIconos();
    }

    /**
     * Asocia iconos a los componentes visuales si los recursos están disponibles.
     */
    private void aplicarIconos() {
        URL iconoUsuarioURL = ContrasenaView.class.getClassLoader().getResource("imagenes/usuariovalidar.png");
        if (iconoUsuarioURL != null) {
            ImageIcon icono = new ImageIcon(iconoUsuarioURL);
            Image img = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblUsuario.setIcon(new ImageIcon(img));
            lblUsuario.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

        URL preguntaIconURL = ContrasenaView.class.getClassLoader().getResource("imagenes/preguntarecuperar.png");
        if (preguntaIconURL != null) {
            ImageIcon icono = new ImageIcon(preguntaIconURL);
            Image imagenEscalada = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            Icon iconoPregunta = new ImageIcon(imagenEscalada);

            lblPregunta1.setIcon(iconoPregunta);
            lblPregunta2.setIcon(iconoPregunta);
            lblPregunta3.setIcon(iconoPregunta);

            lblPregunta1.setHorizontalTextPosition(SwingConstants.RIGHT);
            lblPregunta2.setHorizontalTextPosition(SwingConstants.RIGHT);
            lblPregunta3.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

        URL nuevaContraURL = ContrasenaView.class.getClassLoader().getResource("imagenes/nuevacontra.png");
        if (nuevaContraURL != null) {
            ImageIcon icono = new ImageIcon(nuevaContraURL);
            Image imagenEscalada = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblContrasenaNueva.setIcon(new ImageIcon(imagenEscalada));
            lblContrasenaNueva.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

        URL confirmarContraURL = ContrasenaView.class.getClassLoader().getResource("imagenes/confirmarcontra.png");
        if (confirmarContraURL != null) {
            ImageIcon icono = new ImageIcon(confirmarContraURL);
            Image imagenEscalada = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblConfirmarContraseba.setIcon(new ImageIcon(imagenEscalada));
            lblConfirmarContraseba.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

        URL validarURL = ContrasenaView.class.getClassLoader().getResource("imagenes/validar.png");
        if (validarURL != null) {
            ImageIcon icono = new ImageIcon(validarURL);
            Image img = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnValidarUsuario.setIcon(new ImageIcon(img));
            btnValidarUsuario.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

        URL guardarURL = ContrasenaView.class.getClassLoader().getResource("imagenes/guardar.png");
        if (guardarURL != null) {
            ImageIcon icono = new ImageIcon(guardarURL);
            Image imagenEscalada = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnGuardar.setIcon(new ImageIcon(imagenEscalada));
            btnGuardar.setHorizontalTextPosition(SwingConstants.RIGHT);
        }
    }

    // Getters
    public JTextField getTxtUsername() { return txtUsername; }

    public JButton getBtnValidarUsuario() { return btnValidarUsuario; }

    public JLabel getLblPregunta1() { return lblPregunta1; }

    public JLabel getLblPregunta2() { return lblPregunta2; }

    public JLabel getLblPregunta3() { return lblPregunta3; }

    public JTextField getTxtRespuesta1() { return txtRespuesta1; }

    public JTextField getTxtRespuesta2() { return txtRespuesta2; }

    public JTextField getTxtRespuesta3() { return txtRespuesta3; }

    public JPasswordField getTxtNuevaContrasena() { return txtNuevaContrasena; }

    public JPasswordField getTxtConfirmarContrasena() { return txtConfirmarContrasena; }

    public JButton getBtnGuardar() { return btnGuardar; }

    /**
     * Muestra un mensaje emergente en la vista.
     * @param mensaje el texto a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Establece las preguntas que se deben mostrar al usuario.
     * @param p1 pregunta 1.
     * @param p2 pregunta 2.
     * @param p3 pregunta 3.
     */
    public void setPreguntas(String p1, String p2, String p3) {
        lblPregunta1.setText(p1);
        lblPregunta2.setText(p2);
        lblPregunta3.setText(p3);
    }

    /**
     * Establece el nombre de usuario en el campo correspondiente.
     * @param username nombre de usuario.
     */
    public void setUsername(String username) {
        txtUsername.setText(username);
    }

    /**
     * Actualiza los textos mostrados en la interfaz según el idioma actual.
     */
    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("contrasena.titulo"));
        lblUsuario.setText(mensajeInternacionalizacionHandler.get("login.usuario"));
        btnValidarUsuario.setText(mensajeInternacionalizacionHandler.get("boton.validar"));
        lblPregunta1.setText(mensajeInternacionalizacionHandler.get("contrasena.pregunta1"));
        lblPregunta2.setText(mensajeInternacionalizacionHandler.get("contrasena.pregunta2"));
        lblPregunta3.setText(mensajeInternacionalizacionHandler.get("contrasena.pregunta3"));
        lblContrasenaNueva.setText(mensajeInternacionalizacionHandler.get("contrasena.nueva"));
        lblConfirmarContraseba.setText(mensajeInternacionalizacionHandler.get("contrasena.confirmar"));
        btnGuardar.setText(mensajeInternacionalizacionHandler.get("boton.guardar"));
    }
}
