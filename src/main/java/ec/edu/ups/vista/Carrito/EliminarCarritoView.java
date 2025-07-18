package ec.edu.ups.vista.Carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;
import java.util.List;

/**
 * Vista interna que permite eliminar carritos de compras.
 * <p>
 * Esta clase proporciona una interfaz para buscar carritos existentes por su código y eliminarlos.
 * Muestra los resultados en una tabla y ofrece opciones para limpiar campos y actualizar textos
 * mediante internacionalización.
 */
public class EliminarCarritoView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField textField1;
    private JButton buscarButton;
    private JTable tblProductos;
    private JButton eliminarButton;
    private JLabel lblCodigo;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    /**
     * Constructor que inicializa la vista para eliminar carritos.
     *
     * @param handler Manejador de internacionalización para cargar los textos en el idioma seleccionado.
     */
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
        configurarIconos();
    }

    /**
     * Carga íconos personalizados en botones y etiquetas.
     */
    private void configurarIconos() {
        asignarIcono("imagenes/codigo.png", lblCodigo);
        asignarIcono("imagenes/buscar.png", buscarButton);
        asignarIcono("imagenes/basura.png", eliminarButton);
    }

    /**
     * Asocia un ícono a un componente de Swing.
     *
     * @param ruta     Ruta del recurso de imagen.
     * @param destino  Componente al cual se le asignará el ícono.
     */
    private void asignarIcono(String ruta, JComponent destino) {
        URL url = EliminarCarritoView.class.getClassLoader().getResource(ruta);
        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            ImageIcon iconoEscalado = new ImageIcon(icono.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
            if (destino instanceof JLabel) {
                ((JLabel) destino).setIcon(iconoEscalado);
            } else if (destino instanceof AbstractButton) {
                ((AbstractButton) destino).setIcon(iconoEscalado);
                ((AbstractButton) destino).setHorizontalTextPosition(SwingConstants.RIGHT);
            }
        }
    }

    // ------------------------ Getters ------------------------

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

    /**
     * Muestra un mensaje emergente al usuario.
     *
     * @param mensaje Texto a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia el campo de texto utilizado para la búsqueda por código.
     */
    public void limpiarCampos() {
        textField1.setText("");
    }

    /**
     * Muestra una lista de productos en consola (no en la interfaz gráfica).
     *
     * @param productos Arreglo de nombres de productos.
     */
    public void mostrarProductos(String[] productos) {
        for (String producto : productos) {
            System.out.println(producto);
        }
    }

    /**
     * Carga una lista de carritos encontrados en la tabla.
     *
     * @param carritosEncontrados Lista de carritos a mostrar.
     */
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

    /**
     * Actualiza todos los textos de la interfaz con base en el idioma actual.
     */
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
