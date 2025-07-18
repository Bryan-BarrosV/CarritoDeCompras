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
import java.util.List;
import java.util.Locale;

public class CarritoController {

    private final CarritoDAO carritoDAO;
    private final ProductoDAO productoDAO;
    private final CarritoAnadirView carritoAnadirView;
    private final EliminarCarritoView eliminarCarritoView;
    private final ListarCarritoView listarCarritoView;
    private final CarritoModificarView carritoModificarView;
    private Usuario usuarioAutenticado;

    public CarritoController(CarritoDAO carritoDAO, ProductoDAO productoDAO,
                             CarritoAnadirView carritoAnadirView,
                             EliminarCarritoView eliminarCarritoView,
                             ListarCarritoView listarCarritoView,
                             CarritoModificarView carritoModificarView) {
        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.eliminarCarritoView = eliminarCarritoView;
        this.listarCarritoView = listarCarritoView;
        this.carritoModificarView = carritoModificarView;

        configurarEventos();
    }

    private void configurarEventos() {

        carritoAnadirView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoAnadirView();
            }
        });

        carritoAnadirView.getBtnAnadir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anadirProductoATabla();
            }
        });

        carritoAnadirView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProductoDeTabla();
            }
        });

        carritoAnadirView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });


        eliminarCarritoView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCarrito();
            }
        });

        eliminarCarritoView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCarritosParaEliminar();
            }
        });

        listarCarritoView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarCarritos();
            }
        });

        carritoModificarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCarritoParaModificar();
            }
        });

        carritoModificarView.getBtnModificar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarCarrito();
            }
        });
    }


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

    private void anadirProductoATabla() {
        try {
            int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
            Producto producto = productoDAO.buscarPorCodigo(codigo);
            if (producto == null) {
                carritoAnadirView.mostrarMensaje("Producto no encontrado.");
                return;
            }

            int cantidad = Integer.parseInt(carritoAnadirView.getCbxCantidad().getSelectedItem().toString());
            double subtotal = producto.getPrecio() * cantidad;
            Locale locale = carritoAnadirView.getMensajeInternacionalizacion().getLocale();
            String subtotalFormateado = FormateadorUtils.formatearMoneda(subtotal, locale);

            DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
            modelo.addRow(new Object[]{
                    producto.getCodigo(), producto.getNombre(), producto.getPrecio(), cantidad, subtotalFormateado
            });

            recalcularTotales(modelo, carritoAnadirView.getTxtSubtotal(), carritoAnadirView.getTxtIva(), carritoAnadirView.getTxtTotal());
        } catch (NumberFormatException ex) {
            carritoAnadirView.mostrarMensaje("Código o cantidad inválidos.");
        }
    }

    private void eliminarProductoDeTabla() {
        int filaSeleccionada = carritoAnadirView.getTblProductos().getSelectedRow();
        if (filaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
            modelo.removeRow(filaSeleccionada);
            recalcularTotales(modelo, carritoAnadirView.getTxtSubtotal(), carritoAnadirView.getTxtIva(), carritoAnadirView.getTxtTotal());
        } else {
            carritoAnadirView.mostrarMensaje("Seleccione un producto de la tabla para eliminar.");
        }
    }

    private void eliminarCarrito() {
        int fila = eliminarCarritoView.getTblProductos().getSelectedRow();
        if (fila != -1) {
            int opcion = JOptionPane.showConfirmDialog(eliminarCarritoView, "¿Está seguro de eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                int codigo = (int) eliminarCarritoView.getTblProductos().getValueAt(fila, 0);
                carritoDAO.eliminar(codigo);
                eliminarCarritoView.mostrarMensaje("Carrito eliminado.");
                eliminarCarritoView.cargarDatosTabla(carritoDAO.listarTodos());
            }
        } else {
            eliminarCarritoView.mostrarMensaje("Seleccione un carrito.");
        }
    }

    private void buscarCarritosParaEliminar() {
        eliminarCarritoView.cargarDatosTabla(carritoDAO.listarTodos());
    }

    private void listarCarritos() {
        listarCarritoView.cargarDatos(carritoDAO.listarTodos());
    }

    private void buscarCarritoParaModificar() {
        String textoCodigo = carritoModificarView.getTxtCodigo().getText().trim();
        if (textoCodigo.isEmpty()) {
            carritoModificarView.mostrarMensaje("Ingrese un código.");
            return;
        }

        try {
            int codigo = Integer.parseInt(textoCodigo);
            Carrito carrito = carritoDAO.buscarPorCodigo(codigo);
            if (carrito != null) {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String fechaFormateada = formato.format(carrito.getFechaCreacion().getTime());
                carritoModificarView.getTxtFecha().setText(fechaFormateada);
                carritoModificarView.getModelo().setRowCount(0);
                for (ItemCarrito item : carrito.obtenerItems()) {
                    Producto p = item.getProducto();
                    carritoModificarView.getModelo().addRow(new Object[]{
                            p.getCodigo(), p.getNombre(), p.getPrecio(), item.getCantidad()
                    });
                }
                recalcularTotales(carritoModificarView.getModelo(),
                        carritoModificarView.getTxtSubtotal(),
                        carritoModificarView.getTxtIVA(),
                        carritoModificarView.getTxtTotal());
            } else {
                carritoModificarView.mostrarMensaje("Carrito no encontrado.");
            }
        } catch (NumberFormatException ex) {
            carritoModificarView.mostrarMensaje("Código inválido.");
        }
    }

    private void modificarCarrito() {
        try {
            int codigoCarrito = Integer.parseInt(carritoModificarView.getTxtCodigo().getText().trim());
            Carrito carrito = carritoDAO.buscarPorCodigo(codigoCarrito);

            if (carrito != null) {
                carrito.limpiar();
                DefaultTableModel modelo = (DefaultTableModel) carritoModificarView.getTblProductos().getModel();

                for (int i = 0; i < modelo.getRowCount(); i++) {
                    int codigoProducto = Integer.parseInt(modelo.getValueAt(i, 0).toString());
                    int cantidad = Integer.parseInt(modelo.getValueAt(i, 3).toString());
                    Producto producto = productoDAO.buscarPorCodigo(codigoProducto);
                    if (producto != null && cantidad > 0) {
                        carrito.agregarProducto(producto, cantidad);
                    }
                }

                carritoDAO.actualizar(carrito);

                double subtotal = carrito.calcularSubtotal();
                double iva = carrito.calcularIVA();
                double total = carrito.calcularTotal();

                carritoModificarView.getTxtSubtotal().setText(String.format("%.2f", subtotal));
                carritoModificarView.getTxtIVA().setText(String.format("%.2f", iva));
                carritoModificarView.getTxtTotal().setText(String.format("%.2f", total));

                carritoModificarView.mostrarMensaje("Modificado correctamente\nSubtotal: $" + subtotal +
                        "\nIVA: $" + iva + "\nTotal: $" + total);

                if (listarCarritoView.isVisible()) {
                    listarCarritoView.cargarDatos(carritoDAO.listarTodos());
                }
            } else {
                carritoModificarView.mostrarMensaje("Carrito no encontrado.");
            }
        } catch (NumberFormatException ex) {
            carritoModificarView.mostrarMensaje("Código inválido o cantidad incorrecta.");
        }
    }

    private void recalcularTotales(DefaultTableModel modelo, JTextField txtSubtotal, JTextField txtIva, JTextField txtTotal) {
        double subtotal = 0.0;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            double precio = Double.parseDouble(modelo.getValueAt(i, 2).toString());
            int cantidad = Integer.parseInt(modelo.getValueAt(i, 3).toString());
            subtotal += precio * cantidad;
        }

        double iva = subtotal * 0.12;
        double total = subtotal + iva;

        txtSubtotal.setText(String.format("%.2f", subtotal));
        txtIva.setText(String.format("%.2f", iva));
        txtTotal.setText(String.format("%.2f", total));
    }

    public void mostrarVentanaCarrito() {
        carritoAnadirView.setUsuarioAutenticado(usuarioAutenticado);
        if (!carritoAnadirView.isVisible()) {
            carritoAnadirView.setVisible(false);
            carritoAnadirView.toFront();
        } else {
            carritoAnadirView.toFront();
        }
    }


    public void setUsuarioAutenticado(Usuario usuario) {
        this.usuarioAutenticado = usuario;
    }

}
