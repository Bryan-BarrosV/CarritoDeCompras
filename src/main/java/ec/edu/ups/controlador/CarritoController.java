package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.FormateadorUtils;
import ec.edu.ups.vista.Carrito.CarritoAnadirView;
import ec.edu.ups.vista.Carrito.CarritoModificarView;
import ec.edu.ups.vista.Carrito.EliminarCarritoView;
import ec.edu.ups.vista.Carrito.ListarCarritoView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Controlador encargado de gestionar la lógica de negocio relacionada con carritos de compra.
 *
 * <p>Se encarga de enlazar las vistas de carrito (añadir, modificar, eliminar, listar)
 * con las operaciones de persistencia mediante {@link CarritoDAO} y consulta de productos
 * mediante {@link ProductoDAO}.</p>
 */
public class CarritoController {

    /** DAO para persistencia de carritos. */
    private CarritoDAO carritoDAO;
    /** DAO para consulta de productos. */
    private final ProductoDAO productoDAO;
    /** Vista para añadir productos al carrito. */
    private final CarritoAnadirView carritoAnadirView;
    /** Vista para eliminar carritos existentes. */
    private final EliminarCarritoView eliminarCarritoView;
    /** Vista para listar carritos. */
    private final ListarCarritoView listarCarritoView;
    /** Vista para modificar carritos existentes. */
    private final CarritoModificarView carritoModificarView;
    /** Usuario actualmente autenticado. */
    private Usuario usuarioAutenticado;

    /**
     * Construye un controlador de carrito con sus DAOs y vistas asociadas,
     * e inicializa la configuración de eventos.
     *
     * @param carritoDAO            DAO para la gestión de carritos
     * @param productoDAO           DAO para la gestión de productos
     * @param carritoAnadirView     vista para añadir productos
     * @param eliminarCarritoView   vista para eliminar carritos
     * @param listarCarritoView     vista para listar carritos
     * @param carritoModificarView  vista para modificar carritos
     */
    public CarritoController(
            CarritoDAO carritoDAO,
            ProductoDAO productoDAO,
            CarritoAnadirView carritoAnadirView,
            EliminarCarritoView eliminarCarritoView,
            ListarCarritoView listarCarritoView,
            CarritoModificarView carritoModificarView
    ) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.eliminarCarritoView = eliminarCarritoView;
        this.listarCarritoView = listarCarritoView;
        this.carritoModificarView = carritoModificarView;

        configurarEventos();
    }

    /**
     * Configura los listeners de acción para los botones de todas las vistas.
     */
    private void configurarEventos() {
        carritoAnadirView.getBtnBuscar().addActionListener(e -> buscarProductoAnadirView());
        carritoAnadirView.getBtnAnadir().addActionListener(e -> anadirProductoATabla());
        carritoAnadirView.getBtnEliminar().addActionListener(e -> eliminarProductoDeTabla());
        carritoAnadirView.getBtnGuardar().addActionListener(e -> {
            Carrito carrito = new Carrito();
            DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
            for (int i = 0; i < modelo.getRowCount(); i++) {
                int codigo = (int) modelo.getValueAt(i, 0);
                int cantidad = (int) modelo.getValueAt(i, 3);
                Producto producto = productoDAO.buscarPorCodigo(codigo);
                carrito.agregarProducto(producto, cantidad);
            }
            carritoDAO.crear(carrito);
            carritoAnadirView.mostrarMensaje("Carrito guardado exitosamente.");
            carritoAnadirView.limpiarCampos();
        });

        eliminarCarritoView.getEliminarButton().addActionListener(e -> eliminarCarrito());
        eliminarCarritoView.getBuscarButton().addActionListener(e -> buscarCarritosParaEliminar());
        listarCarritoView.getBtnListar().addActionListener(e -> listarCarritos());
        carritoModificarView.getBtnBuscar().addActionListener(e -> buscarCarritoParaModificar());
        carritoModificarView.getBtnModificar().addActionListener(e -> modificarCarrito());
    }

    /**
     * Busca un producto por su código en la vista de añadir carrito y muestra sus datos.
     */
    private void buscarProductoAnadirView() {
        try {
            int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);
            if (producto != null) {
                carritoAnadirView.getTxtNombre().setText(producto.getNombre());
                carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
            } else {
                carritoAnadirView.mostrarMensaje("Producto no encontrado.");
            }
        } catch (NumberFormatException ex) {
            carritoAnadirView.mostrarMensaje("Código inválido.");
        }
    }

    /**
     * Añade el producto seleccionado al modelo de tabla de la vista de añadir carrito
     * y recalcula los totales.
     */
    private void anadirProductoATabla() {
        try {
            int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);
            if (producto == null) {
                carritoAnadirView.mostrarMensaje("Producto no encontrado.");
                return;
            }
            int cantidad = Integer.parseInt(
                    carritoAnadirView.getCbxCantidad().getSelectedItem().toString()
            );
            double subtotal = producto.getPrecio() * cantidad;
            Locale locale = carritoAnadirView
                    .getMensajeInternacionalizacion()
                    .getLocale();
            String subtotalFormateado =
                    FormateadorUtils.formatearMoneda(subtotal, locale);

            DefaultTableModel modelo =
                    (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
            modelo.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    cantidad,
                    subtotalFormateado
            });
            recalcularTotales(
                    modelo,
                    carritoAnadirView.getTxtSubtotal(),
                    carritoAnadirView.getTxtIva(),
                    carritoAnadirView.getTxtTotal()
            );
        } catch (NumberFormatException ex) {
            carritoAnadirView.mostrarMensaje("Código o cantidad inválidos.");
        }
    }

    /**
     * Elimina el producto seleccionado de la tabla y recalcula los totales.
     */
    private void eliminarProductoDeTabla() {
        int fila = carritoAnadirView.getTblProductos().getSelectedRow();
        if (fila != -1) {
            DefaultTableModel modelo =
                    (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
            modelo.removeRow(fila);
            recalcularTotales(
                    modelo,
                    carritoAnadirView.getTxtSubtotal(),
                    carritoAnadirView.getTxtIva(),
                    carritoAnadirView.getTxtTotal()
            );
        } else {
            carritoAnadirView.mostrarMensaje(
                    "Seleccione un producto de la tabla para eliminar."
            );
        }
    }

    /**
     * Elimina el carrito seleccionado en la vista de eliminación.
     */
    private void eliminarCarrito() {
        int fila =
                eliminarCarritoView.getTblProductos().getSelectedRow();
        if (fila != -1) {
            int opcion = JOptionPane.showConfirmDialog(
                    eliminarCarritoView,
                    "¿Está seguro de eliminar?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );
            if (opcion == JOptionPane.YES_OPTION) {
                int codigo = (int)
                        eliminarCarritoView
                                .getTblProductos()
                                .getValueAt(fila, 0);
                carritoDAO.eliminar(codigo);
                eliminarCarritoView.mostrarMensaje("Carrito eliminado.");
                eliminarCarritoView
                        .cargarDatosTabla(carritoDAO.listarTodos());
            }
        } else {
            eliminarCarritoView.mostrarMensaje("Seleccione un carrito.");
        }
    }

    /**
     * Carga todos los carritos en la tabla de la vista de eliminación.
     */
    private void buscarCarritosParaEliminar() {
        eliminarCarritoView.cargarDatosTabla(
                carritoDAO.listarTodos()
        );
    }

    /**
     * Carga todos los carritos en la vista de listado.
     */
    private void listarCarritos() {
        listarCarritoView.cargarDatos(carritoDAO.listarTodos());
    }

    /**
     * Busca y muestra los datos de un carrito en la vista de modificación.
     */
    private void buscarCarritoParaModificar() {
        String codigoTexto =
                carritoModificarView.getTxtCodigo().getText().trim();
        if (codigoTexto.isEmpty()) {
            carritoModificarView.mostrarMensaje("Ingrese un código.");
            return;
        }
        try {
            int codigo = Integer.parseInt(codigoTexto);
            Carrito carrito = carritoDAO.buscarPorCodigo(codigo);
            if (carrito != null) {
                SimpleDateFormat formato =
                        new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                carritoModificarView
                        .getTxtFecha()
                        .setText(
                                formato.format(
                                        carrito.getFechaCreacion().getTime()
                                )
                        );
                DefaultTableModel modelo =
                        carritoModificarView.getModelo();
                modelo.setRowCount(0);
                for (ItemCarrito item : carrito.obtenerItems()) {
                    Producto p = item.getProducto();
                    modelo.addRow(new Object[]{
                            p.getCodigo(),
                            p.getNombre(),
                            p.getPrecio(),
                            item.getCantidad()
                    });
                }
                recalcularTotales(
                        modelo,
                        carritoModificarView.getTxtSubtotal(),
                        carritoModificarView.getTxtIVA(),
                        carritoModificarView.getTxtTotal()
                );
            } else {
                carritoModificarView.mostrarMensaje(
                        "Carrito no encontrado."
                );
            }
        } catch (NumberFormatException ex) {
            carritoModificarView.mostrarMensaje("Código inválido.");
        }
    }

    /**
     * Aplica las modificaciones al carrito y actualiza la base de datos y las vistas.
     */
    private void modificarCarrito() {
        try {
            int codigoCarrito = Integer.parseInt(
                    carritoModificarView.getTxtCodigo().getText().trim()
            );
            Carrito carrito = carritoDAO.buscarPorCodigo(codigoCarrito);
            if (carrito != null) {
                carrito.limpiar();
                DefaultTableModel modelo =
                        (DefaultTableModel) carritoModificarView
                                .getTblProductos()
                                .getModel();
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    int codigoProducto =
                            Integer.parseInt(
                                    modelo.getValueAt(i, 0).toString()
                            );
                    int cantidad =
                            Integer.parseInt(
                                    modelo.getValueAt(i, 3).toString()
                            );
                    Producto producto =
                            productoDAO.buscarPorCodigo(codigoProducto);
                    if (producto != null && cantidad > 0) {
                        carrito.agregarProducto(producto, cantidad);
                    }
                }
                carritoDAO.actualizar(carrito);
                double subtotal = carrito.calcularSubtotal();
                double iva = carrito.calcularIVA();
                double total = carrito.calcularTotal();
                carritoModificarView
                        .getTxtSubtotal()
                        .setText(String.format("%.2f", subtotal));
                carritoModificarView
                        .getTxtIVA()
                        .setText(String.format("%.2f", iva));
                carritoModificarView
                        .getTxtTotal()
                        .setText(String.format("%.2f", total));
                carritoModificarView.mostrarMensaje(
                        "Modificado correctamente\n" +
                                "Subtotal: $" + subtotal + "\n" +
                                "IVA: $" + iva + "\n" +
                                "Total: $" + total
                );
                if (listarCarritoView.isVisible()) {
                    listarCarritoView.cargarDatos(
                            carritoDAO.listarTodos()
                    );
                }
            } else {
                carritoModificarView.mostrarMensaje(
                        "Carrito no encontrado."
                );
            }
        } catch (NumberFormatException ex) {
            carritoModificarView.mostrarMensaje(
                    "Código inválido o cantidad incorrecta."
            );
        }
    }

    /**
     * Recalcula subtotal, IVA y total a partir del modelo de tabla y actualiza
     * los campos de la vista.
     *
     * @param modelo    modelo de la tabla con productos y cantidades
     * @param txtSub    campo de texto para mostrar el subtotal
     * @param txtIva    campo de texto para mostrar el IVA
     * @param txtTotal  campo de texto para mostrar el total
     */
    private void recalcularTotales(
            DefaultTableModel modelo,
            JTextField txtSub,
            JTextField txtIva,
            JTextField txtTotal
    ) {
        double subtotal = 0.0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            double precio =
                    Double.parseDouble(modelo.getValueAt(i, 2).toString());
            int cantidad =
                    Integer.parseInt(modelo.getValueAt(i, 3).toString());
            subtotal += precio * cantidad;
        }
        double iva = subtotal * 0.12;
        double total = subtotal + iva;
        txtSub.setText(String.format("%.2f", subtotal));
        txtIva.setText(String.format("%.2f", iva));
        txtTotal.setText(String.format("%.2f", total));
    }

    /**

     * Muestra la ventana de añadir carrito y establece el usuario autenticado.
     */
    public void mostrarVentanaCarrito() {
        carritoAnadirView.setUsuarioAutenticado(usuarioAutenticado);
        if (!carritoAnadirView.isVisible()) {
            carritoAnadirView.setVisible(false);
            carritoAnadirView.toFront();
        } else {
            carritoAnadirView.toFront();
        }
    }

    /**
     * Establece el usuario autenticado en el controlador.
     *
     * @param usuario usuario que ha iniciado sesión
     */
    public void setUsuarioAutenticado(Usuario usuario) {
        this.usuarioAutenticado = usuario;
    }

    /**
     * Actualiza el DAO de carrito en tiempo de ejecución.
     *
     * @param carritoDAO nueva implementación de {@link CarritoDAO}
     */
    public void setCarritoDAO(CarritoDAO carritoDAO) {
        this.carritoDAO = carritoDAO;
    }
}
