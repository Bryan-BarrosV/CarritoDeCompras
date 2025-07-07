package ec.edu.ups.vista.Producto;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ModificarProductoView extends JInternalFrame {

    private JPanel panelPrincipal;

    private JTextField textCodigo;
    private JTextField textProducto;
    private JTextField textPrecio;
    private JButton btnBuscar;
    private JButton btnGuardar;
    private JLabel lblCodigo;
    private JLabel lblProducto;
    private JLabel lblPrecio;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public ModificarProductoView(MensajeInternacionalizacionHandler handler) {
        this.mensajeInternacionalizacionHandler = handler;

        setTitle("Modificar Producto");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(false);

        URL codigoURL = ModificarProductoView.class.getClassLoader().getResource("imagenes/codigo.png");
        if (codigoURL != null) {
            ImageIcon iconoCodigo = new ImageIcon(codigoURL);
            Image imgCodigo = iconoCodigo.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblCodigo.setIcon(new ImageIcon(imgCodigo));
            lblCodigo.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró el icono codigo.png");
        }

        URL productoURL = ModificarProductoView.class.getClassLoader().getResource("imagenes/productomod.png");
        if (productoURL != null) {
            ImageIcon iconoProducto = new ImageIcon(productoURL);
            Image imgProducto = iconoProducto.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblProducto.setIcon(new ImageIcon(imgProducto));
            lblProducto.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró el icono productomod.png");
        }

        URL precioURL = ModificarProductoView.class.getClassLoader().getResource("imagenes/precio.png");
        if (precioURL != null) {
            ImageIcon iconoPrecio = new ImageIcon(precioURL);
            Image imgPrecio = iconoPrecio.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblPrecio.setIcon(new ImageIcon(imgPrecio));
            lblPrecio.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró el icono precio.png");
        }

        URL buscarURL = ModificarProductoView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (buscarURL != null) {
            ImageIcon iconoBuscar = new ImageIcon(buscarURL);
            Image imgBuscar = iconoBuscar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnBuscar.setIcon(new ImageIcon(imgBuscar));
            btnBuscar.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró buscar.png");
        }

        URL guardarURL = ModificarProductoView.class.getClassLoader().getResource("imagenes/guardar.png");
        if (guardarURL != null) {
            ImageIcon iconoGuardar = new ImageIcon(guardarURL);
            Image imgGuardar = iconoGuardar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnGuardar.setIcon(new ImageIcon(imgGuardar));
            btnGuardar.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró guardar.png");
        }


    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getTextCodigo() {
        return textCodigo;
    }

    public JTextField getTextProducto() {
        return textProducto;
    }

    public JTextField getTextPrecio() {
        return textPrecio;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        textCodigo.setText("");
        textProducto.setText("");
        textPrecio.setText("");
    }
    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("producto.modificar.titulo"));
        lblCodigo.setText(mensajeInternacionalizacionHandler.get("producto.label.codigo"));
        lblProducto.setText(mensajeInternacionalizacionHandler.get("producto.label.nombre"));
        lblPrecio.setText(mensajeInternacionalizacionHandler.get("producto.label.precio"));
        btnBuscar.setText(mensajeInternacionalizacionHandler.get("boton.buscar"));
        btnGuardar.setText(mensajeInternacionalizacionHandler.get("boton.guardar"));
    }

}
