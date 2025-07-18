package ec.edu.ups;

import ec.edu.ups.controlador.CarritoController;
import ec.edu.ups.controlador.ContrasenaController;
import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.controlador.UsuarioController;
import ec.edu.ups.dao.*;
import ec.edu.ups.dao.impl.*;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.Carrito.CarritoAnadirView;
import ec.edu.ups.vista.Carrito.CarritoModificarView;
import ec.edu.ups.vista.Carrito.EliminarCarritoView;
import ec.edu.ups.vista.Carrito.ListarCarritoView;
import ec.edu.ups.vista.Contrasena.ContrasenaPreguntaView;
import ec.edu.ups.vista.Contrasena.ContrasenaView;
import ec.edu.ups.vista.LoginView;
import ec.edu.ups.vista.MenuPrincipalView;
import ec.edu.ups.vista.Producto.EliminarProductoView;
import ec.edu.ups.vista.Producto.ModificarProductoView;
import ec.edu.ups.vista.Producto.ProductoAnadirView;
import ec.edu.ups.vista.Producto.ProductoListaView;
import ec.edu.ups.vista.Usuario.UsuarioAnadirView;
import ec.edu.ups.vista.Usuario.UsuarioEliminarView;
import ec.edu.ups.vista.Usuario.UsuarioListarView;
import ec.edu.ups.vista.Usuario.UsuarioModificarView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
// --------Modififcado Pregunta si se hace commit
//-----------------------------------------------

public class Main {
    private static String modoAlmacenamiento = "MEMORIA";
    @SuppressWarnings("all")
    public static void main(String[] args)throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        EventQueue.invokeLater(() -> {
            final MensajeInternacionalizacionHandler handler = new MensajeInternacionalizacionHandler("es", "EC");
            DAOFactory daoFactory;
            try {
                daoFactory = new DAOFactory(modoAlmacenamiento);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            ProductoDAO productoDAO = daoFactory.getProductoDAO();
            CarritoDAO carritoDAO = null;
            UsuarioDAO usuarioDAO = null;
            ContrasenaDAO contrasenaDAO = null;
            PreguntaDAO preguntaDAO = null;
            RespuestaDAO respuestaDAO = null;

            try {
                carritoDAO = daoFactory.getCarritoDAO();
                usuarioDAO = daoFactory.getUsuarioDAO();
                contrasenaDAO = daoFactory.getContrasenaDAO();
                preguntaDAO = daoFactory.getPreguntaDAO(handler);
                respuestaDAO = daoFactory.getRespuestaDAO();
            } catch (Exception ex) {
                ex.printStackTrace();
                return;
            }


            MenuPrincipalView ventanaPrincipal = new MenuPrincipalView(handler);

            LoginView loginView = new LoginView(handler);
            MenuPrincipalView principalView = new MenuPrincipalView(handler);
            ProductoAnadirView productoAnadirView = new ProductoAnadirView(handler);
            ProductoListaView productoListaView = new ProductoListaView(handler);
            CarritoAnadirView carritoAnadirView = new CarritoAnadirView(handler);
            EliminarProductoView eliminarProductoView = new EliminarProductoView(handler);
            ModificarProductoView modificarProductoView = new ModificarProductoView(handler);
            EliminarCarritoView eliminarCarritoView = new EliminarCarritoView(handler);
            ListarCarritoView listarCarritoView = new ListarCarritoView(handler);
            UsuarioAnadirView usuarioAnadirView = new UsuarioAnadirView(handler);
            UsuarioEliminarView usuarioEliminarView = new UsuarioEliminarView(handler);
            UsuarioListarView usuarioListarView = new UsuarioListarView(handler);
            UsuarioModificarView usuarioModificarView = new UsuarioModificarView(handler);
            ContrasenaView contrasenaView = new ContrasenaView(handler);
            ContrasenaPreguntaView contrasenaPreguntaView = new ContrasenaPreguntaView(handler);
            CarritoModificarView carritoModificarView = new CarritoModificarView(handler);

            principalView.getItemIdiomaEN().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handler.setLenguaje("en", "US");
                    principalView.actualizarTexto();
                    loginView.actualizarTextosLogin();

                    productoAnadirView.actualizarTextos();
                    productoListaView.actualizarTextos();
                    modificarProductoView.actualizarTextos();
                    eliminarProductoView.actualizarTextos();

                    carritoAnadirView.actualizarTextos();
                    carritoModificarView.actualizarTextos();
                    eliminarCarritoView.actualizarTextos();
                    listarCarritoView.actualizarTextos();

                    usuarioAnadirView.actualizarTextos();
                    usuarioEliminarView.actualizarTextos();
                    usuarioListarView.actualizarTextos();
                    usuarioModificarView.actualizarTextos();

                    contrasenaPreguntaView.actualizarTextos();
                    contrasenaView.actualizarTextos();
                }
            });

            principalView.getItemIdiomaES().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handler.setLenguaje("es", "EC");
                    principalView.actualizarTexto();
                    loginView.actualizarTextosLogin();

                    productoAnadirView.actualizarTextos();
                    productoListaView.actualizarTextos();
                    modificarProductoView.actualizarTextos();
                    eliminarProductoView.actualizarTextos();

                    carritoAnadirView.actualizarTextos();
                    carritoModificarView.actualizarTextos();
                    eliminarCarritoView.actualizarTextos();
                    listarCarritoView.actualizarTextos();

                    usuarioAnadirView.actualizarTextos();
                    usuarioEliminarView.actualizarTextos();
                    usuarioListarView.actualizarTextos();
                    usuarioModificarView.actualizarTextos();

                    contrasenaPreguntaView.actualizarTextos();
                    contrasenaView.actualizarTextos();
                }
            });

            principalView.getItemIdiomaFR().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handler.setLenguaje("fr", "FR");
                    principalView.actualizarTexto();
                    loginView.actualizarTextosLogin();

                    productoAnadirView.actualizarTextos();
                    productoListaView.actualizarTextos();
                    modificarProductoView.actualizarTextos();
                    eliminarProductoView.actualizarTextos();

                    carritoAnadirView.actualizarTextos();
                    carritoModificarView.actualizarTextos();
                    eliminarCarritoView.actualizarTextos();
                    listarCarritoView.actualizarTextos();

                    usuarioAnadirView.actualizarTextos();
                    usuarioEliminarView.actualizarTextos();
                    usuarioListarView.actualizarTextos();
                    usuarioModificarView.actualizarTextos();

                    contrasenaPreguntaView.actualizarTextos();
                    contrasenaView.actualizarTextos();
                }
            });

            loginView.getItemIdiomaES().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handler.setLenguaje("es", "EC");
                    principalView.actualizarTexto();
                    loginView.actualizarTextosLogin();

                    productoAnadirView.actualizarTextos();
                    productoListaView.actualizarTextos();
                    modificarProductoView.actualizarTextos();
                    eliminarProductoView.actualizarTextos();

                    carritoAnadirView.actualizarTextos();
                    carritoModificarView.actualizarTextos();
                    eliminarCarritoView.actualizarTextos();
                    listarCarritoView.actualizarTextos();

                    usuarioAnadirView.actualizarTextos();
                    usuarioEliminarView.actualizarTextos();
                    usuarioListarView.actualizarTextos();
                    usuarioModificarView.actualizarTextos();

                    contrasenaPreguntaView.actualizarTextos();
                    contrasenaView.actualizarTextos();
                }
            });

            loginView.getItemIdiomaEN().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handler.setLenguaje("en", "US");
                    principalView.actualizarTexto();
                    loginView.actualizarTextosLogin();

                    productoAnadirView.actualizarTextos();
                    productoListaView.actualizarTextos();
                    modificarProductoView.actualizarTextos();
                    eliminarProductoView.actualizarTextos();

                    carritoAnadirView.actualizarTextos();
                    carritoModificarView.actualizarTextos();
                    eliminarCarritoView.actualizarTextos();
                    listarCarritoView.actualizarTextos();

                    usuarioAnadirView.actualizarTextos();
                    usuarioEliminarView.actualizarTextos();
                    usuarioListarView.actualizarTextos();
                    usuarioModificarView.actualizarTextos();

                    contrasenaPreguntaView.actualizarTextos();
                    contrasenaView.actualizarTextos();
                }
            });

            loginView.getItemIdiomaFR().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handler.setLenguaje("fr", "FR");
                    principalView.actualizarTexto();
                    loginView.actualizarTextosLogin();

                    productoAnadirView.actualizarTextos();
                    productoListaView.actualizarTextos();
                    modificarProductoView.actualizarTextos();
                    eliminarProductoView.actualizarTextos();

                    carritoAnadirView.actualizarTextos();
                    carritoModificarView.actualizarTextos();
                    eliminarCarritoView.actualizarTextos();
                    listarCarritoView.actualizarTextos();

                    usuarioAnadirView.actualizarTextos();
                    usuarioEliminarView.actualizarTextos();
                    usuarioListarView.actualizarTextos();
                    usuarioModificarView.actualizarTextos();

                    contrasenaPreguntaView.actualizarTextos();
                    contrasenaView.actualizarTextos();
                }
            });


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
                    contrasenaPreguntaView,
                    preguntaDAO,
                    handler
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

            principalView.getMenuItemCerrarSesion().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    principalView.dispose();
                    loginView.setVisible(true);
                }
            });

            loginView.setVisible(true);

            loginView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    Usuario usuarioAutenticado = usuarioController.getUsuarioAutenticado();
                    if (usuarioAutenticado != null) {
                        carritoController.setUsuarioAutenticado(usuarioAutenticado);
                        principalView.actualizarTexto();
                        String bienvenida = handler.get("mensaje.bienvenida")  + ": " + usuarioAutenticado.getUsername();
                        principalView.mostrarMensaje(bienvenida);
                        principalView.configurarOpcionesPorRol(usuarioAutenticado.getRol().name());
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


            loginView.getBtnOlvidoContrasena().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loginView.getLayeredPane().add(contrasenaView);
                    contrasenaView.setVisible(true);
                    contrasenaView.toFront();
                }
            });


            principalView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, productoAnadirView);
                }
            });

            principalView.getMenuItemActualizarProducto().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, modificarProductoView);
                }
            });

            principalView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, productoListaView);
                }
            });

            principalView.getMenuItemEliminarProducto().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, eliminarProductoView);
                }
            });


            principalView.getMenuItemListarCarrito().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, listarCarritoView);
                }
            });

            principalView.getMenuItemEliminarCarrito().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, eliminarCarritoView);
                }
            });

            principalView.getMenuItemModificarCarrito().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, carritoModificarView);
                }
            });

            principalView.getMenuItemCrearCarrito().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    carritoController.mostrarVentanaCarrito();
                    mostrarVentana(principalView, carritoAnadirView);
                }
            });


            principalView.getMenuItemCrearUsuario().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, usuarioAnadirView);
                }
            });

            principalView.getMenuItemListarUsuario().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, usuarioListarView);
                }
            });

            principalView.getMenuItemModificarUsuario().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, usuarioModificarView);
                }
            });

            principalView.getMenuItemEliminarUsuario().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, usuarioEliminarView);
                }
            });

            principalView.getMenuItemModificarCarrito().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, carritoModificarView);
                }
            });

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
