package ec.edu.ups.vista;

import ec.edu.ups.modelo.Producto;

import javax.swing.*;

public class EliminarProductoView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField textCodigo;
    private JTextField textProducto;
    private JButton btnBuscar;
    private JButton btnEliminar;

    public EliminarProductoView() {
        setTitle("Eliminar Producto");
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
        return textCodigo;
    }

    public JTextField getTextProducto() {
        return textProducto;
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
        textCodigo.setText("");
        textProducto.setText("");
    }

    public void mostrarProducto(Producto producto) {
        if (producto != null) {
            textProducto.setText(producto.getNombre());
        } else {
            mostrarMensaje("Producto no encontrado.");
            limpiarCampos();
        }
    }
}
