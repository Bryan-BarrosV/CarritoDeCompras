package ec.edu.ups.vista;

import ec.edu.ups.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class EliminarProductoView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField textField1;
    private JButton buscarButton;
    private JTable tblProductos;
    private JButton eliminarButton;
    private DefaultTableModel modeloTabla;

    public EliminarProductoView() {
        setTitle("Eliminar Producto");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(false);

        modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        Object[] columnas = {"Codigo", "Nombre", "Precio"};
        modeloTabla.setColumnIdentifiers(columnas);
        tblProductos.setModel(modeloTabla);
        tblProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void cargarDatosTabla(List<Producto> listaProductos) {
        modeloTabla.setRowCount(0);
        
        if (listaProductos != null) {
            for (Producto producto : listaProductos) {
                Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
                };
                modeloTabla.addRow(fila);
            }
        }
        
        if (modeloTabla.getRowCount() > 0) {
            tblProductos.setRowSelectionInterval(0, 0);
        }
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
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

}