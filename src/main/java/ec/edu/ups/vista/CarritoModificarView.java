package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CarritoModificarView extends JInternalFrame {

    private JTextField txtCodigo;
    private JButton btnBuscar;
    private JTextField txtFecha;
    private JTable tblProductos;
    private JTextField txtSubtotal;
    private JTextField txtIVA;
    private JTextField txtTotal;
    private JButton btnModificar;
    private JPanel panelPrincipal;
    private DefaultTableModel modelo;
    private JLabel lblCodigo;
    private JLabel lblFecha;

    public CarritoModificarView() {
        super("Modificar Carrito", true, true, true, true);
        panelPrincipal = new JPanel(new BorderLayout());
        setContentPane(panelPrincipal);
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelDatos = new JPanel(new GridLayout(2, 3));
        txtCodigo = new JTextField();
        btnBuscar = new JButton("Buscar");
        txtFecha = new JTextField();
        txtFecha.setEditable(false);

        panelDatos.add(new JLabel("Código:"));
        panelDatos.add(txtCodigo);
        panelDatos.add(btnBuscar);
        panelDatos.add(new JLabel("Fecha:"));
        panelDatos.add(txtFecha);
        panelDatos.add(new JLabel(""));

        panelPrincipal.add(panelDatos, BorderLayout.NORTH);

        String[] columnas = {"Código", "Nombre", "Precio", "Cantidad"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Solo la cantidad se puede editar
            }
        };
        tblProductos = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tblProductos);
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new GridLayout(2, 4));
        txtSubtotal = new JTextField();
        txtIVA = new JTextField();
        txtTotal = new JTextField();
        txtSubtotal.setEditable(false);
        txtIVA.setEditable(false);
        txtTotal.setEditable(false);
        btnModificar = new JButton("Modificar");

        panelInferior.add(new JLabel("Subtotal:"));
        panelInferior.add(txtSubtotal);
        panelInferior.add(new JLabel("IVA:"));
        panelInferior.add(txtIVA);
        panelInferior.add(new JLabel("Total:"));
        panelInferior.add(txtTotal);
        panelInferior.add(new JLabel(""));
        panelInferior.add(btnModificar);

        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cargarDatos(Carrito carrito) {
        modelo.setRowCount(0);
        for (ItemCarrito item : carrito.obtenerItems()) {
            Producto p = item.getProducto();
            modelo.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNombre(),
                    p.getPrecio(),
                    item.getCantidad()
            });
        }
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    public JTextField getTxtIVA() {
        return txtIVA;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }
}
