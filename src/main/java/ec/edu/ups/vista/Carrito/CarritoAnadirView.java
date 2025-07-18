package ec.edu.ups.vista.Carrito;

import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.util.Locale;

/**
 * Vista interna que permite al usuario añadir productos a un carrito de compras.
 * <p>
 * Esta clase proporciona una interfaz gráfica construida con Swing para gestionar el ingreso
 * de productos al carrito, calcular subtotales, IVA y total, y persistir la información.
 * Además, soporta la internacionalización de los textos mediante {@link MensajeInternacionalizacionHandler}.
 */
public class CarritoAnadirView extends JInternalFrame {

    private JButton btnBuscar;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnAnadir;
    private JTable tblProductos;
    private JTextField txtSubtotal;
    private JTextField txtIva;
    private JTextField txtTotal;
    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JComboBox cbxCantidad;
    private JPanel panelPrincipal;
    private JTextField textUsername;
    private JButton btnEliminar;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private JLabel lblCantidad;
    private JLabel lblUsuario;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;
    private Locale locale;

    /**
     * Constructor que inicializa y configura los componentes de la vista.
     *
     * @param handler manejador de internacionalización que proporciona los textos traducidos.
     */
    public CarritoAnadirView(MensajeInternacionalizacionHandler handler) {
        super("Carrito de Compras", true, true, false, true);
        this.mensajeInternacionalizacionHandler = handler;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        textUsername.setEditable(false);
        cargarDatos();
        configurarTabla();
        configurarIconos();
        actualizarTextos();
        setVisible(false);
    }

    /**
     * Carga la lista de cantidades disponibles en el combo.
     */
    private void cargarDatos() {
        cbxCantidad.removeAllItems();
        for (int i = 1; i <= 20; i++) {
            cbxCantidad.addItem(String.valueOf(i));
        }
    }

    /**
     * Configura las columnas de la tabla de productos.
     */
    private void configurarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        Object[] columnas = {
                mensajeInternacionalizacionHandler.get("producto.label.codigo"),
                mensajeInternacionalizacionHandler.get("producto.label.nombre"),
                mensajeInternacionalizacionHandler.get("producto.label.precio"),
                mensajeInternacionalizacionHandler.get("producto.label.cantidad"),
                mensajeInternacionalizacionHandler.get("carrito.label.subtotal")
        };
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);
    }

    /**
     * Asocia íconos personalizados a los botones y etiquetas principales de la vista.
     */
    private void configurarIconos() {
        asociarIcono("imagenes/limpiar.png", btnLimpiar);
        asociarIcono("imagenes/añadircarr.png", btnAnadir);
        asociarIcono("imagenes/usuariocarr.png", lblUsuario);
        asociarIcono("imagenes/codigo.png", lblCodigo);
        asociarIcono("imagenes/productonombre.png", lblNombre);
        asociarIcono("imagenes/precio.png", lblPrecio);
        asociarIcono("imagenes/buscar.png", btnBuscar);
        asociarIcono("imagenes/basura.png", btnEliminar);
        asociarIcono("imagenes/guardar.png", btnGuardar);
    }

    /**
     * Asocia un icono a un componente Swing.
     *
     * @param ruta     la ruta del recurso dentro del proyecto.
     * @param destino  el componente que recibirá el icono.
     */
    private void asociarIcono(String ruta, JComponent destino) {
        URL url = CarritoAnadirView.class.getClassLoader().getResource(ruta);
        if (url != null) {
            ImageIcon icono = new ImageIcon(url);
            Image imagenEscalada = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            if (destino instanceof AbstractButton) {
                ((AbstractButton) destino).setIcon(new ImageIcon(imagenEscalada));
                ((AbstractButton) destino).setHorizontalTextPosition(SwingConstants.RIGHT);
            } else if (destino instanceof JLabel) {
                ((JLabel) destino).setIcon(new ImageIcon(imagenEscalada));
            }
        }
    }

    public JButton getBtnBuscar() { return btnBuscar; }
    public JTextField getTxtCodigo() { return txtCodigo; }
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtPrecio() { return txtPrecio; }
    public JButton getBtnAnadir() { return btnAnadir; }
    public JTable getTblProductos() { return tblProductos; }
    public JTextField getTxtSubtotal() { return txtSubtotal; }
    public JTextField getTxtIva() { return txtIva; }
    public JTextField getTxtTotal() { return txtTotal; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JComboBox getCbxCantidad() { return cbxCantidad; }
    public JPanel getPanelPrincipal() { return panelPrincipal; }
    public JTextField getTextUsername() { return textUsername; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public MensajeInternacionalizacionHandler getMensajeInternacionalizacion() { return mensajeInternacionalizacionHandler; }

    /**
     * Limpia todos los campos del formulario y la tabla.
     */
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        cbxCantidad.setSelectedIndex(0);
        txtSubtotal.setText("");
        txtIva.setText("");
        txtTotal.setText("");
        textUsername.setText("");
        ((DefaultTableModel) tblProductos.getModel()).setRowCount(0);
    }

    /**
     * Muestra un cuadro de diálogo emergente con un mensaje.
     *
     * @param mensaje texto a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Establece el nombre de usuario en el campo correspondiente.
     *
     * @param username nombre del usuario autenticado.
     */
    public void setNombreUsuario(String username) {
        this.textUsername.setText(username);
        this.textUsername.setEditable(false);
    }

    /**
     * Alias para {@link #setNombreUsuario(String)}.
     *
     * @param username nombre de usuario.
     */
    public void setUsu(String username) {
        setNombreUsuario(username);
    }

    /**
     * Actualiza todos los textos visibles de la vista, utilizando el archivo de internacionalización actual.
     */
    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("carrito.titulo"));
        lblCodigo.setText(mensajeInternacionalizacionHandler.get("producto.label.codigo"));
        lblNombre.setText(mensajeInternacionalizacionHandler.get("producto.label.nombre"));
        lblPrecio.setText(mensajeInternacionalizacionHandler.get("producto.label.precio"));
        lblCantidad.setText(mensajeInternacionalizacionHandler.get("producto.label.cantidad"));
        lblUsuario.setText(mensajeInternacionalizacionHandler.get("carrito.label.usuario"));

        btnBuscar.setText(mensajeInternacionalizacionHandler.get("boton.buscar"));
        btnAnadir.setText(mensajeInternacionalizacionHandler.get("carrito.boton.anadir"));
        btnGuardar.setText(mensajeInternacionalizacionHandler.get("boton.guardar"));
        btnLimpiar.setText(mensajeInternacionalizacionHandler.get("boton.limpiar"));
        btnEliminar.setText(mensajeInternacionalizacionHandler.get("boton.eliminar"));

        configurarTabla();
    }

    /**
     * Establece el nombre del usuario autenticado en la vista.
     *
     * @param usuario el objeto usuario autenticado.
     */
    public void setUsuarioAutenticado(Usuario usuario) {
        textUsername.setText(usuario.getUsername());
    }
}
