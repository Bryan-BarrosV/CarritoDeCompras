package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.ModificarProductoView;
import ec.edu.ups.vista.ProductoAnadirView;
import ec.edu.ups.vista.ProductoListaView;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final ModificarProductoView modificarProductoView;
    private final ProductoDAO productoDAO;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              ModificarProductoView modificarProductoView) {
        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.modificarProductoView = modificarProductoView;
        configurarEventos();
    }

    private void configurarEventos() {
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoLista();
            }
        });

        productoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductosLista();
            }
        });

        modificarProductoView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoModificar();
            }
        });

        modificarProductoView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        modificarProductoView.getModificarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarProducto();
            }
        });
    }

    private void guardarProducto() {
        if (productoAnadirView.getTxtCodigo().getText().isEmpty() ||
                productoAnadirView.getTxtNombre().getText().isEmpty() ||
                productoAnadirView.getTxtPrecio().getText().isEmpty()) {
            productoAnadirView.mostrarMensaje("Todos los campos son obligatorios.");
            return;
        }

        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        Producto productoExistente = productoDAO.buscarPorCodigo(codigo);
        if (productoExistente == null) {
            productoDAO.crear(new Producto(codigo, nombre, precio));
            productoAnadirView.mostrarMensaje("Producto guardado correctamente.");
        } else {
            productoAnadirView.mostrarMensaje("Error: Ya existe un producto con ese código.");
        }

        productoAnadirView.limpiarCampos();
        listarProductosLista();
        buscarProductoModificar();
    }

    private void buscarProductoLista() {
        String nombre = productoListaView.getTxtBuscar().getText();
        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    private void listarProductosLista() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }

    private void buscarProductoModificar() {
        String nombreBusqueda = modificarProductoView.getTextField1().getText();
        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombreBusqueda);
        modificarProductoView.cargarDatosTabla(productosEncontrados);
    }

    private void eliminarProducto() {
        int filaSeleccionada = modificarProductoView.getTblProductos().getSelectedRow();
        if (filaSeleccionada != -1) {
            int codigo = (int) modificarProductoView.getTblProductos().getModel().getValueAt(filaSeleccionada, 0);

            productoDAO.eliminar(codigo);
            JOptionPane.showMessageDialog(modificarProductoView, "Producto eliminado correctamente.");
            buscarProductoModificar();
            listarProductosLista();
        } else {
            JOptionPane.showMessageDialog(modificarProductoView, "Seleccione un producto para eliminar.");
        }
    }

    private void modificarProducto() {
        int filaSeleccionada = modificarProductoView.getTblProductos().getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(modificarProductoView, "Seleccione un producto en la tabla para modificar.");
            return;
        }

        int codigo = (int) modificarProductoView.getTblProductos().getModel().getValueAt(filaSeleccionada, 0);
        String nuevoNombre = modificarProductoView.getTextNombre().getText();
        String nuevoPrecioStr = modificarProductoView.getTextPrecio().getText();

        if (nuevoNombre.isEmpty() && nuevoPrecioStr.isEmpty()) {
            JOptionPane.showMessageDialog(modificarProductoView, "Ingrese el nuevo nombre o precio para modificar.");
            return;
        }

        Producto productoAModificar = productoDAO.buscarPorCodigo(codigo);
        if (productoAModificar != null) {
            if (!nuevoNombre.isEmpty()) {
                productoAModificar.setNombre(nuevoNombre);
            }
            if (!nuevoPrecioStr.isEmpty()) {
                double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
                productoAModificar.setPrecio(nuevoPrecio);
            }
            productoDAO.actualizar(productoAModificar);
            JOptionPane.showMessageDialog(modificarProductoView, "Producto modificado correctamente.");
            modificarProductoView.getTextField1().setText("");
            modificarProductoView.getTextNombre().setText("");
            modificarProductoView.getTextPrecio().setText("");
            buscarProductoModificar();
            listarProductosLista();
        } else {
            JOptionPane.showMessageDialog(modificarProductoView, "Error: Producto no encontrado para modificar.");
        }
    }
}