package ec.edu.ups.vista.Carrito;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

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

    public CarritoAnadirView(MensajeInternacionalizacionHandler handler){
        super("Carrito de Compras", true, true, false, true);
        this.mensajeInternacionalizacionHandler = handler;
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);

        DefaultTableModel modelo = new DefaultTableModel();
        Object[] columnas = {
                handler.get("producto.label.codigo"),
                handler.get("producto.label.nombre"),
                handler.get("producto.label.precio"),
                handler.get("producto.label.cantidad"),
                handler.get("carrito.label.subtotal")
        };
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);

        cargarDatos();
        setVisible(false);
        actualizarTextos();

        URL limpiarURL = CarritoAnadirView.class.getClassLoader().getResource("imagenes/limpiar.png");
        if (limpiarURL != null) {
            ImageIcon iconoLimpiar = new ImageIcon(limpiarURL);
            Image imgLimpiar = iconoLimpiar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnLimpiar.setIcon(new ImageIcon(imgLimpiar));
            btnLimpiar.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

        URL anadirCarrURL = CarritoAnadirView.class.getClassLoader().getResource("imagenes/añadircarr.png");
        if (anadirCarrURL != null) {
            ImageIcon iconoAnadir = new ImageIcon(anadirCarrURL);
            Image imgAnadir = iconoAnadir.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnAnadir.setIcon(new ImageIcon(imgAnadir));
            btnAnadir.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

        URL usuarioURL = CarritoAnadirView.class.getClassLoader().getResource("imagenes/usuariocarr.png");
        if (usuarioURL != null) {
            ImageIcon iconoUsuario = new ImageIcon(usuarioURL);
            Image imgUsuario = iconoUsuario.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblUsuario.setIcon(new ImageIcon(imgUsuario));
        }

        URL codigoURL = CarritoAnadirView.class.getClassLoader().getResource("imagenes/codigo.png");
        if (codigoURL != null) {
            ImageIcon iconoCodigo = new ImageIcon(codigoURL);
            Image imgCodigo = iconoCodigo.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblCodigo.setIcon(new ImageIcon(imgCodigo));
        }

        URL nombreURL = CarritoAnadirView.class.getClassLoader().getResource("imagenes/productonombre.png");
        if (nombreURL != null) {
            ImageIcon iconoNombre = new ImageIcon(nombreURL);
            Image imgNombre = iconoNombre.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblNombre.setIcon(new ImageIcon(imgNombre));
        }

        URL precioURL = CarritoAnadirView.class.getClassLoader().getResource("imagenes/precio.png");
        if (precioURL != null) {
            ImageIcon iconoPrecio = new ImageIcon(precioURL);
            Image imgPrecio = iconoPrecio.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblPrecio.setIcon(new ImageIcon(imgPrecio));
        }

        URL buscarURL = CarritoAnadirView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (buscarURL != null) {
            ImageIcon iconoBuscar = new ImageIcon(buscarURL);
            Image imgBuscar = iconoBuscar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnBuscar.setIcon(new ImageIcon(imgBuscar));
            btnBuscar.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

        URL eliminarURL = CarritoAnadirView.class.getClassLoader().getResource("imagenes/basura.png");
        if (eliminarURL != null) {
            ImageIcon iconoEliminar = new ImageIcon(eliminarURL);
            Image imgEliminar = iconoEliminar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnEliminar.setIcon(new ImageIcon(imgEliminar));
            btnEliminar.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

        URL guardarURL = CarritoAnadirView.class.getClassLoader().getResource("imagenes/guardar.png");
        if (guardarURL != null) {
            ImageIcon iconoGuardar = new ImageIcon(guardarURL);
            Image imgGuardar = iconoGuardar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnGuardar.setIcon(new ImageIcon(imgGuardar));
            btnGuardar.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

    }

    private void cargarDatos(){
        cbxCantidad.removeAllItems();
        for(int i = 0; i < 20; i++){
            cbxCantidad.addItem(String.valueOf(i + 1));
        }
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JButton getBtnAnadir() {
        return btnAnadir;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public JTextField getTxtSubtotal() {
        return txtSubtotal;
    }

    public JTextField getTxtIva() {
        return txtIva;
    }

    public JTextField getTxtTotal() {
        return txtTotal;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JComboBox getCbxCantidad() {
        return cbxCantidad;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getTextUsername() { return textUsername; }

    public JButton getBtnEliminar() {return btnEliminar;}

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        cbxCantidad.setSelectedIndex(0);
        txtSubtotal.setText("");
        txtIva.setText("");
        txtTotal.setText("");
        textUsername.setText("");
        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
        modelo.setRowCount(0);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    public void setNombreUsuario(String username) {
        this.textUsername.setText(username);
        this.textUsername.setEditable(false);
    }
    public void setUsu(String username) {
        this.textUsername.setText(username);
        this.textUsername.setEditable(false);
    }
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

        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
        modelo.setColumnIdentifiers(new Object[]{
                mensajeInternacionalizacionHandler.get("producto.label.codigo"),
                mensajeInternacionalizacionHandler.get("producto.label.nombre"),
                mensajeInternacionalizacionHandler.get("producto.label.precio"),
                mensajeInternacionalizacionHandler.get("producto.label.cantidad"),
                mensajeInternacionalizacionHandler.get("carrito.label.subtotal")
        });
    }

    public MensajeInternacionalizacionHandler getMensajeInternacionalizacion() {
        return mensajeInternacionalizacionHandler;
    }
}