package ec.edu.ups.vista;

import ec.edu.ups.modelo.Carrito;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ListarCarritoView extends JInternalFrame {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblCarrito;
    private JPanel panelPrincipal;
    private JButton btnListar;
    private DefaultTableModel modelo;

    public ListarCarritoView() {
        setContentPane(panelPrincipal);
        setTitle("Listado de Carritos");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        modelo = new DefaultTableModel();
        Object[] columnas = {"Código Carrito", "Fecha Creación", "Número de Items", "Subtotal", "IVA", "Total"};
        modelo.setColumnIdentifiers(columnas);

        tblCarrito.setModel(modelo);
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
}