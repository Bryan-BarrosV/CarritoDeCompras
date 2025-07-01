package ec.edu.ups.vista;

import javax.swing.*;

public class ModificarProductoView extends JInternalFrame {

    private JPanel panelPrincipal;

    private JTextField textCodigo;
    private JTextField textProducto;
    private JTextField textPrecio;
    private JButton btnBuscar;
    private JButton btnGuardar;

    public ModificarProductoView() {
        setTitle("Modificar Producto");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
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
        return textCodigo;
    }

    public JTextField getTextProducto() {
        return textProducto;
    }

    public JTextField getTextPrecio() {
        return textPrecio;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        textCodigo.setText("");
        textProducto.setText("");
        textPrecio.setText("");
    }
}
