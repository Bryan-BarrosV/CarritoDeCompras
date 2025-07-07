package ec.edu.ups.vista.Usuario;

import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.net.URL;

public class UsuarioEliminarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField textUsername;
    private JPasswordField textContrasena;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel lblUsuario;
    private JLabel lblContrasena;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public UsuarioEliminarView(MensajeInternacionalizacionHandler handler) {
        this.mensajeInternacionalizacionHandler = handler;

        setTitle(handler.get("usuario.eliminar.titulo"));

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(false);

        URL usuarioURL = UsuarioEliminarView.class.getClassLoader().getResource("imagenes/usuario.png");
        if (usuarioURL != null) {
            lblUsuario.setIcon(new ImageIcon(new ImageIcon(usuarioURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró usuario.png");
        }

        URL contrasenaURL = UsuarioEliminarView.class.getClassLoader().getResource("imagenes/contraseña.png");
        if (contrasenaURL != null) {
            lblContrasena.setIcon(new ImageIcon(new ImageIcon(contrasenaURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró contraseña.png");
        }

        URL buscarURL = UsuarioEliminarView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (buscarURL != null) {
            btnBuscar.setIcon(new ImageIcon(new ImageIcon(buscarURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
            btnBuscar.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró buscar.png");
        }

        URL eliminarURL = UsuarioEliminarView.class.getClassLoader().getResource("imagenes/basura.png");
        if (eliminarURL != null) {
            btnEliminar.setIcon(new ImageIcon(new ImageIcon(eliminarURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
            btnEliminar.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró basura.png");
        }

    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getTextCodigo() {
        return textUsername;
    }

    public JPasswordField getTextContrasenia() {
        return textContrasena;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        textUsername.setText("");
        textContrasena.setText("");
    }

    public void mostrarUsuario(Usuario usuario) {
        if (usuario != null) {
            textContrasena.setText(usuario.getContrasenia());
        } else {
            mostrarMensaje("usuario.no.encontrado");
            limpiarCampos();
        }
    }

    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("usuario.eliminar.titulo"));
        lblUsuario.setText(mensajeInternacionalizacionHandler.get("usuario.label.username"));
        lblContrasena.setText(mensajeInternacionalizacionHandler.get("usuario.label.contrasena"));
        btnBuscar.setText(mensajeInternacionalizacionHandler.get("boton.buscar"));
        btnEliminar.setText(mensajeInternacionalizacionHandler.get("boton.eliminar"));
    }
}

