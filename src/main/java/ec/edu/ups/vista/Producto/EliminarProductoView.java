package ec.edu.ups.vista.Producto;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

public class EliminarProductoView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField textCodigo;
    private JTextField textProducto;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel lblCodigo;
    private JLabel lblProducto;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public EliminarProductoView(MensajeInternacionalizacionHandler handler) {
        this.mensajeInternacionalizacionHandler=handler;
        setTitle(mensajeInternacionalizacionHandler.get("producto.eliminar.titulo"));
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(false);
        actualizarTextos();
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
    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("producto.eliminar.titulo"));
        lblCodigo.setText(mensajeInternacionalizacionHandler.get("producto.label.codigo"));
        lblProducto.setText(mensajeInternacionalizacionHandler.get("producto.label.nombre"));
        btnBuscar.setText(mensajeInternacionalizacionHandler.get("boton.buscar"));
        btnEliminar.setText(mensajeInternacionalizacionHandler.get("boton.eliminar"));
    }

}
