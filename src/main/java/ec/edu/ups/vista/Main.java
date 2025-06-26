package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                ProductoDAO productoDAO = new ProductoDAOMemoria();
                CarritoDAO carritoDAO = new CarritoDAOMemoria();
                UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();

                LoginView loginView = new LoginView();
                MenuPrincipalView principalView = new MenuPrincipalView();
                ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                CarritoAnadirView carritoAnadirView = new CarritoAnadirView();
                EliminarProductoView eliminarProductoView = new EliminarProductoView();
                ModificarProductoView modificarProductoView = new ModificarProductoView();
                EliminarCarritoView eliminarCarritoView = new EliminarCarritoView();
                ListarCarritoView listarCarritoView = new ListarCarritoView();
                UsuarioAnadirView usuarioAnadirView = new UsuarioAnadirView();
                UsuarioEliminarView usuarioEliminarView = new UsuarioEliminarView();
                UsuarioListarView usuarioListarView = new UsuarioListarView();
                UsuarioModificarView usuarioModificarView = new UsuarioModificarView();

                UsuarioController usuarioController = new UsuarioController(
                        usuarioDAO,
                        principalView,
                        usuarioAnadirView,
                        usuarioListarView,
                        usuarioModificarView,
                        usuarioEliminarView,
                        loginView
                );

                ProductoController productoController = new ProductoController(
                        productoDAO,
                        productoAnadirView,
                        productoListaView,
                        carritoAnadirView,
                        eliminarProductoView,
                        modificarProductoView
                );

                CarritoController carritoController = new CarritoController(
                        carritoDAO,
                        productoDAO,
                        carritoAnadirView,
                        eliminarCarritoView,
                        listarCarritoView
                );
                principalView.getMenuItemCerrarSesion().addActionListener(e -> {
                    principalView.dispose();
                    loginView.setVisible(true);
                });

                loginView.setVisible(true);
                loginView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();
                        if (usuarioAutenticado != null) {
                            principalView.mostrarMensaje("Bienvenido: " + usuarioAutenticado.getUsername());
                            if (usuarioAutenticado.getRol().equals(Rol.USUARIO)) {
                                principalView.deshabilitarMenusAdministrador();
                            }
                            principalView.setVisible(true);
                        }
                    }
                });

                loginView.getBtnRegistrarse().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        loginView.getLayeredPane().add(usuarioAnadirView);
                        usuarioAnadirView.setVisible(true);
                        usuarioAnadirView.toFront();
                    }
                });

                principalView.getMenuItemCrearProducto().addActionListener(e -> mostrarVentana(principalView, productoAnadirView));
                principalView.getMenuItemActualizarProducto().addActionListener(e -> mostrarVentana(principalView, modificarProductoView));
                principalView.getMenuItemBuscarProducto().addActionListener(e -> mostrarVentana(principalView, productoListaView));
                principalView.getMenuItemCrearProducto().addActionListener(e -> mostrarVentana(principalView, productoAnadirView));
                principalView.getMenuItemEliminarProducto().addActionListener(e -> mostrarVentana(principalView, eliminarProductoView));

                principalView.getMenuItemListarCarrito().addActionListener(e -> mostrarVentana(principalView, listarCarritoView));
                principalView.getMenuItemEliminarCarrito().addActionListener(e -> mostrarVentana(principalView, eliminarCarritoView));
                principalView.getMenuItemCrearCarrito().addActionListener(e -> mostrarVentana(principalView, carritoAnadirView));

                principalView.getMenuItemCrearUsuario().addActionListener(e -> mostrarVentana(principalView, usuarioAnadirView));
                principalView.getMenuItemListarUsuario().addActionListener(e -> mostrarVentana(principalView, usuarioListarView));
                principalView.getMenuItemModificarUsuario().addActionListener(e -> mostrarVentana(principalView, usuarioModificarView));
                principalView.getMenuItemEliminarUsuario().addActionListener(e -> mostrarVentana(principalView, usuarioEliminarView));


            }

            private void mostrarVentana(MenuPrincipalView principal, JInternalFrame vista) {
                if (!vista.isVisible()) {
                    principal.getjDesktopPane().add(vista);
                    vista.setVisible(true);
                    vista.toFront();
                } else {
                    vista.toFront();
                }
            }
        });


    }
}
