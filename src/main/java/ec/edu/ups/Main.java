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
/**
 * Clase principal del sistema que inicia y configura todos los componentes del programa.
 * Gestiona la creación de DAOs, controladores y vistas, así como la configuración del idioma,
 * modo de almacenamiento y eventos de la interfaz gráfica.
 *
 * Se usa Java Swing para la interfaz gráfica y se sigue el patrón MVC.
 *
 * @author Bryan
 */
public class Main {
    /** Modo actual de almacenamiento de datos (MEMORIA, TEXTO o BINARIO). */
    public static String modoAlmacenamiento = "MEMORIA";

    /** Fábrica para obtener instancias de DAOs según el modo de almacenamiento. */
    public static DAOFactory daoFactory;

    /** DAO para gestionar usuarios. */
    public static UsuarioDAO usuarioDAO;

    /** DAO para gestionar productos. */
    public static ProductoDAO productoDAO;

    /** DAO para gestionar carritos de compra. */
    public static CarritoDAO carritoDAO;

    /** DAO para gestionar contraseñas. */
    public static ContrasenaDAO contrasenaDAO;

    /** DAO para gestionar preguntas de seguridad. */
    public static PreguntaDAO preguntaDAO;

    /** DAO para gestionar respuestas de seguridad. */
    public static RespuestaDAO respuestaDAO;

    /**
     * Método principal que lanza la aplicación.
     *
     * @param args Argumentos de la línea de comandos.
     * @throws UnsupportedLookAndFeelException Si no se soporta el estilo Nimbus.
     * @throws ClassNotFoundException Si no se encuentra la clase de estilo.
     * @throws InstantiationException Si ocurre un error al instanciar el estilo.
     * @throws IllegalAccessException Si no se tiene acceso al estilo.
     */
    @SuppressWarnings("all")
    /**
     * Método principal que inicia la aplicación Swing.
     * <p>
     * Configura el estilo visual Nimbus, crea el manejador de internacionalización,
     * instancia la fábrica DAO según el modo de almacenamiento seleccionado
     * (MEMORIA, TEXTO o BINARIO), e inicializa los DAOs necesarios para el sistema.
     * <p>
     * Todo el proceso se ejecuta en el hilo de eventos de la interfaz gráfica para garantizar
     * una ejecución segura en entornos gráficos Swing.
     *
     * @param args Argumentos de la línea de comandos.
     * @throws UnsupportedLookAndFeelException Si el LookAndFeel Nimbus no es compatible.
     * @throws ClassNotFoundException Si no se encuentra la clase del LookAndFeel.
     * @throws InstantiationException Si ocurre un error al instanciar el LookAndFeel.
     * @throws IllegalAccessException Si no se tiene permiso para acceder al LookAndFeel.
     */
    public static void main(String[] args)throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        EventQueue.invokeLater(() -> {

            /**
             * Manejador para la carga dinámica de textos internacionalizados según idioma y país.
             */
            final MensajeInternacionalizacionHandler handler = new MensajeInternacionalizacionHandler("es", "EC");

            /**
             * Fábrica global que proporciona instancias DAO según el modo de almacenamiento actual.
             */
            daoFactory = new DAOFactory(modoAlmacenamiento);

            /**
             * Fábrica DAO local usada dentro del hilo actual para obtener los DAOs específicos.
             */
            DAOFactory daoFactory;

            try {
                daoFactory = new DAOFactory(modoAlmacenamiento);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            /**
             * DAO que proporciona acceso a los datos de productos.
             */
            ProductoDAO productoDAO = daoFactory.getProductoDAO();

            /**
             * DAOs restantes inicializados de forma nula para ser asignados en el bloque try.
             */
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

            /** Vista secundaria no usada directamente, posible remanente para futura integración. */
            MenuPrincipalView ventanaPrincipal = new MenuPrincipalView(handler);

            /** Vista de inicio de sesión del sistema. */
            LoginView loginView = new LoginView(handler);

            /** Vista principal del menú general, contiene las opciones del sistema. */
            MenuPrincipalView principalView = new MenuPrincipalView(handler);

            /** Vista para añadir nuevos productos al sistema. */
            ProductoAnadirView productoAnadirView = new ProductoAnadirView(handler);

            /** Vista para listar los productos disponibles en el sistema. */
            ProductoListaView productoListaView = new ProductoListaView(handler);

            /** Vista para añadir productos a un carrito de compras. */
            CarritoAnadirView carritoAnadirView = new CarritoAnadirView(handler);

            /** Vista para eliminar productos existentes del sistema. */
            EliminarProductoView eliminarProductoView = new EliminarProductoView(handler);

            /** Vista para modificar los datos de un producto registrado. */
            ModificarProductoView modificarProductoView = new ModificarProductoView(handler);

            /** Vista para eliminar carritos del sistema. */
            EliminarCarritoView eliminarCarritoView = new EliminarCarritoView(handler);

            /** Vista para listar todos los carritos registrados. */
            ListarCarritoView listarCarritoView = new ListarCarritoView(handler);

            /** Vista para registrar nuevos usuarios en el sistema. */
            UsuarioAnadirView usuarioAnadirView = new UsuarioAnadirView(handler);

            /** Vista para eliminar usuarios existentes. */
            UsuarioEliminarView usuarioEliminarView = new UsuarioEliminarView(handler);

            /** Vista para listar todos los usuarios registrados. */
            UsuarioListarView usuarioListarView = new UsuarioListarView(handler);

            /** Vista para modificar la información de un usuario. */
            UsuarioModificarView usuarioModificarView = new UsuarioModificarView(handler);

            /** Vista principal para recuperar contraseña mediante verificación. */
            ContrasenaView contrasenaView = new ContrasenaView(handler);

            /** Vista auxiliar para responder la pregunta de seguridad durante recuperación de contraseña. */
            ContrasenaPreguntaView contrasenaPreguntaView = new ContrasenaPreguntaView(handler);

            /** Vista para modificar los elementos de un carrito de compras. */
            CarritoModificarView carritoModificarView = new CarritoModificarView(handler);


            /**
             * Listener para cambiar el idioma del sistema a inglés desde la vista principal.
             * Actualiza todos los textos visibles en las vistas usando el recurso en_US.
             */
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

            /**
             * Listener para cambiar el idioma del sistema a español desde la vista principal.
             * Se cargan los textos desde el recurso es_EC.
             */
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

            /**
             * Listener para cambiar el idioma del sistema a francés desde la vista principal.
             * Se cargan los textos desde el recurso fr_FR.
             */
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

            /**
             * Listener para cambiar el idioma a español desde la vista de login.
             */
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

            /**
             * Listener para cambiar el idioma a inglés desde la vista de login.
             */
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


            /**
             * Listener que cambia el idioma del sistema a francés desde la vista de login.
             * Se actualizan todos los textos visibles en las diferentes vistas con el recurso 'fr_FR'.
             */
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

            /**
             * Creación del controlador de usuarios, el cual gestiona operaciones como
             * registro, modificación, eliminación, autenticación y recuperación de credenciales.
             */
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


            /**
             * Creación del controlador de productos.
             * Gestiona la lógica relacionada con el registro, listado, modificación y eliminación de productos.
             */
            ProductoController productoController = new ProductoController(
                    productoDAO,
                    productoAnadirView,
                    productoListaView,
                    carritoAnadirView,
                    eliminarProductoView,
                    modificarProductoView
            );

            /**
             * Creación del controlador de carritos.
             * Encargado de administrar la lógica para añadir, listar, modificar y eliminar carritos.
             */
            CarritoController carritoController = new CarritoController(
                    carritoDAO,
                    productoDAO,
                    carritoAnadirView,
                    eliminarCarritoView,
                    listarCarritoView,
                    carritoModificarView
            );

            /**
             * Creación del controlador de contraseñas.
             * Maneja la lógica de recuperación de contraseña por parte del usuario.
             */
            ContrasenaController contrasenaController = new ContrasenaController(
                    contrasenaDAO,
                    usuarioDAO,
                    contrasenaView
            );

            /**
             * Listener del menú para cerrar sesión.
             * Oculta la vista principal y vuelve a mostrar la pantalla de login.
             */
            principalView.getMenuItemCerrarSesion().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    principalView.dispose();
                    loginView.setVisible(true);
                }
            });

            /**
             * Se muestra la ventana de login al iniciar el sistema.
             */
            loginView.setVisible(true);


            /**
             * Listener que se activa al cerrar la ventana de login.
             * Si el usuario fue autenticado correctamente, se configura la vista principal
             * con sus permisos según rol, se muestra un mensaje de bienvenida y se muestra la ventana principal.
             */
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

            /**
             * Listener para cambiar el modo de almacenamiento a MEMORIA desde el menú de configuración.
             * Se actualizan los DAOs activos con implementaciones en memoria.
             */
            loginView.getItemMemoria().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    daoFactory.setModo("MEMORIA");
                    usuarioController.setUsuarioDAO(daoFactory.getUsuarioDAO());
                    productoController.setProductoDAO(daoFactory.getProductoDAO());
                    carritoController.setCarritoDAO(daoFactory.getCarritoDAO());
                }
            });

            /**
             * Listener para cambiar el modo de almacenamiento a TEXTO desde el menú de configuración.
             * Se actualizan los DAOs activos con implementaciones basadas en archivos de texto plano.
             */
            loginView.getItemTexto().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    daoFactory.setModo("TEXTO");
                    usuarioController.setUsuarioDAO(daoFactory.getUsuarioDAO());
                    productoController.setProductoDAO(daoFactory.getProductoDAO());
                    carritoController.setCarritoDAO(daoFactory.getCarritoDAO());
                }
            });

            /**
             * Listener para cambiar el modo de almacenamiento a BINARIO desde el menú de configuración.
             * Se actualizan los DAOs activos con implementaciones que manejan archivos binarios.
             */
            loginView.getItemBinario().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    daoFactory.setModo("BINARIO");
                    usuarioController.setUsuarioDAO(daoFactory.getUsuarioDAO());
                    productoController.setProductoDAO(daoFactory.getProductoDAO());
                    carritoController.setCarritoDAO(daoFactory.getCarritoDAO());
                }
            });

            /**
             * Listener del botón "Registrarse" en la vista de login.
             * Muestra la vista para añadir un nuevo usuario dentro del panel principal del login.
             */
            loginView.getBtnRegistrarse().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loginView.getLayeredPane().add(usuarioAnadirView);
                    usuarioAnadirView.setVisible(true);
                    usuarioAnadirView.toFront();
                }
            });

            /**
             * Listener del botón "¿Olvidó su contraseña?" en la vista de login.
             * Muestra la vista de recuperación de contraseña dentro del panel principal del login.
             */
            loginView.getBtnOlvidoContrasena().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loginView.getLayeredPane().add(contrasenaView);
                    contrasenaView.setVisible(true);
                    contrasenaView.toFront();
                }
            });

            /**
             * Menú principal: opción para crear un nuevo producto.
             * Abre la vista correspondiente en el escritorio principal.
             */
            principalView.getMenuItemCrearProducto().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, productoAnadirView);
                }
            });

            /**
             * Menú principal: opción para modificar un producto existente.
             * Abre la vista correspondiente en el escritorio principal.
             */
            principalView.getMenuItemActualizarProducto().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, modificarProductoView);
                }
            });

            /**
             * Menú principal: opción para buscar o listar productos registrados.
             * Abre la vista correspondiente en el escritorio principal.
             */
            principalView.getMenuItemBuscarProducto().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, productoListaView);
                }
            });

            /**
             * Menú principal: opción para eliminar un producto existente.
             * Abre la vista correspondiente en el escritorio principal.
             */
            principalView.getMenuItemEliminarProducto().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, eliminarProductoView);
                }
            });



            /**
             * Menú principal: opción para listar todos los carritos registrados.
             * Abre la vista correspondiente en el escritorio principal.
             */
            principalView.getMenuItemListarCarrito().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, listarCarritoView);
                }
            });

            /**
             * Menú principal: opción para eliminar un carrito existente.
             * Abre la vista correspondiente en el escritorio principal.
             */
            principalView.getMenuItemEliminarCarrito().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, eliminarCarritoView);
                }
            });

            /**
             * Menú principal: opción para modificar un carrito de compras.
             * Abre la vista correspondiente en el escritorio principal.
             */
            principalView.getMenuItemModificarCarrito().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, carritoModificarView);
                }
            });

            /**
             * Menú principal: opción para crear un nuevo carrito.
             * Muestra información inicial del carrito y abre la vista de creación.
             */
            principalView.getMenuItemCrearCarrito().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    carritoController.mostrarVentanaCarrito();
                    mostrarVentana(principalView, carritoAnadirView);
                }
            });

            /**
             * Menú principal: opción para registrar un nuevo usuario.
             * Abre la vista correspondiente en el escritorio principal.
             */
            principalView.getMenuItemCrearUsuario().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, usuarioAnadirView);
                }
            });

            /**
             * Menú principal: opción para listar los usuarios registrados.
             * Abre la vista correspondiente en el escritorio principal.
             */
            principalView.getMenuItemListarUsuario().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, usuarioListarView);
                }
            });

            /**
             * Menú principal: opción para modificar un usuario existente.
             * Abre la vista correspondiente en el escritorio principal.
             */
            principalView.getMenuItemModificarUsuario().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, usuarioModificarView);
                }
            });


            /**
             * Menú principal: opción para eliminar un usuario registrado.
             * Abre la vista correspondiente en el escritorio principal.
             */
            principalView.getMenuItemEliminarUsuario().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, usuarioEliminarView);
                }
            });

            /**
             * Menú principal: opción para modificar un carrito de compras.
             * Este listener aparece duplicado por requerimientos de menú.
             * Abre la vista correspondiente en el escritorio principal.
             */
            principalView.getMenuItemModificarCarrito().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentana(principalView, carritoModificarView);
                }
            });

        });
    }

    /**
     * Método auxiliar que muestra una vista interna dentro del JDesktopPane de la ventana principal.
     * Si la vista ya está visible, simplemente la trae al frente.
     *
     * @param principal Ventana principal donde se encuentra el JDesktopPane.
     * @param vista     Vista interna (JInternalFrame) que se desea mostrar.
     */
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

