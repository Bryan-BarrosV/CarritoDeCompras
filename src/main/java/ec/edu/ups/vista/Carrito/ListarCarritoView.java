package ec.edu.ups.vista.Carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListarCarritoView extends JInternalFrame {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblCarrito;
    private JPanel panelPrincipal;
    private JButton btnListar;
    private JLabel lblCodigo;
    private DefaultTableModel modelo;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public ListarCarritoView(MensajeInternacionalizacionHandler handler) {
        this.mensajeInternacionalizacionHandler = handler;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Código Carrito", "Fecha Creación", "Número de Items", "Subtotal", "IVA", "Total"};
        modelo.setColumnIdentifiers(columnas);

        tblCarrito.setModel(modelo);

        actualizarTextos();
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JTable getTblCarritos() {
        return tblCarrito;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void cargarDatos(List<Carrito> listaCarritos) {
        modelo.setRowCount(0);

        if (listaCarritos != null) {
            for (Carrito carrito : listaCarritos) {
                if (carrito != null) {
                    String fecha = "N/A";
                    if (carrito.getFechaCreacion() != null) {
                        fecha = String.format("%tF %tT", carrito.getFechaCreacion(), carrito.getFechaCreacion());
                    }

                    Object[] fila = {
                            carrito.getCodigo(),
                            fecha,
                            carrito.obtenerItems().size(),
                            String.format("%.2f", carrito.calcularSubtotal()),
                            String.format("%.2f", carrito.calcularIVA()),
                            String.format("%.2f", carrito.calcularTotal())
                    };
                    modelo.addRow(fila);
                }
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampoBusqueda() {
        if (txtBuscar != null) {
            txtBuscar.setText("");
        }
    }
    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("carrito.listar.titulo"));
        lblCodigo.setText(mensajeInternacionalizacionHandler.get("carrito.label.codigo"));
        btnBuscar.setText(mensajeInternacionalizacionHandler.get("boton.buscar"));
        btnListar.setText(mensajeInternacionalizacionHandler.get("boton.listar"));

        modelo.setColumnIdentifiers(new Object[]{
                mensajeInternacionalizacionHandler.get("carrito.label.codigo"),
                mensajeInternacionalizacionHandler.get("carrito.label.fecha"),
                mensajeInternacionalizacionHandler.get("carrito.label.items"),
                mensajeInternacionalizacionHandler.get("carrito.label.subtotal"),
                mensajeInternacionalizacionHandler.get("carrito.label.iva"),
                mensajeInternacionalizacionHandler.get("carrito.label.total")
        });
    }
}