package ec.edu.ups.vista.Usuario;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

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
