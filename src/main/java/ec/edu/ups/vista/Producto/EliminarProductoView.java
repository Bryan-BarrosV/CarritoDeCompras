package ec.edu.ups.vista.Producto;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class EliminarProductoView extends JInternalFrame {
    private JPanel panelPrincipal;
    private JTextField textCodigo;
    private JTextField textProducto;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JLabel lblCodigo;
    private JLabel lblProducto;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public EliminarProductoView(MensajeInternacionalizacionHandler handler) {
        this.mensajeInternacionalizacionHandler=handler;
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

        URL codigoURL = EliminarProductoView.class.getClassLoader().getResource("imagenes/codigo.png");
        if (codigoURL != null) {
            ImageIcon icono = new ImageIcon(codigoURL);
            Image img = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblCodigo.setIcon(new ImageIcon(img));
            lblCodigo.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró el icono codigo.png");
        }

        URL productoURL = EliminarProductoView.class.getClassLoader().getResource("imagenes/productoeli.png");
        if (productoURL != null) {
            ImageIcon icono = new ImageIcon(productoURL);
            Image img = icono.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblProducto.setIcon(new ImageIcon(img));
            lblProducto.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró el icono productoeli.png");
        }


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

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getTextCodigo() {
        return textCodigo;
    }

    public JTextField getTextProducto() {
        return textProducto;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        textCodigo.setText("");
        textProducto.setText("");
    }

    public void mostrarProducto(Producto producto) {
        if (producto != null) {
            textProducto.setText(producto.getNombre());
        } else {
            mostrarMensaje("Producto no encontrado.");
            limpiarCampos();
        }
    }
    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("producto.eliminar.titulo"));
        lblCodigo.setText(mensajeInternacionalizacionHandler.get("producto.label.codigo"));
        lblProducto.setText(mensajeInternacionalizacionHandler.get("producto.label.nombre"));
        btnBuscar.setText(mensajeInternacionalizacionHandler.get("boton.buscar"));
        btnEliminar.setText(mensajeInternacionalizacionHandler.get("boton.eliminar"));
    }

}
