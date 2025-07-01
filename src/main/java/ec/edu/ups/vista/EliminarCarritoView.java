package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EliminarCarritoView extends  JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField textField1;
    private JButton buscarButton;
    private JTable tblProductos;
    private JButton eliminarButton;

    public EliminarCarritoView() {
        super("Eliminar Carrito", true, true, false, true);
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setVisible(false);
    }
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }
    public JTextField getTextField1() {
        return textField1;
    }
    public JButton getBuscarButton() {
        return buscarButton;
    }
    public JTable getTblProductos() {
        return tblProductos;
    }
    public JButton getEliminarButton() {
        return eliminarButton;
    }
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void limpiarCampos() {
        textField1.setText("");
    }
    public void mostrarProductos(String[] productos) {
        for (String producto : productos) {
            System.out.println(producto);
        }
    }

    public void cargarDatosTabla(List<Carrito> carritosEncontrados) {
        Object[] columnas = {"Código", "Fecha", "Items", "Subtotal", "IVA", "Total"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Carrito carrito : carritosEncontrados) {
            Object[] fila = {
                    carrito.getCodigo(),
                    String.format("%tF %tT", carrito.getFechaCreacion(), carrito.getFechaCreacion()),
                    carrito.obtenerItems().size(),
                    String.format("%.2f", carrito.calcularSubtotal()),
                    String.format("%.2f", carrito.calcularIVA()),
                    String.format("%.2f", carrito.calcularTotal())
            };
            modelo.addRow(fila);
        }

        tblProductos.setModel(modelo);

        if (modelo.getRowCount() > 0) {
            tblProductos.setRowSelectionInterval(0, 0);
        } else {
            mostrarMensaje("No hay carritos disponibles.");
        }
    }

}
