package ec.edu.ups.vista.Producto;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

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

        URL codigoURL = ProductoAnadirView.class.getClassLoader().getResource("imagenes/codigo.png");
        if (codigoURL != null) {
            ImageIcon iconoCodigo = new ImageIcon(codigoURL);
            Image imgCodigo = iconoCodigo.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblCodigo.setIcon(new ImageIcon(imgCodigo));
        }

        URL nombreURL = ProductoAnadirView.class.getClassLoader().getResource("imagenes/productonombre.png");
        if (nombreURL != null) {
            ImageIcon iconoNombre = new ImageIcon(nombreURL);
            Image imgNombre = iconoNombre.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblNombre.setIcon(new ImageIcon(imgNombre));
        }

        URL precioURL = ProductoAnadirView.class.getClassLoader().getResource("imagenes/precio.png");
        if (precioURL != null) {
            ImageIcon iconoPrecio = new ImageIcon(precioURL);
            Image imgPrecio = iconoPrecio.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblPrecio.setIcon(new ImageIcon(imgPrecio));
        }

        URL aceptarURL = ProductoAnadirView.class.getClassLoader().getResource("imagenes/aceptar.png");
        if (aceptarURL != null) {
            ImageIcon iconoAceptar = new ImageIcon(aceptarURL);
            Image imgAceptar = iconoAceptar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnAceptar.setIcon(new ImageIcon(imgAceptar));
            btnAceptar.setHorizontalTextPosition(SwingConstants.RIGHT);
        }

        URL limpiarURL = ProductoAnadirView.class.getClassLoader().getResource("imagenes/limpiar.png");
        if (limpiarURL != null) {
            ImageIcon iconoLimpiar = new ImageIcon(limpiarURL);
            Image imgLimpiar = iconoLimpiar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnLimpiar.setIcon(new ImageIcon(imgLimpiar));
            btnLimpiar.setHorizontalTextPosition(SwingConstants.RIGHT);
        }



        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(JTextField txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public void setBtnAceptar(JButton btnAceptar) {
        this.btnAceptar = btnAceptar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(JButton btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    public void mostrarProductos(List<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }
    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("producto.anadir.titulo"));
        lblCodigo.setText(mensajeInternacionalizacionHandler.get("producto.label.codigo"));
        lblNombre.setText(mensajeInternacionalizacionHandler.get("producto.label.nombre"));
        lblPrecio.setText(mensajeInternacionalizacionHandler.get("producto.label.precio"));
        btnAceptar.setText(mensajeInternacionalizacionHandler.get("boton.aceptar"));
        btnLimpiar.setText(mensajeInternacionalizacionHandler.get("boton.limpiar"));
    }
}
