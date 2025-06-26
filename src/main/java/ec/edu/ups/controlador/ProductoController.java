package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.*;
import ec.edu.ups.vista.ModificarProductoView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoController {

    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final CarritoAnadirView carritoAnadirView;
    private final ProductoDAO productoDAO;
    private final EliminarProductoView eliminarProductoView;
    private final ModificarProductoView modificarProductoView;

    public ProductoController(ProductoDAO productoDAO,
                              ProductoAnadirView productoAnadirView,
                              ProductoListaView productoListaView,
                              CarritoAnadirView carritoAnadirView,
                              EliminarProductoView eliminarProductoView,
                              ModificarProductoView modificarProductoView) {

        this.productoDAO = productoDAO;
        this.productoAnadirView = productoAnadirView;
        this.productoListaView = productoListaView;
        this.carritoAnadirView = carritoAnadirView;
        this.eliminarProductoView = eliminarProductoView;
        this.modificarProductoView = modificarProductoView;
        this.configurarEventosEnVistas();
    }

    private void configurarEventosEnVistas() {
        productoAnadirView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarProducto();
            }
        });

        productoListaView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        productoListaView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });

        carritoAnadirView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoPorCodigo();
            }
        });

        eliminarProductoView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoParaEliminar();
            }
        });

        eliminarProductoView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProductoSeleccionado();
            }
        });

        modificarProductoView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoParaModificar();
            }
        });

        modificarProductoView.getModificarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarProductoSeleccionado();
            }
        });
    }

    private void buscarProductoParaModificar() {
        String nombreBusqueda = modificarProductoView.getTextProducto().getText();
        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombreBusqueda);
        modificarProductoView.cargarDatosTabla(productosEncontrados);
    }

    private void modificarProductoSeleccionado() {
        int filaSeleccionada = modificarProductoView.getTblProductos().getSelectedRow();
        if (filaSeleccionada >= 0) {
            int codigo = (int) modificarProductoView.getTblProductos().getValueAt(filaSeleccionada, 0);
            String nuevoNombre = modificarProductoView.getTextNombre().getText();
            double nuevoPrecio = Double.parseDouble(modificarProductoView.getTextPrecio().getText());

            Producto productoModificado = new Producto(codigo, nuevoNombre, nuevoPrecio);
            productoDAO.actualizar(productoModificado);

            String nombreBusqueda = modificarProductoView.getTextProducto().getText();
            List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombreBusqueda);
            modificarProductoView.cargarDatosTabla(productosEncontrados);

            JOptionPane.showMessageDialog(modificarProductoView, "Producto modificado correctamente");
        } else {
            JOptionPane.showMessageDialog(modificarProductoView, "Por favor, seleccione un producto para modificar");
        }
    }


    private void buscarProductoParaEliminar() {
        String nombreBusqueda = eliminarProductoView.getTextField1().getText().trim();
        if (!nombreBusqueda.isEmpty()) {
            List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombreBusqueda);
            eliminarProductoView.cargarDatosTabla(productosEncontrados);
        } else {
            List<Producto> todosLosProductos = productoDAO.listarTodos();
            eliminarProductoView.cargarDatosTabla(todosLosProductos);
        }
    }

    private void eliminarProductoSeleccionado() {
        int filaSeleccionada = eliminarProductoView.getTblProductos().getSelectedRow();
        if (filaSeleccionada >= 0) {
            int codigo = (int) eliminarProductoView.getTblProductos().getValueAt(filaSeleccionada, 0);
            String nombre = (String) eliminarProductoView.getTblProductos().getValueAt(filaSeleccionada, 1);

            int confirmacion = JOptionPane.showConfirmDialog(
                    eliminarProductoView,
                    "¿Está seguro que desea eliminar el producto:\n" +
                            "Código: " + codigo + "\n" +
                            "Nombre: " + nombre + "?",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                productoDAO.eliminar(codigo);
                buscarProductoParaEliminar();
                JOptionPane.showMessageDialog(
                        eliminarProductoView,
                        "Producto eliminado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        } else {
            JOptionPane.showMessageDialog(
                    eliminarProductoView,
                    "Por favor, seleccione un producto para eliminar",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void guardarProducto() {
        int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
        String nombre = productoAnadirView.getTxtNombre().getText();
        double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

        productoDAO.crear(new Producto(codigo, nombre, precio));
        productoAnadirView.mostrarMensaje("Producto guardado correctamente");
        productoAnadirView.limpiarCampos();
        productoAnadirView.mostrarProductos(productoDAO.listarTodos());
    }

    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();

        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }

    private void buscarProductoPorCodigo() {
        int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        if (producto == null) {
            carritoAnadirView.mostrarMensaje("No se encontro el producto");
            carritoAnadirView.getTxtNombre().setText("");
            carritoAnadirView.getTxtPrecio().setText("");
        } else {
            carritoAnadirView.getTxtNombre().setText(producto.getNombre());
            carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
        }

    }
}