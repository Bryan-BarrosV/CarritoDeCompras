package ec.edu.ups.vista.Carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

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
    private JLabel lblSubtotal;
    private JLabel lblIva;
    private JLabel lblTotal;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public CarritoModificarView(MensajeInternacionalizacionHandler handler) {
        super("Modificar Carrito", true, true, true, true);
        this.mensajeInternacionalizacionHandler = handler;

        panelPrincipal = new JPanel(new BorderLayout());
        setContentPane(panelPrincipal);
        setSize(600, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lblCodigo = new JLabel();
        lblFecha = new JLabel();
        lblSubtotal = new JLabel();
        lblIva = new JLabel();
        lblTotal = new JLabel();

        JPanel panelDatos = new JPanel(new GridLayout(2, 3));
        txtCodigo = new JTextField();
        btnBuscar = new JButton();
        txtFecha = new JTextField();
        txtFecha.setEditable(false);

        panelDatos.add(lblCodigo);
        panelDatos.add(txtCodigo);
        panelDatos.add(btnBuscar);
        panelDatos.add(lblFecha);
        panelDatos.add(txtFecha);
        panelDatos.add(new JLabel(""));

        panelPrincipal.add(panelDatos, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new Object[]{"Código", "Nombre", "Precio", "Cantidad"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
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
        btnModificar = new JButton();

        panelInferior.add(lblSubtotal);
        panelInferior.add(txtSubtotal);
        panelInferior.add(lblIva);
        panelInferior.add(txtIVA);
        panelInferior.add(lblTotal);
        panelInferior.add(txtTotal);
        panelInferior.add(new JLabel(""));
        panelInferior.add(btnModificar);

        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        actualizarTextos();
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

    public JTextField getTxtCodigo() { return txtCodigo; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JTextField getTxtFecha() { return txtFecha; }
    public JTable getTblProductos() { return tblProductos; }
    public JTextField getTxtSubtotal() { return txtSubtotal; }
    public JTextField getTxtIVA() { return txtIVA; }
    public JTextField getTxtTotal() { return txtTotal; }
    public JButton getBtnModificar() { return btnModificar; }
    public DefaultTableModel getModelo() { return modelo; }

    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("carrito.modificar.titulo"));
        lblCodigo.setText(mensajeInternacionalizacionHandler.get("producto.label.codigo"));
        lblFecha.setText(mensajeInternacionalizacionHandler.get("carrito.label.fecha"));
        lblSubtotal.setText(mensajeInternacionalizacionHandler.get("carrito.label.subtotal"));
        lblIva.setText(mensajeInternacionalizacionHandler.get("carrito.label.iva"));
        lblTotal.setText(mensajeInternacionalizacionHandler.get("carrito.label.total"));
        btnBuscar.setText(mensajeInternacionalizacionHandler.get("boton.buscar"));
        btnModificar.setText(mensajeInternacionalizacionHandler.get("boton.modificar"));

        modelo.setColumnIdentifiers(new Object[]{
                mensajeInternacionalizacionHandler.get("producto.label.codigo"),
                mensajeInternacionalizacionHandler.get("producto.label.nombre"),
                mensajeInternacionalizacionHandler.get("producto.label.precio"),
                mensajeInternacionalizacionHandler.get("producto.label.cantidad")
        });
    }
}
