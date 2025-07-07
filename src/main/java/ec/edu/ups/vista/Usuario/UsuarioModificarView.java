package ec.edu.ups.vista.Usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

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
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public JComboBox<String> getCmbRol() {
        return cmbRol;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {

        txtNombre.setText("");
        txtContrasenia.setText("");
        cmbRol.setSelectedIndex(0);
    }

    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("usuario.modificar.titulo"));
        lblNombre.setText(mensajeInternacionalizacionHandler.get("usuario.label.nombre"));
        lblContrasena.setText(mensajeInternacionalizacionHandler.get("usuario.label.contrasena"));
        lblRol.setText(mensajeInternacionalizacionHandler.get("usuario.label.rol"));
        btnBuscar.setText(mensajeInternacionalizacionHandler.get("boton.buscar"));
        btnModificar.setText(mensajeInternacionalizacionHandler.get("boton.modificar"));
    }
}
