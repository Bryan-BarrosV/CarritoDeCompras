package ec.edu.ups.vista.Carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;
import java.util.List;

public class EliminarCarritoView extends  JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField textField1;
    private JButton buscarButton;
    private JTable tblProductos;
    private JButton eliminarButton;
    private JLabel lblCodigo;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public EliminarCarritoView(MensajeInternacionalizacionHandler handler) {
        super("Eliminar Carrito", true, true, false, true);
        this.mensajeInternacionalizacionHandler = handler;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setVisible(false);

        actualizarTextos();

        URL codigoURL = EliminarCarritoView.class.getClassLoader().getResource("imagenes/codigo.png");
        if (codigoURL != null) {
            ImageIcon iconoCodigo = new ImageIcon(codigoURL);
            lblCodigo.setIcon(new ImageIcon(iconoCodigo.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        }

        URL buscarURL = EliminarCarritoView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (buscarURL != null) {
            ImageIcon iconoBuscar = new ImageIcon(buscarURL);
            buscarButton.setIcon(new ImageIcon(iconoBuscar.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
            buscarButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

        URL eliminarURL = EliminarCarritoView.class.getClassLoader().getResource("imagenes/basura.png");
        if (eliminarURL != null) {
            ImageIcon iconoEliminar = new ImageIcon(eliminarURL);
            eliminarButton.setIcon(new ImageIcon(iconoEliminar.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
            eliminarButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

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
        Object[] columnas = {
                mensajeInternacionalizacionHandler.get("producto.label.codigo"),
                mensajeInternacionalizacionHandler.get("carrito.label.fecha"),
                mensajeInternacionalizacionHandler.get("carrito.label.items"),
                mensajeInternacionalizacionHandler.get("carrito.label.subtotal"),
                mensajeInternacionalizacionHandler.get("carrito.label.iva"),
                mensajeInternacionalizacionHandler.get("carrito.label.total")
        };

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
            mostrarMensaje(mensajeInternacionalizacionHandler.get("carrito.vacio"));
        }
    }
    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("carrito.eliminar.titulo"));
        lblCodigo.setText(mensajeInternacionalizacionHandler.get("carrito.label.codigo"));
        buscarButton.setText(mensajeInternacionalizacionHandler.get("boton.buscar"));
        eliminarButton.setText(mensajeInternacionalizacionHandler.get("boton.eliminar"));


        if (tblProductos.getModel() instanceof DefaultTableModel) {
            ((DefaultTableModel) tblProductos.getModel()).setColumnIdentifiers(new Object[]{
                    mensajeInternacionalizacionHandler.get("producto.label.codigo"),
                    mensajeInternacionalizacionHandler.get("carrito.label.fecha"),
                    mensajeInternacionalizacionHandler.get("carrito.label.items"),
                    mensajeInternacionalizacionHandler.get("carrito.label.subtotal"),
                    mensajeInternacionalizacionHandler.get("carrito.label.iva"),
                    mensajeInternacionalizacionHandler.get("carrito.label.total")
            });
        }
    }

}
