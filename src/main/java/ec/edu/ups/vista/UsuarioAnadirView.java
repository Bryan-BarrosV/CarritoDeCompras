package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioAnadirView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtNombre;
    private JTextField txtID;
    private JPasswordField txtContrasenia;
    private JComboBox<String> cbxRol;
    private JButton btnRegistrar;
    private JButton btnLimpiar;

    public UsuarioAnadirView() {
        super("Registrar Usuario", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setVisible(false);

        cbxRol.removeAllItems();
        cbxRol.addItem("ADMIN");
        cbxRol.addItem("CLIENTE");
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtID() {
        return txtID;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public JComboBox<String> getCbxRol() {
        return cbxRol;
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

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtID.setText("");
        txtContrasenia.setText("");
        cbxRol.setSelectedIndex(0);
    }
}
