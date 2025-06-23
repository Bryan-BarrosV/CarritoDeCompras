package ec.edu.ups.controlador;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.CarritoAnadirView;
import ec.edu.ups.vista.EliminarCarritoView;
import ec.edu.ups.vista.ListarCarritoView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CarritoController {

    private final CarritoDAO carritoDAO;
    private final ProductoDAO productoDAO;
    private final CarritoAnadirView carritoAnadirView;
    private final EliminarCarritoView eliminarCarritoView;
    private final ListarCarritoView listarCarritoView;

    public CarritoController(CarritoDAO carritoDAO, ProductoDAO productoDAO,
                             CarritoAnadirView carritoAnadirView,
                             EliminarCarritoView eliminarCarritoView,
                             ListarCarritoView listarCarritoView) {

        this.carritoDAO = carritoDAO;
        this.productoDAO = productoDAO;
        this.carritoAnadirView = carritoAnadirView;
        this.eliminarCarritoView = eliminarCarritoView;
        this.listarCarritoView = listarCarritoView;

        this.carritoAnadirView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
                Producto producto = productoDAO.buscarPorCodigo(codigo);
                if (producto != null) {
                    carritoAnadirView.getTxtNombre().setText(producto.getNombre());
                    carritoAnadirView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
                } else {
                    carritoAnadirView.mostrarMensaje("Producto no encontrado.");
                }
            }
        });

        this.carritoAnadirView.getBtnAnadir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(carritoAnadirView.getTxtCodigo().getText());
                Producto producto = productoDAO.buscarPorCodigo(codigo);
                int cantidad = Integer.parseInt(carritoAnadirView.getCbxCantidad().getSelectedItem().toString());

                DefaultTableModel modelo = (DefaultTableModel) carritoAnadirView.getTblProductos().getModel();
                double subtotal = producto.getPrecio() * cantidad;
                modelo.addRow(new Object[]{producto.getCodigo(), producto.getNombre(), producto.getPrecio(), cantidad, subtotal});

                double sumaSubtotal = 0;
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    sumaSubtotal += (double) modelo.getValueAt(i, 4);
                }

                double iva = sumaSubtotal * 0.12;
                double total = sumaSubtotal + iva;

                carritoAnadirView.getTxtSubtotal().setText(String.valueOf(sumaSubtotal));
                carritoAnadirView.getTxtIva().setText(String.valueOf(iva));
                carritoAnadirView.getTxtTotal().setText(String.valueOf(total));
            }
        });

        this.carritoAnadirView.getBtnGuardar().addActionListener(new ActionListener() {
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

        this.eliminarCarritoView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = Integer.parseInt(eliminarCarritoView.getTextField1().getText());
                carritoDAO.eliminar(codigo);
                eliminarCarritoView.mostrarMensaje("Carrito eliminado.");
                eliminarCarritoView.limpiarCampos();
            }
        });

        this.eliminarCarritoView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Carrito> carritos = carritoDAO.listarTodos();
                eliminarCarritoView.cargarDatosTabla(carritos);
            }
        });

        this.listarCarritoView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Carrito> carritos = carritoDAO.listarTodos();
                listarCarritoView.cargarDatos(carritos);
            }
        });
        eliminarCarritoView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = eliminarCarritoView.getTblProductos().getSelectedRow();
                if (filaSeleccionada != -1) {
                    int codigo = (int) eliminarCarritoView.getTblProductos().getValueAt(filaSeleccionada, 0);
                    carritoDAO.eliminar(codigo);
                    eliminarCarritoView.mostrarMensaje("Carrito eliminado correctamente.");
                    eliminarCarritoView.cargarDatosTabla(carritoDAO.listarTodos());
                } else {
                    eliminarCarritoView.mostrarMensaje("Debe seleccionar un carrito para eliminar.");
                }
            }
        });
        eliminarCarritoView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = eliminarCarritoView.getTblProductos().getSelectedRow();
                if (filaSeleccionada != -1) {
                    int opcion = JOptionPane.showConfirmDialog(
                            eliminarCarritoView,
                            "¿Está seguro de que desea eliminar este carrito?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (opcion == JOptionPane.YES_OPTION) {
                        int codigo = (int) eliminarCarritoView.getTblProductos().getValueAt(filaSeleccionada, 0);
                        carritoDAO.eliminar(codigo);
                        eliminarCarritoView.mostrarMensaje("Carrito eliminado correctamente.");
                        eliminarCarritoView.cargarDatosTabla(carritoDAO.listarTodos());
                    }
                } else {
                    eliminarCarritoView.mostrarMensaje("Debe seleccionar un carrito para eliminar.");
                }
            }
        });

    }
}
