package ec.edu.ups.vista;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ContrasenaController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.dao.ContrasenaDAO;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.dao.impl.CarritoDAOMemoria;
import ec.edu.ups.dao.impl.ContrasenaDAOMemoria;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;
import ec.edu.ups.dao.impl.UsuarioDAOMemoria;
import ec.edu.ups.modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ProductoDAO productoDAO = new ProductoDAOMemoria();
            CarritoDAO carritoDAO = new CarritoDAOMemoria();
            UsuarioDAO usuarioDAO = new UsuarioDAOMemoria();
            ContrasenaDAO contrasenaDAO = new ContrasenaDAOMemoria();

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
            ContrasenaView contrasenaView = new ContrasenaView();
            ContrasenaPreguntaView contrasenaPreguntaView = new ContrasenaPreguntaView();
            CarritoModificarView carritoModificarView = new CarritoModificarView();

            UsuarioController usuarioController = new UsuarioController(
                    usuarioDAO,
                    contrasenaDAO,
                    principalView,
                    usuarioAnadirView,
                    usuarioListarView,
                    usuarioModificarView,
                    usuarioEliminarView,
                    loginView,
                    contrasenaView,
                    contrasenaPreguntaView
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
                    listarCarritoView,
                    carritoModificarView
            );

            ContrasenaController contrasenaController = new ContrasenaController(
                    contrasenaDAO,
                    usuarioDAO,
                    contrasenaView
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
                        carritoController.setUsuarioAutenticado(usuarioAutenticado);
                        principalView.mostrarMensaje("Bienvenido: " + usuarioAutenticado.getUsername());
                        principalView.configurarOpcionesPorRol(usuarioAutenticado.getRol().name());
                        principalView.setVisible(true);
                    }
                }
            });

            loginView.getBtnRegistrarse().addActionListener(e -> {
                loginView.getLayeredPane().add(usuarioAnadirView);
                usuarioAnadirView.setVisible(true);
                usuarioAnadirView.toFront();
            });

            loginView.getBtnOlvidoContrasena().addActionListener(e -> {
                loginView.getLayeredPane().add(contrasenaView);
                contrasenaView.setVisible(true);
                contrasenaView.toFront();
            });

            principalView.getMenuItemCrearProducto().addActionListener(e -> mostrarVentana(principalView, productoAnadirView));
            principalView.getMenuItemActualizarProducto().addActionListener(e -> mostrarVentana(principalView, modificarProductoView));
            principalView.getMenuItemBuscarProducto().addActionListener(e -> mostrarVentana(principalView, productoListaView));
            principalView.getMenuItemEliminarProducto().addActionListener(e -> mostrarVentana(principalView, eliminarProductoView));

            principalView.getMenuItemListarCarrito().addActionListener(e -> mostrarVentana(principalView, listarCarritoView));
            principalView.getMenuItemEliminarCarrito().addActionListener(e -> mostrarVentana(principalView, eliminarCarritoView));
            principalView.getMenuItemModificarCarrito().addActionListener(e -> mostrarVentana(principalView, carritoModificarView));
            principalView.getMenuItemCrearCarrito().addActionListener(e -> {
                carritoController.mostrarVentanaCarrito();
                mostrarVentana(principalView, carritoAnadirView);
            });

            principalView.getMenuItemCrearUsuario().addActionListener(e -> mostrarVentana(principalView, usuarioAnadirView));
            principalView.getMenuItemListarUsuario().addActionListener(e -> mostrarVentana(principalView, usuarioListarView));
            principalView.getMenuItemModificarUsuario().addActionListener(e -> mostrarVentana(principalView, usuarioModificarView));
            principalView.getMenuItemEliminarUsuario().addActionListener(e -> mostrarVentana(principalView, usuarioEliminarView));
            principalView.getMenuItemModificarCarrito().addActionListener(e -> mostrarVentana(principalView, carritoModificarView));

        });
    }

    private static void mostrarVentana(MenuPrincipalView principal, JInternalFrame vista) {
        if (!vista.isVisible()) {
            principal.getjDesktopPane().add(vista);
            vista.setVisible(true);
            vista.toFront();
        } else {
            vista.toFront();
        }
    }
}
