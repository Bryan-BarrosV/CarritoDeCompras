package ec.edu.ups.controlador;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.vista.CarritoAnadirView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarritoController {
    private final CarritoAnadirView carritoView;
    private final ProductoDAO productoDAO;
    private final Carrito carrito;
    private DefaultTableModel modeloTabla;

    public CarritoController(CarritoAnadirView carritoView, ProductoDAO productoDAO) {
        this.carritoView = carritoView;
        this.productoDAO = productoDAO;
        this.carrito = new Carrito();
        
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Subtotal");
        carritoView.getTblProductos().setModel(modeloTabla);
        
        configurarEventos();
    }

private void configurarEventos() {
    carritoView.getBtnBuscar().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            buscarProductoPorCodigo();
        }
    });

    // Tus otros eventos existentes...
    carritoView.getBtnAnadir().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            agregarAlCarrito();
        }
    });
    
    carritoView.getBtnLimpiar().addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            limpiarCarrito();
        }
    });
}

private void buscarProductoPorCodigo() {
    int codigo = Integer.parseInt(carritoView.getTxtCodigo().getText());
    Producto producto = productoDAO.buscarPorCodigo(codigo);
    
    if (producto == null) {
        carritoView.mostrarMensaje("No se encontró el producto");
        carritoView.getTxtNombre().setText("");
        carritoView.getTxtPrecio().setText("");
    } else {
        carritoView.getTxtNombre().setText(producto.getNombre());
        carritoView.getTxtPrecio().setText(String.valueOf(producto.getPrecio()));
    }
}

    private void agregarAlCarrito() {
        int codigo = Integer.parseInt(carritoView.getTxtCodigo().getText());
        Producto producto = productoDAO.buscarPorCodigo(codigo);
        
        if (producto != null) {
            int cantidad = Integer.parseInt((String) carritoView.getCbxCantidad().getSelectedItem());
            
            carrito.agregarProducto(producto, cantidad);
            actualizarTabla();
            calcularTotales();

            carritoView.getTxtCodigo().setText("");
            carritoView.getTxtNombre().setText("");
            carritoView.getTxtPrecio().setText("");
            carritoView.getCbxCantidad().setSelectedIndex(0);
        } else {
            carritoView.mostrarMensaje("Producto no encontrado");
        }
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        
        for (ItemCarrito item : carrito.obtenerItems()) {
            Object[] fila = {
                item.getProducto().getCodigo(),
                item.getProducto().getNombre(),
                item.getProducto().getPrecio(),
                item.getCantidad(),
                item.getSubtotal()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void calcularTotales() {
        double subtotal = carrito.calcularSubtotal();
        double iva = subtotal * 0.12;
        double total = subtotal + iva;

        carritoView.getTxtSubtotal().setText(String.format("%.2f", subtotal));
        carritoView.getTxtIva().setText(String.format("%.2f", iva));
        carritoView.getTxtTotal().setText(String.format("%.2f", total));
    }

    private void limpiarCarrito() {
        carrito.vaciarCarrito();
        actualizarTabla();
        calcularTotales();
        carritoView.getTxtCodigo().setText("");
        carritoView.getTxtNombre().setText("");
        carritoView.getTxtPrecio().setText("");
        carritoView.getCbxCantidad().setSelectedIndex(0);
    }
}