package ec.edu.ups.vista.Carrito;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;

/**
 * Vista interna para la modificación de un carrito de compras.
 * Permite buscar un carrito por código, visualizar sus ítems,
 * modificar las cantidades y actualizar los totales.
 * <p>
 * Soporta internacionalización de etiquetas y botones.
 * </p>
 */
public class CarritoModificarView extends JInternalFrame {

    /** Campo de texto para ingresar el código de carrito. */
    private JTextField txtCodigo;
    /** Botón para iniciar la búsqueda del carrito. */
    private JButton btnBuscar;
    /** Campo de texto que muestra la fecha de creación del carrito. */
    private JTextField txtFecha;
    /** Tabla que lista los productos e ítems del carrito. */
    private JTable tblProductos;
    /** Campo de texto que muestra el subtotal del carrito. */
    private JTextField txtSubtotal;
    /** Campo de texto que muestra el valor del IVA del carrito. */
    private JTextField txtIVA;
    /** Campo de texto que muestra el total final del carrito. */
    private JTextField txtTotal;
    /** Botón para aplicar las modificaciones al carrito. */
    private JButton btnModificar;
    /** Panel principal que contiene todos los componentes. */
    private JPanel panelPrincipal;
    /** Modelo de tabla para gestionar las filas de productos. */
    private DefaultTableModel modelo;
    private JLabel lblCodigo;
    private JLabel lblFecha;
    private JLabel lblSubtotal;
    private JLabel lblIva;
    private JLabel lblTotal;

    /**
     * Manejador de internacionalización para obtener textos traducidos.
     */
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    /**
     * Construye la vista de modificación de carrito y configura todos
     * los componentes gráficos e íconos.
     *
     * @param handler manejador de mensajes para internacionalización
     */
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

        // Panel de datos: código, búsqueda y fecha
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

        // Configuración de la tabla de productos e ítems
        modelo = new DefaultTableModel(new Object[]{"Código", "Nombre", "Precio", "Cantidad"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };
        tblProductos = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tblProductos);
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        // Panel inferior: totales y botón de modificar
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

        // Inicializar textos traducidos
        actualizarTextos();

        // Carga de íconos
        cargarIcono(lblCodigo, "imagenes/codigo.png", 20);
        cargarIcono(lblFecha, "imagenes/fecha.png", 20);
        cargarIcono(btnBuscar, "imagenes/buscar.png", 20);
        cargarIcono(btnModificar, "imagenes/modificar.png", 20);
    }

    /**
     * Muestra un mensaje emergente con el texto proporcionado.
     *
     * @param mensaje texto a mostrar en el diálogo
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Carga en la vista los datos del carrito recibido,
     * incluyendo ítems, fecha, subtotal, IVA y total.
     *
     * @param carrito objeto Carrito con los datos a visualizar
     */
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

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = formato.format(carrito.getFechaCreacion().getTime());
        txtFecha.setText(fechaFormateada);

        txtSubtotal.setText(String.format("%.2f", carrito.calcularSubtotal()));
        txtIVA.setText(String.format("%.2f", carrito.calcularIVA()));
        txtTotal.setText(String.format("%.2f", carrito.calcularTotal()));
    }

    /**
     * @return campo de texto para el código del carrito
     */
    public JTextField getTxtCodigo() { return txtCodigo; }

    /**
     * @return botón para buscar el carrito
     */
    public JButton getBtnBuscar() { return btnBuscar; }

    /**
     * @return campo de texto que muestra la fecha
     */
    public JTextField getTxtFecha() { return txtFecha; }

    /**
     * @return tabla de productos e ítems
     */
    public JTable getTblProductos() { return tblProductos; }

    /**
     * @return campo que muestra el subtotal
     */
    public JTextField getTxtSubtotal() { return txtSubtotal; }

    /**
     * @return campo que muestra el IVA
     */
    public JTextField getTxtIVA() { return txtIVA; }

    /**
     * @return campo que muestra el total
     */
    public JTextField getTxtTotal() { return txtTotal; }

    /**
     * @return botón para aplicar las modificaciones
     */
    public JButton getBtnModificar() { return btnModificar; }

    /**
     * @return modelo de la tabla de ítems
     */
    public DefaultTableModel getModelo() { return modelo; }

    /**
     * Actualiza todos los textos de etiquetas, columnas y botones
     * según el idioma configurado en el manejador de internacionalización.
     */
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

    /**
     * Carga un icono en un componente JLabel o JButton.
     *
     * @param component componente donde asignar el icono
     * @param path        ruta del recurso de imagen
     * @param size        tamaño de anchura y altura del icono en píxeles
     */
    private void cargarIcono(JComponent component, String path, int size) {
        URL url = CarritoModificarView.class.getClassLoader().getResource(path);
        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            Image img = icono.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            if (component instanceof JLabel) {
                ((JLabel) component).setIcon(new ImageIcon(img));
            } else if (component instanceof JButton) {
                ((JButton) component).setIcon(new ImageIcon(img));
                ((JButton) component).setHorizontalTextPosition(SwingConstants.RIGHT);
            }
        }
    }
}
