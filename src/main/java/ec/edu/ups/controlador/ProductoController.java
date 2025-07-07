package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.Carrito.CarritoAnadirView;
import ec.edu.ups.vista.Producto.EliminarProductoView;
import ec.edu.ups.vista.Producto.ModificarProductoView;
import ec.edu.ups.vista.Producto.ProductoAnadirView;
import ec.edu.ups.vista.Producto.ProductoListaView;

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

        eliminarProductoView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoParaEliminar();
            }
        });

        eliminarProductoView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        modificarProductoView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoParaModificar();
            }
        });

        modificarProductoView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarProducto();
            }
        });
    }

    private void guardarProducto() {
        try {
            int codigo = Integer.parseInt(productoAnadirView.getTxtCodigo().getText());
            String nombre = productoAnadirView.getTxtNombre().getText();
            double precio = Double.parseDouble(productoAnadirView.getTxtPrecio().getText());

            productoDAO.crear(new Producto(codigo, nombre, precio));
            productoAnadirView.mostrarMensaje("Producto guardado correctamente");
            productoAnadirView.limpiarCampos();
        } catch (NumberFormatException ex) {
            productoAnadirView.mostrarMensaje("Error: Verifique los datos ingresados");
        }
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
        try {
            int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);
            if (producto != null) {
                carritoAnadirView.getTxtNombre().setText(producto.getNombre());
                carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
            } else {
                carritoAnadirView.mostrarMensaje("Producto no encontrado");
            }
        } catch (NumberFormatException ex) {
            carritoAnadirView.mostrarMensaje("Código inválido");
        }
    }

    private void buscarProductoParaEliminar() {
        try {
            int codigo = Integer.parseInt(eliminarProductoView.getTextCodigo().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);
            eliminarProductoView.mostrarProducto(producto);
        } catch (NumberFormatException ex) {
            eliminarProductoView.mostrarMensaje("Código inválido");
        }
    }

    private void eliminarProducto() {
        try {
            int codigo = Integer.parseInt(eliminarProductoView.getTextCodigo().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);
            if (producto != null) {
                productoDAO.eliminar(codigo);
                eliminarProductoView.mostrarMensaje("Producto eliminado correctamente");
                eliminarProductoView.limpiarCampos();
            } else {
                eliminarProductoView.mostrarMensaje("Producto no encontrado");
            }
        } catch (NumberFormatException ex) {
            eliminarProductoView.mostrarMensaje("Código inválido");
        }
    }

    private void buscarProductoParaModificar() {
        try {
            int codigo = Integer.parseInt(modificarProductoView.getTextCodigo().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);
            if (producto != null) {
                modificarProductoView.getTextProducto().setText(producto.getNombre());
                modificarProductoView.getTextPrecio().setText(String.valueOf(producto.getPrecio()));
            } else {
                modificarProductoView.mostrarMensaje("Producto no encontrado");
            }
        } catch (NumberFormatException ex) {
            modificarProductoView.mostrarMensaje("Código inválido");
        }
    }

    private void modificarProducto() {
        try {
            int codigo = Integer.parseInt(modificarProductoView.getTextCodigo().getText());
            String nombre = modificarProductoView.getTextProducto().getText();
            double precio = Double.parseDouble(modificarProductoView.getTextPrecio().getText());

            Producto producto = productoDAO.buscarPorCodigo(codigo);
            if (producto != null) {
                producto.setNombre(nombre);
                producto.setPrecio(precio);
                productoDAO.actualizar(producto);
                modificarProductoView.mostrarMensaje("Producto modificado correctamente");
                modificarProductoView.limpiarCampos();
            } else {
                modificarProductoView.mostrarMensaje("Producto no encontrado");
            }
        } catch (NumberFormatException ex) {
            modificarProductoView.mostrarMensaje("Datos inválidos");
        }
    }
}
