package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Clase que representa la ventana de inicio de sesión del sistema.
 * Incluye campos para usuario, contraseña, botones de acción, y menús
 * de configuración e idioma. Integra internacionalización dinámica mediante
 * {@link MensajeInternacionalizacionHandler}.
 *
 * <p>Permite cambiar el idioma y el modo de almacenamiento desde el menú.</p>
 *
 * @author Bryan
 */
public class LoginView extends JFrame {

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

    private JMenuBar menuBar;
    private JMenu menuIdioma;
    private JMenu menuConfiguracion;
    private JMenuItem itemMemoria;
    private JMenuItem itemTexto;
    private JMenuItem itemBinario;

    private JMenuItem itemEspañol, itemIngles, itemFrances;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    /**
     * Constructor principal de la ventana de Login.
     * Inicializa los componentes, iconos y textos internacionales.
     *
     * @param mensajeI el handler de internacionalización para cargar los textos según el idioma actual.
     */
    public LoginView(MensajeInternacionalizacionHandler mensajeI) {
        this.mensajeInternacionalizacionHandler = mensajeI;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuIdioma = new JMenu();
        menuBar.add(menuIdioma);

        itemEspañol = new JMenuItem("Español");
        itemIngles = new JMenuItem("English");
        itemFrances = new JMenuItem("Français");

        menuIdioma.add(itemEspañol);
        menuIdioma.add(itemIngles);
        menuIdioma.add(itemFrances);

        menuConfiguracion = new JMenu("Configuración");
        menuBar.add(menuConfiguracion);

        itemMemoria = new JMenuItem("Memoria");
        itemTexto = new JMenuItem("Archivo de Texto");
        itemBinario = new JMenuItem("Archivo Binario");

        menuConfiguracion.add(itemMemoria);
        menuConfiguracion.add(itemTexto);
        menuConfiguracion.add(itemBinario);

        cargarIcono("imagenes/usuario.png", nombreLabel);
        cargarIcono("imagenes/contraseña.png", contraseñaLabel);
        cargarIcono("imagenes/acceso.png", btnIniciarSesion);
        cargarIcono("imagenes/login.png", btnRegistrarse);
        cargarIcono("imagenes/olvidosucontraseña.png", btnOlvidoContrasena);

        actualizarTextosLogin();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Carga un icono desde la ruta especificada y lo asigna al componente.
     *
     * @param ruta ruta del recurso de imagen.
     * @param componente JLabel o JButton que recibirá el icono.
     */
    private void cargarIcono(String ruta, AbstractButton componente) {
        URL url = LoginView.class.getClassLoader().getResource(ruta);
        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            Image imagen = icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            componente.setIcon(new ImageIcon(imagen));
            componente.setHorizontalTextPosition(SwingConstants.RIGHT);
            componente.setVerticalTextPosition(SwingConstants.CENTER);
        } else {
            System.err.println("No se encontró la imagen " + ruta);
        }
    }

    private void cargarIcono(String ruta, JLabel componente) {
        URL url = LoginView.class.getClassLoader().getResource(ruta);
        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            Image imagen = icono.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            componente.setIcon(new ImageIcon(imagen));
            componente.setHorizontalTextPosition(SwingConstants.RIGHT);
            componente.setVerticalTextPosition(SwingConstants.CENTER);
        } else {
            System.err.println("No se encontró la imagen " + ruta);
        }
    }

    /**
     * Actualiza los textos de todos los componentes visibles usando el handler de internacionalización.
     */
    public void actualizarTextosLogin() {
        menuIdioma.setText(mensajeInternacionalizacionHandler.get("menu.idiomas"));
        nombreLabel.setText(mensajeInternacionalizacionHandler.get("login.usuario"));
        contraseñaLabel.setText(mensajeInternacionalizacionHandler.get("login.contrasena"));
        btnIniciarSesion.setText(mensajeInternacionalizacionHandler.get("login.boton.iniciar"));
        btnRegistrarse.setText(mensajeInternacionalizacionHandler.get("login.boton.registrarse"));
        btnOlvidoContrasena.setText(mensajeInternacionalizacionHandler.get("login.boton.olvido"));
        setTitle(mensajeInternacionalizacionHandler.get("login.titulo"));
    }

    /**
     * Muestra un mensaje emergente.
     * @param mensaje El mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia los campos de texto del formulario.
     */
    public void limpiarCampos() {
        txtUsername.setText("");
        txtContrasenia.setText("");
    }


    /**
     * Devuelve el campo de texto para ingresar el nombre de usuario.
     *
     * @return JTextField del nombre de usuario.
     */
    public JTextField getTxtUsername() {
        return txtUsername;
    }

    /**
     * Devuelve el campo de contraseña.
     *
     * @return JPasswordField de la contraseña.
     */
    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    /**
     * Devuelve el botón para iniciar sesión.
     *
     * @return JButton de inicio de sesión.
     */
    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    /**
     * Devuelve el botón para registrarse.
     *
     * @return JButton para registrarse.
     */
    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    /**
     * Devuelve el botón para recuperar la contraseña.
     *
     * @return JButton para recuperar contraseña.
     */
    public JButton getBtnOlvidoContrasena() {
        return btnOlvidoContrasena;
    }

    /**
     * Devuelve el ítem de menú para cambiar el idioma a español.
     *
     * @return JMenuItem para idioma español.
     */
    public JMenuItem getItemIdiomaES() {
        return itemEspañol;
    }

    /**
     * Devuelve el ítem de menú para cambiar el idioma a inglés.
     *
     * @return JMenuItem para idioma inglés.
     */
    public JMenuItem getItemIdiomaEN() {
        return itemIngles;
    }

    /**
     * Devuelve el ítem de menú para cambiar el idioma a francés.
     *
     * @return JMenuItem para idioma francés.
     */
    public JMenuItem getItemIdiomaFR() {
        return itemFrances;
    }

    /**
     * Devuelve el ítem de menú para seleccionar almacenamiento en memoria.
     *
     * @return JMenuItem para almacenamiento en memoria.
     */
    public JMenuItem getItemMemoria() {
        return itemMemoria;
    }

    /**
     * Devuelve el ítem de menú para seleccionar almacenamiento en archivo de texto.
     *
     * @return JMenuItem para almacenamiento en archivo de texto.
     */
    public JMenuItem getItemTexto() {
        return itemTexto;
    }

    /**
     * Devuelve el ítem de menú para seleccionar almacenamiento en archivo binario.
     *
     * @return JMenuItem para almacenamiento en archivo binario.
     */
    public JMenuItem getItemBinario() {
        return itemBinario;
    }

}
