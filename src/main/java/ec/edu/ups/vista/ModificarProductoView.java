package ec.edu.ups.vista;

import ec.edu.ups.modelo.Producto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ModificarProductoView extends JFrame {

    private JPanel panelPrincipal;

    private JTextField textField1;
    private JButton buscarButton;
    private JTable tblProductos;
    private JButton eliminarButton;
    private JButton modificarButton;
    private JTextField textNombre;
    private JTextField textPrecio;
    private DefaultTableModel modeloTabla;

    public ModificarProductoView() {

        setTitle("Modificar Producto");
        setContentPane(panelPrincipal);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
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

    public JButton getModificarButton() {
        return modificarButton;
    }

    public JTextField getTextNombre() {
        return textNombre;
    }

    public JTextField getTextPrecio() {
        return textPrecio;
    }
}