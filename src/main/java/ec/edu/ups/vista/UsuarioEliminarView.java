package ec.edu.ups.vista;

import ec.edu.ups.modelo.Usuario;

import javax.swing.*;

public class UsuarioEliminarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField textUsername;
    private JPasswordField textContrasena;
    private JButton btnBuscar;
    private JButton btnEliminar;

    public UsuarioEliminarView() {
        setTitle("Eliminar Usuario");
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
        textContrasena.setText("");  // Esto es válido para JPasswordField
    }

    public void mostrarUsuario(Usuario usuario) {
        if (usuario != null) {
            textContrasena.setText(usuario.getContrasenia());
        } else {
            mostrarMensaje("Usuario no encontrado.");
            limpiarCampos();
        }
    }
}

