package ec.edu.ups.vista.Producto;

import ec.edu.ups.modelo.Producto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;
import java.util.List;

/**
 * Vista interna que permite listar y buscar productos en el sistema.
 * Muestra los productos en una tabla con soporte para internacionalización.
 */
public class ProductoListaView extends JInternalFrame {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JTable tblProductos;
    private JPanel panelPrincipal;
    private JButton btnListar;
    private JLabel lblNombre;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    /**
     * Constructor que inicializa la vista con sus componentes, íconos y textos internacionalizados.
     * @param handler Manejador de internacionalización.
     */
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

        // Icono de listar
        URL listarURL = ProductoListaView.class.getClassLoader().getResource("imagenes/listar.png");
        if (listarURL != null) {
            ImageIcon iconoListar = new ImageIcon(listarURL);
            Image imgListar = iconoListar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnListar.setIcon(new ImageIcon(imgListar));
            btnListar.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró listar.png");
        }

        // Icono de buscar
        URL buscarURL = ProductoListaView.class.getClassLoader().getResource("imagenes/buscar.png");
        if (buscarURL != null) {
            ImageIcon iconoBuscar = new ImageIcon(buscarURL);
            Image imgBuscar = iconoBuscar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            btnBuscar.setIcon(new ImageIcon(imgBuscar));
            btnBuscar.setHorizontalTextPosition(SwingConstants.RIGHT);
        } else {
            System.err.println("No se encontró buscar.png");
        }

        // Icono de nombre
        URL nombreURL = ProductoListaView.class.getClassLoader().getResource("imagenes/productonombre.png");
        if (nombreURL != null) {
            ImageIcon iconoNombre = new ImageIcon(nombreURL);
            Image imgNombre = iconoNombre.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            lblNombre.setIcon(new ImageIcon(imgNombre));
        } else {
            System.err.println("No se encontró productonombre.png");
        }
    }

    /** @return Campo de texto para ingresar nombre a buscar */
    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    /** @param txtBuscar Campo de texto para búsqueda */
    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    /** @return Botón para realizar búsqueda */
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    /** @param btnBuscar Botón para realizar búsqueda */
    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    /** @return Tabla que contiene los productos */
    public JTable getTblProductos() {
        return tblProductos;
    }

    /** @param tblProductos Tabla de productos */
    public void setTblProductos(JTable tblProductos) {
        this.tblProductos = tblProductos;
    }

    /** @return Panel principal de la vista */
    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    /** @param panelPrincipal Panel principal */
    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    /** @return Botón para listar todos los productos */
    public JButton getBtnListar() {
        return btnListar;
    }

    /** @param btnListar Botón de listado */
    public void setBtnListar(JButton btnListar) {
        this.btnListar = btnListar;
    }

    /** @return Modelo de la tabla */
    public DefaultTableModel getModelo() {
        return modelo;
    }

    /** @param modelo Modelo de tabla para productos */
    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    /**
     * Carga los datos de una lista de productos en la tabla.
     * @param listaProductos Lista de productos a mostrar.
     */
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

    /**
     * Actualiza los textos visibles en la vista según el idioma actual.
     */
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