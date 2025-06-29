package ec.edu.ups.vista;

import ec.edu.ups.modelo.Producto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ModificarProductoView extends JInternalFrame {

    private JPanel panelPrincipal;

    private JTextField textProducto;
    private JButton buscarButton;
    private JTable tblProductos;
    private JTextField textNombre;
    private JTextField textPrecio;
    private JButton modificarButton;
    private DefaultTableModel modeloTabla;

    public ModificarProductoView() {
        setTitle("Modificar Producto");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(false);

        modeloTabla = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio"};
        modeloTabla.setColumnIdentifiers(columnas);
        tblProductos.setModel(modeloTabla);
    }

    public void cargarDatosTabla(List<Producto> listaProductos) {
        modeloTabla.setNumRows(0);

        for (Producto producto : listaProductos) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            };
            modeloTabla.addRow(fila);
        }
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getTextProducto() {
        return textProducto;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public JTextField getTextNombre() {
        return textNombre;
    }

    public JTextField getTextPrecio() {
        return textPrecio;
    }

    public JButton getModificarButton() {
        return modificarButton;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }
}