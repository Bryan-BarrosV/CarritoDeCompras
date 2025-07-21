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

/**
 * Controlador para gestionar las operaciones relacionadas con productos,
 * incluyendo la creación, búsqueda, modificación y eliminación de productos.
 * Interactúa con las vistas y el DAO correspondiente.
 */
public class ProductoController {

    private final ProductoAnadirView productoAnadirView;
    private final ProductoListaView productoListaView;
    private final CarritoAnadirView carritoAnadirView;
    private ProductoDAO productoDAO;
    private final EliminarProductoView eliminarProductoView;
    private final ModificarProductoView modificarProductoView;

    /**
     * Constructor que inicializa las vistas y el DAO, y configura los eventos.
     */
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

    /**
     * Registra los listeners de acciones en todas las vistas relacionadas con producto.
     */
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

    /**
     * Guarda un nuevo producto ingresado desde la vista de anadir.
     */
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

    /**
     * Busca productos por nombre y los muestra en la vista correspondiente.
     */
    private void buscarProducto() {
        String nombre = productoListaView.getTxtBuscar().getText();
        List<Producto> productosEncontrados = productoDAO.buscarPorNombre(nombre);
        productoListaView.cargarDatos(productosEncontrados);
    }

    /**
     * Lista todos los productos disponibles en la vista de lista.
     */
    private void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        productoListaView.cargarDatos(productos);
    }

    /**
     * Busca un producto por código en la vista de carrito y muestra sus datos.
     */
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

    /**
     * Busca un producto por código en la vista de eliminación y muestra sus datos.
     */
    private void buscarProductoParaEliminar() {
        try {
            int codigo = Integer.parseInt(eliminarProductoView.getTextCodigo().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);
            eliminarProductoView.mostrarProducto(producto);
        } catch (NumberFormatException ex) {
            eliminarProductoView.mostrarMensaje("Código inválido");
        }
    }

    /**
     * Elimina un producto si existe y actualiza la vista.
     */
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

    /**
     * Busca un producto para modificarlo y carga sus datos en la vista.
     */
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

    /**
     * Modifica los datos de un producto existente y actualiza la vista.
     */
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

    /**
     * Permite actualizar el DAO de producto en tiempo de ejecución.
     *
     * @param productoDAO nueva implementación de {@link ProductoDAO}
     */
    public void setProductoDAO(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }
}
