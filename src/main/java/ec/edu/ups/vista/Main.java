package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                ProductoDAO productoDAO = new ProductoDAOMemoria();

                MenuPrincipalView principalView = new MenuPrincipalView();
                ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                CarritoAnadirView carritoAnadirView = new CarritoAnadirView();
                EliminarProductoView eliminarProductoView = new EliminarProductoView();
                ModificarProductoView modificarProductoView = new ModificarProductoView();

                productoAnadirView.setVisible(false);
                productoListaView.setVisible(false);
                carritoAnadirView.setVisible(false);
                eliminarProductoView.setVisible(false);
                modificarProductoView.setVisible(false);

                ProductoController productoController = new ProductoController(productoDAO,
                        productoAnadirView, productoListaView, carritoAnadirView, 
                        eliminarProductoView, modificarProductoView);

                CarritoController carritoController = new CarritoController(carritoAnadirView, productoDAO);

                principalView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!productoAnadirView.isVisible()) {
                            principalView.getjDesktopPane().add(productoAnadirView);
                            productoAnadirView.setVisible(true);
                            productoAnadirView.toFront();
                        } else {
                            productoAnadirView.toFront();
                        }
                    }
                });

                principalView.getMenuItemActualizarProducto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!modificarProductoView.isVisible()) {
                            principalView.getjDesktopPane().add(modificarProductoView);
                            modificarProductoView.setVisible(true);
                            modificarProductoView.toFront();
                        } else {
                            modificarProductoView.toFront();
                        }
                    }
                });

                principalView.getMenuItemCrearCarrito().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!carritoAnadirView.isVisible()) {
                            principalView.getjDesktopPane().add(carritoAnadirView);
                            carritoAnadirView.setVisible(true);
                            carritoAnadirView.toFront();
                        } else {
                            carritoAnadirView.toFront();
                        }
                    }
                });

                principalView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!productoListaView.isVisible()) {
                            principalView.getjDesktopPane().add(productoListaView);
                            productoListaView.setVisible(true);
                            productoListaView.toFront();
                        } else {
                            productoListaView.toFront();
                        }
                    }
                });

                principalView.getMenuItemEliminarProducto().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!eliminarProductoView.isVisible()) {
                            principalView.getjDesktopPane().add(eliminarProductoView);
                            eliminarProductoView.setVisible(true);
                            eliminarProductoView.toFront();
                        } else {
                            eliminarProductoView.toFront();
                        }
                    }
                });



                principalView.setVisible(true);
            }
        });
    }
}