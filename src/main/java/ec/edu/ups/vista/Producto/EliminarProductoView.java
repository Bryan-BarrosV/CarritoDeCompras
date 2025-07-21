package ec.edu.ups.vista.Producto;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Vista interna que permite buscar y eliminar un producto del sistema mediante su código.
 * Utiliza internacionalización para los textos y carga iconos para mejorar la interfaz.
 */
public class EliminarProductoView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField textCodigo;
    private JTextField textProducto;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel lblCodigo;
    private JLabel lblProducto;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    /**
     * Constructor que inicializa los componentes de la vista y carga iconos e internacionalización.
     * @param handler manejador de internacionalización para traducciones dinámicas.
     */
    public EliminarProductoView(MensajeInternacionalizacionHandler handler) {
        this.mensajeInternacionalizacionHandler = handler;
        setTitle(mensajeInternacionalizacionHandler.get("producto.eliminar.titulo"));
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(false);
        actualizarTextos();

        // Ícono del campo código
        URL codigoURL = EliminarProductoView.class.getClassLoader().getResource("imagenes/codigo.png");
        if (codigoURL != null) {
            ImageIcon icono = new ImageIcon(codigoURL);
            Image img = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblCodigo.setIcon(new ImageIcon(img));
            lblCodigo.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró el icono codigo.png");
        }

        // Ícono del campo producto
        URL productoURL = EliminarProductoView.class.getClassLoader().getResource("imagenes/productoeli.png");
        if (productoURL != null) {
            ImageIcon icono = new ImageIcon(productoURL);
            Image img = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblProducto.setIcon(new ImageIcon(img));
            lblProducto.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró el icono productoeli.png");
        }

        // Ícono del botón eliminar
        URL eliminarURL = EliminarProductoView.class.getClassLoader().getResource("imagenes/basura.png");
        if (eliminarURL != null) {
            ImageIcon icono = new ImageIcon(eliminarURL);
            Image imagenEscalada = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            Icon iconoEliminar = new ImageIcon(imagenEscalada);
            btnEliminar.setIcon(iconoEliminar);
            btnEliminar.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró el icono borrar.png");
        }

        // Ícono del botón buscar
        URL buscarURL = EliminarProductoView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (buscarURL != null) {
            ImageIcon icono = new ImageIcon(buscarURL);
            Image imagenEscalada = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            Icon iconoBuscar = new ImageIcon(imagenEscalada);
            btnBuscar.setIcon(iconoBuscar);
            btnBuscar.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró el icono buscar.png");
        }
    }

    /**
     * Devuelve el panel principal de la vista.
     * @return panel principal.
     */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /**
     * Devuelve el campo de texto para ingresar el código del producto.
     * @return campo de texto del código.
     */
    public JTextField getTextCodigo() {
        return textCodigo;
    }

    /**
     * Devuelve el campo de texto que muestra el nombre del producto.
     * @return campo de texto del producto.
     */
    public JTextField getTextProducto() {
        return textProducto;
    }

    /**
     * Devuelve el botón para buscar productos.
     * @return botón de búsqueda.
     */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /**
     * Devuelve el botón para eliminar productos.
     * @return botón de eliminación.
     */
    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    /**
     * Muestra un mensaje en un cuadro de diálogo.
     * @param mensaje el mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia los campos de texto de la vista.
     */
    public void limpiarCampos() {
        textCodigo.setText("");
        textProducto.setText("");
    }

    /**
     * Muestra la información del producto en la vista, o limpia si no se encuentra.
     * @param producto el producto a mostrar.
     */
    public void mostrarProducto(Producto producto) {
        if (producto != null) {
            textProducto.setText(producto.getNombre());
        } else {
            mostrarMensaje("Producto no encontrado.");
            limpiarCampos();
        }
    }

    /**
     * Actualiza los textos de los componentes según el idioma seleccionado.
     */
    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("producto.eliminar.titulo"));
        lblCodigo.setText(mensajeInternacionalizacionHandler.get("producto.label.codigo"));
        lblProducto.setText(mensajeInternacionalizacionHandler.get("producto.label.nombre"));
        btnBuscar.setText(mensajeInternacionalizacionHandler.get("boton.buscar"));
        btnEliminar.setText(mensajeInternacionalizacionHandler.get("boton.eliminar"));
    }
}
