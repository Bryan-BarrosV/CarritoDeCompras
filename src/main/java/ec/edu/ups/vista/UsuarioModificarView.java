package ec.edu.ups.vista;

import javax.swing.*;

public class UsuarioModificarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtNombre;
    private JPasswordField txtContrasenia;
    private JComboBox<String> cmbRol;
    private JButton btnBuscar;
    private JButton btnModificar;

    public UsuarioModificarView() {
        setContentPane(panelPrincipal);
        setTitle("Modificar Usuario");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setVisible(false);
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
}
