package ec.edu.ups.vista.Producto;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

/**
 * Vista interna para añadir un nuevo producto al sistema.
 * Permite ingresar código, nombre y precio, y ofrece funcionalidades para aceptar o limpiar los datos ingresados.
 * La vista es internacionalizable mediante {@link MensajeInternacionalizacionHandler}.
 */
public class ProductoAnadirView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtPrecio;
    private JTextField txtNombre;
    private JTextField txtCodigo;
    private JButton btnAceptar;
    private JButton btnLimpiar;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblPrecio;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    /**
     * Constructor que configura la interfaz gráfica, aplica internacionalización e íconos.
     * @param handler Manejador de internacionalización.
     */
    public ProductoAnadirView(MensajeInternacionalizacionHandler handler) {
        this.mensajeInternacionalizacionHandler = handler;

        setContentPane(panelPrincipal);
        setTitle(mensajeInternacionalizacionHandler.get("producto.anadir.titulo"));
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setVisible(false);
        actualizarTextos();

        // Icono para código
        URL codigoURL = ProductoAnadirView.class.getClassLoader().getResource("imagenes/codigo.png");
        if (codigoURL != null) {
            ImageIcon iconoCodigo = new ImageIcon(codigoURL);
            Image imgCodigo = iconoCodigo.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblCodigo.setIcon(new ImageIcon(imgCodigo));
        }

        // Icono para nombre
        URL nombreURL = ProductoAnadirView.class.getClassLoader().getResource("imagenes/productonombre.png");
        if (nombreURL != null) {
            ImageIcon iconoNombre = new ImageIcon(nombreURL);
            Image imgNombre = iconoNombre.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblNombre.setIcon(new ImageIcon(imgNombre));
        }

        // Icono para precio
        URL precioURL = ProductoAnadirView.class.getClassLoader().getResource("imagenes/precio.png");
        if (precioURL != null) {
            ImageIcon iconoPrecio = new ImageIcon(precioURL);
            Image imgPrecio = iconoPrecio.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblPrecio.setIcon(new ImageIcon(imgPrecio));
        }

        // Icono para botón aceptar
        URL aceptarURL = ProductoAnadirView.class.getClassLoader().getResource("imagenes/aceptar.png");
        if (aceptarURL != null) {
            ImageIcon iconoAceptar = new ImageIcon(aceptarURL);
            Image imgAceptar = iconoAceptar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnAceptar.setIcon(new ImageIcon(imgAceptar));
            btnAceptar.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

        // Icono para botón limpiar
        URL limpiarURL = ProductoAnadirView.class.getClassLoader().getResource("imagenes/limpiar.png");
        if (limpiarURL != null) {
            ImageIcon iconoLimpiar = new ImageIcon(limpiarURL);
            Image imgLimpiar = iconoLimpiar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnLimpiar.setIcon(new ImageIcon(imgLimpiar));
            btnLimpiar.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

        // Acción para limpiar campos
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    /** @return Panel principal del formulario */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /** @param panelPrincipal Panel principal del formulario */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /** @return Campo de texto para precio */
    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    /** @param txtPrecio Campo de texto para precio */
    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    /** @return Campo de texto para nombre */
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    /** @param txtNombre Campo de texto para nombre */
    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    /** @return Campo de texto para código */
    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    /** @param txtCodigo Campo de texto para código */
    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    /** @return Botón aceptar */
    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    /** @param btnAceptar Botón aceptar */
    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    /** @return Botón limpiar */
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    /** @param btnLimpiar Botón limpiar */
    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    /**
     * Muestra un mensaje emergente al usuario.
     * @param mensaje Mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    /**
     * Limpia todos los campos del formulario.
     */
    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    /**
     * Muestra por consola los productos recibidos (modo texto).
     * @param productos Lista de productos a mostrar.
     */
    public void mostrarProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    /**
     * Actualiza los textos visibles de la interfaz con el idioma configurado.
     */
    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("producto.anadir.titulo"));
        lblCodigo.setText(mensajeInternacionalizacionHandler.get("producto.label.codigo"));
        lblNombre.setText(mensajeInternacionalizacionHandler.get("producto.label.nombre"));
        lblPrecio.setText(mensajeInternacionalizacionHandler.get("producto.label.precio"));
        btnAceptar.setText(mensajeInternacionalizacionHandler.get("boton.aceptar"));
        btnLimpiar.setText(mensajeInternacionalizacionHandler.get("boton.limpiar"));
    }
}
