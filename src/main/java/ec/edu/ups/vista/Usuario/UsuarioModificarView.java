package ec.edu.ups.vista.Usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.net.URL;

/**
 * Vista interna para modificar la información de un usuario en el sistema.
 * Permite actualizar el nombre, contraseña y rol de un usuario.
 * Soporta internacionalización mediante MensajeInternacionalizacionHandler.
 */
public class UsuarioModificarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtNombre;
    private JPasswordField txtContrasenia;
    private JComboBox<String> cmbRol;
    private JButton btnBuscar;
    private JButton btnModificar;
    private JLabel lblNombre;
    private JLabel lblContrasena;
    private JLabel lblRol;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    /**
     * Constructor que inicializa los componentes gráficos y los íconos de la vista.
     * @param handler Manejador de mensajes internacionalizados.
     */
    public UsuarioModificarView(MensajeInternacionalizacionHandler handler) {
        this.mensajeInternacionalizacionHandler = handler;

        setContentPane(panelPrincipal);
        setTitle(handler.get("usuario.modificar.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setVisible(false);

        actualizarTextos();

        URL nombreURL = UsuarioModificarView.class.getClassLoader().getResource("imagenes/nombre.png");
        if (nombreURL != null) {
            lblNombre.setIcon(new ImageIcon(new ImageIcon(nombreURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró nombre.png");
        }

        URL contrasenaURL = UsuarioModificarView.class.getClassLoader().getResource("imagenes/contraseña.png");
        if (contrasenaURL != null) {
            lblContrasena.setIcon(new ImageIcon(new ImageIcon(contrasenaURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró contraseña.png");
        }

        URL rolURL = UsuarioModificarView.class.getClassLoader().getResource("imagenes/rol.png");
        if (rolURL != null) {
            lblRol.setIcon(new ImageIcon(new ImageIcon(rolURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró rol.png");
        }

        URL buscarURL = UsuarioModificarView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (buscarURL != null) {
            btnBuscar.setIcon(new ImageIcon(new ImageIcon(buscarURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
            btnBuscar.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró buscar.png");
        }

        URL modificarURL = UsuarioModificarView.class.getClassLoader().getResource("imagenes/modificar.png");
        if (modificarURL != null) {
            btnModificar.setIcon(new ImageIcon(new ImageIcon(modificarURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
            btnModificar.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró modificar.png");
        }
    }

    /** @return El panel principal de la vista. */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /** @return Campo de texto para ingresar el nombre del usuario. */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /** @return Campo de contraseña para ingresar o modificar la clave del usuario. */
    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    /** @return ComboBox para seleccionar el rol del usuario. */
    public JComboBox<String> getCmbRol() {
        return cmbRol;
    }

    /** @return Botón para buscar usuario por nombre. */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /** @return Botón para aplicar las modificaciones. */
    public JButton getBtnModificar() {
        return btnModificar;
    }

    /**
     * Muestra un mensaje emergente en la interfaz.
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia los campos del formulario.
     */
    public void limpiarCampos() {
        txtNombre.setText("");
        txtContrasenia.setText("");
        cmbRol.setSelectedIndex(0);
    }

    /**
     * Actualiza los textos e íconos de la vista con base en el idioma actual.
     */
    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("usuario.modificar.titulo"));
        lblNombre.setText(mensajeInternacionalizacionHandler.get("usuario.label.nombre"));
        lblContrasena.setText(mensajeInternacionalizacionHandler.get("usuario.label.contrasena"));
        lblRol.setText(mensajeInternacionalizacionHandler.get("usuario.label.rol"));
        btnBuscar.setText(mensajeInternacionalizacionHandler.get("boton.buscar"));
        btnModificar.setText(mensajeInternacionalizacionHandler.get("boton.modificar"));
    }
}
