package ec.edu.ups.vista.Usuario;

import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

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

