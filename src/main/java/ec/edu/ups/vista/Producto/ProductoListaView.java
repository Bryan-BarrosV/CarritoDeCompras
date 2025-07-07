package ec.edu.ups.vista.Producto;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.util.List;

public class ProductoListaView extends JInternalFrame {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblProductos;
    private JPanel panelPrincipal;
    private JButton btnListar;
    private JLabel lblNombre;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;


    public ProductoListaView(MensajeInternacionalizacionHandler handler) {
        this.mensajeInternacionalizacionHandler = handler;

        setContentPane(panelPrincipal);
        setTitle("Listado de Productos");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);


        modelo = new DefaultTableModel();
        Object[] columnas = {"Codigo", "Nombre", "Precio"};
        modelo.setColumnIdentifiers(columnas);
        tblProductos.setModel(modelo);
        actualizarTextos();

        URL listarURL = ProductoListaView.class.getClassLoader().getResource("imagenes/listar.png");
        if (listarURL != null) {
            ImageIcon iconoListar = new ImageIcon(listarURL);
            Image imgListar = iconoListar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnListar.setIcon(new ImageIcon(imgListar));
            btnListar.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró listar.png");
        }

        URL buscarURL = ProductoListaView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (buscarURL != null) {
            ImageIcon iconoBuscar = new ImageIcon(buscarURL);
            Image imgBuscar = iconoBuscar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnBuscar.setIcon(new ImageIcon(imgBuscar));
            btnBuscar.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró buscar.png");
        }

        URL nombreURL = ProductoListaView.class.getClassLoader().getResource("imagenes/productonombre.png");
        if (nombreURL != null) {
            ImageIcon iconoNombre = new ImageIcon(nombreURL);
            Image imgNombre = iconoNombre.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblNombre.setIcon(new ImageIcon(imgNombre));
        } else {
            System.err.println("No se encontró productonombre.png");
        }
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void cargarDatos(List<Producto> listaProductos) {
        modelo.setNumRows(0);

        for (Producto producto : listaProductos) {
            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            };
            modelo.addRow(fila);
        }

    }
    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("producto.listar.titulo"));
        lblNombre.setText(mensajeInternacionalizacionHandler.get("producto.label.nombre"));
        btnBuscar.setText(mensajeInternacionalizacionHandler.get("boton.buscar"));
        btnListar.setText(mensajeInternacionalizacionHandler.get("boton.listar"));

        modelo.setColumnIdentifiers(new Object[]{
                mensajeInternacionalizacionHandler.get("producto.label.codigo"),
                mensajeInternacionalizacionHandler.get("producto.label.nombre"),
                mensajeInternacionalizacionHandler.get("producto.label.precio")
        });
    }

}