package ec.edu.ups.vista.Carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.net.URL;
import java.util.List;

/**
 * Vista interna para listar y buscar carritos de compras.
 * <p>
 * Esta clase permite al usuario buscar carritos por código, visualizar todos los carritos registrados
 * y ver detalles como fecha de creación, número de ítems, subtotal, IVA y total.
 * Se apoya en el manejador de internacionalización para adaptar los textos según el idioma seleccionado.
 */
public class ListarCarritoView extends JInternalFrame {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblCarrito;
    private JPanel panelPrincipal;
    private JButton btnListar;
    private JLabel lblCodigo;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    /**
     * Constructor que inicializa la vista con sus componentes y aplica los textos traducidos.
     *
     * @param handler Manejador de internacionalización que permite cambiar los textos del GUI dinámicamente.
     */
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
        configurarIconos();
    }

    /**
     * Asigna íconos personalizados a los botones y etiquetas si los recursos existen.
     */
    private void configurarIconos() {
        asignarIcono("imagenes/codigo.png", lblCodigo);
        asignarIcono("imagenes/buscar.png", btnBuscar);
        asignarIcono("imagenes/listar.png", btnListar);
    }

    /**
     * Asocia un ícono a un componente Swing si se encuentra la ruta del recurso.
     *
     * @param ruta     Ruta del archivo de imagen.
     * @param destino  Componente al que se le aplicará el ícono.
     */
    private void asignarIcono(String ruta, JComponent destino) {
        URL url = ListarCarritoView.class.getClassLoader().getResource(ruta);
        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            ImageIcon iconoEscalado = new ImageIcon(icono.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
            if (destino instanceof JLabel) {
                ((JLabel) destino).setIcon(iconoEscalado);
            } else if (destino instanceof AbstractButton) {
                ((AbstractButton) destino).setIcon(iconoEscalado);
                ((AbstractButton) destino).setHorizontalTextPosition(SwingConstants.RIGHT);
            }
        } else {
            System.err.println("No se encontró el ícono: " + ruta);
        }
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

    /**
     * Carga una lista de carritos en la tabla principal.
     *
     * @param listaCarritos Lista de objetos Carrito a mostrar.
     */
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

    /**
     * Muestra un mensaje emergente en pantalla.
     *
     * @param mensaje Texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia el campo de texto utilizado para la búsqueda de carritos.
     */
    public void limpiarCampoBusqueda() {
        if (txtBuscar != null) {
            txtBuscar.setText("");
        }
    }

    /**
     * Actualiza los textos visibles en la interfaz utilizando el idioma actual.
     */
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
