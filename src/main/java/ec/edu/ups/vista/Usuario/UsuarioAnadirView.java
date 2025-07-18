package ec.edu.ups.vista.Usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.net.URL;

public class UsuarioAnadirView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtUsername;
    private JPasswordField txtContrasenia;
    private JButton btnRegistrar;
    private JButton btnLimpiar;
    private JTextField txtNombreCompleto;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JLabel lblNombreCompleto;
    private JLabel lblNombre;
    private JLabel lblCorreo;
    private JLabel lblTelefono;
    private JLabel lblContrasena;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public UsuarioAnadirView(MensajeInternacionalizacionHandler handler) {
        super("Registrar Usuario", true, true, false, true);
        this.mensajeInternacionalizacionHandler = handler;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setVisible(false);
        actualizarTextos();

        URL nombreURL = UsuarioAnadirView.class.getClassLoader().getResource("imagenes/nombre.png");
        if (nombreURL != null) {
            lblNombre.setIcon(new ImageIcon(new ImageIcon(nombreURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró el ícono nombre.png");
        }

        URL contrasenaURL = UsuarioAnadirView.class.getClassLoader().getResource("imagenes/contraseña.png");
        if (contrasenaURL != null) {
            lblContrasena.setIcon(new ImageIcon(new ImageIcon(contrasenaURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró el ícono contraseña.png");
        }

        URL nombreCompletoURL = UsuarioAnadirView.class.getClassLoader().getResource("imagenes/nombrecompleto.png");
        if (nombreCompletoURL != null) {
            lblNombreCompleto.setIcon(new ImageIcon(new ImageIcon(nombreCompletoURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró el ícono nombrecompleto.png");
        }

        URL correoURL = UsuarioAnadirView.class.getClassLoader().getResource("imagenes/correo.png");
        if (correoURL != null) {
            lblCorreo.setIcon(new ImageIcon(new ImageIcon(correoURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró el ícono correo.png");
        }

        URL telefonoURL = UsuarioAnadirView.class.getClassLoader().getResource("imagenes/telefono.png");
        if (telefonoURL != null) {
            lblTelefono.setIcon(new ImageIcon(new ImageIcon(telefonoURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró el ícono telefono.png");
        }

        URL registrarURL = UsuarioAnadirView.class.getClassLoader().getResource("imagenes/registrar.png");
        if (registrarURL != null) {
            btnRegistrar.setIcon(new ImageIcon(new ImageIcon(registrarURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
            btnRegistrar.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró el ícono registrar.png");
        }

        URL limpiarURL = UsuarioAnadirView.class.getClassLoader().getResource("imagenes/limpiar.png");
        if (limpiarURL != null) {
            btnLimpiar.setIcon(new ImageIcon(new ImageIcon(limpiarURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
            btnLimpiar.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró el ícono limpiar.png");
        }

    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getTxtNombreCompleto() {return txtNombreCompleto;}

    public JTextField getTxtCorreo() {return txtCorreo;}

    public JTextField getTxtTelefono() {return txtTelefono;}

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtUsername.setText("");
        txtContrasenia.setText("");
        txtNombreCompleto.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
    }

    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("usuario.anadir.titulo"));
        lblNombre.setText(mensajeInternacionalizacionHandler.get("usuario.label.username"));
        lblContrasena.setText(mensajeInternacionalizacionHandler.get("usuario.label.contrasena"));
        lblNombreCompleto.setText(mensajeInternacionalizacionHandler.get("usuario.label.nombreCompleto"));
        lblCorreo.setText(mensajeInternacionalizacionHandler.get("usuario.label.correo"));
        lblTelefono.setText(mensajeInternacionalizacionHandler.get("usuario.label.telefono"));
        btnRegistrar.setText(mensajeInternacionalizacionHandler.get("boton.registrar"));
        btnLimpiar.setText(mensajeInternacionalizacionHandler.get("boton.limpiar"));
    }

}
