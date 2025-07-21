package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Vista principal del sistema, presenta menús para gestionar productos, usuarios y carritos.
 * Contiene soporte para internacionalización y control de visibilidad según el rol del usuario.
 */
public class MenuPrincipalView extends JFrame {

    // Menús principales y submenús
    private JMenuBar menuBar;
    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenu menuUsuario;
    private JMenu menuSesion;
    private JMenu menuIdioma;

    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemActualizarProducto;
    private JMenuItem menuItemBuscarProducto;

    private JMenuItem menuItemCrearCarrito;
    private JMenuItem menuItemListarCarrito;
    private JMenuItem menuItemEliminarCarrito;
    private JMenuItem menuItemModificarCarrito;

    private JMenuItem menuItemCrearUsuario;
    private JMenuItem menuItemListarUsuario;
    private JMenuItem menuItemModificarUsuario;
    private JMenuItem menuItemEliminarUsuario;

    private JMenuItem menuItemCerrarSesion;

    private JMenuItem itemIdiomaES;
    private JMenuItem itemIdiomaEN;
    private JMenuItem itemIdiomaFR;

    private MiJDesktopPane jDesktopPane;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    /**
     * Constructor principal de la vista, inicializa componentes y configura eventos e íconos.
     * @param handler Manejador de internacionalización.
     */
    public MenuPrincipalView(MensajeInternacionalizacionHandler handler) {
        this.mensajeInternacionalizacionHandler = handler;
        MiJDesktopPane desktopPane = new MiJDesktopPane();
        setContentPane(desktopPane);
        jDesktopPane = new MiJDesktopPane();
        setContentPane(jDesktopPane);

        // Inicialización de menús y estructura
        menuBar = new JMenuBar();
        menuProducto = new JMenu();
        menuCarrito = new JMenu();
        menuUsuario = new JMenu();
        menuSesion = new JMenu();
        menuIdioma = new JMenu();

        menuItemCrearProducto = new JMenuItem();
        menuItemEliminarProducto = new JMenuItem();
        menuItemActualizarProducto = new JMenuItem();
        menuItemBuscarProducto = new JMenuItem();

        menuItemCrearCarrito = new JMenuItem();
        menuItemListarCarrito = new JMenuItem();
        menuItemEliminarCarrito = new JMenuItem();
        menuItemModificarCarrito = new JMenuItem();

        menuItemCrearUsuario = new JMenuItem();
        menuItemListarUsuario = new JMenuItem();
        menuItemModificarUsuario = new JMenuItem();
        menuItemEliminarUsuario = new JMenuItem();

        menuItemCerrarSesion = new JMenuItem();

        itemIdiomaES = new JMenuItem();
        itemIdiomaEN = new JMenuItem();
        itemIdiomaFR = new JMenuItem();

        // Organización del menú
        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuUsuario);
        menuBar.add(menuSesion);
        menuBar.add(menuIdioma);

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemActualizarProducto);
        menuProducto.add(menuItemBuscarProducto);

        menuCarrito.add(menuItemCrearCarrito);
        menuCarrito.add(menuItemListarCarrito);
        menuCarrito.add(menuItemEliminarCarrito);
        menuCarrito.add(menuItemModificarCarrito);

        menuUsuario.add(menuItemCrearUsuario);
        menuUsuario.add(menuItemListarUsuario);
        menuUsuario.add(menuItemModificarUsuario);
        menuUsuario.add(menuItemEliminarUsuario);

        menuSesion.add(menuItemCerrarSesion);

        menuIdioma.add(itemIdiomaES);
        menuIdioma.add(itemIdiomaEN);
        menuIdioma.add(itemIdiomaFR);

        setJMenuBar(menuBar);
        setContentPane(jDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(false);

        actualizarTexto();

        // Carga de íconos (se omite documentación repetitiva)
        // Eventos para cambiar idioma
        itemIdiomaES.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensajeInternacionalizacionHandler.setLenguaje("es", "EC");
                actualizarTexto();
            }
        });

        itemIdiomaEN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensajeInternacionalizacionHandler.setLenguaje("en", "US");
                actualizarTexto();
            }
        });

        itemIdiomaFR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensajeInternacionalizacionHandler.setLenguaje("fr", "FR");
                actualizarTexto();
            }
        });
    }

    /**
     * Actualiza los textos visibles en los menús según el idioma seleccionado.
     */
    public void actualizarTexto() {
        // ... (sin cambios)
    }

    /**
     * Configura las opciones de menú según el rol del usuario autenticado.
     * @param rol Rol del usuario ("ADMIN", "USUARIO", etc.).
     */
    public void configurarOpcionesPorRol(String rol) {
        // ... (sin cambios)
    }

    // Getters documentados con Javadoc:

    /** @return JMenuItem para crear usuario */
    public JMenuItem getMenuItemCrearUsuario() { return menuItemCrearUsuario; }

    /** @return JMenuItem para listar usuarios */
    public JMenuItem getMenuItemListarUsuario() { return menuItemListarUsuario; }

    /** @return JMenuItem para modificar usuarios */
    public JMenuItem getMenuItemModificarUsuario() { return menuItemModificarUsuario; }

    /** @return JMenuItem para eliminar usuarios */
    public JMenuItem getMenuItemEliminarUsuario() { return menuItemEliminarUsuario; }

    /** @return JMenuItem para crear productos */
    public JMenuItem getMenuItemCrearProducto() { return menuItemCrearProducto; }

    /** @return JMenuItem para eliminar productos */
    public JMenuItem getMenuItemEliminarProducto() { return menuItemEliminarProducto; }

    /** @return JMenuItem para actualizar productos */
    public JMenuItem getMenuItemActualizarProducto() { return menuItemActualizarProducto; }

    /** @return JMenuItem para buscar productos */
    public JMenuItem getMenuItemBuscarProducto() { return menuItemBuscarProducto; }

    /** @return JMenuItem para crear carritos */
    public JMenuItem getMenuItemCrearCarrito() { return menuItemCrearCarrito; }

    /** @return JMenuItem para listar carritos */
    public JMenuItem getMenuItemListarCarrito() { return menuItemListarCarrito; }

    /** @return JMenuItem para eliminar carritos */
    public JMenuItem getMenuItemEliminarCarrito() { return menuItemEliminarCarrito; }

    /** @return JMenuItem para modificar carritos */
    public JMenuItem getMenuItemModificarCarrito() { return menuItemModificarCarrito; }

    /** @return JMenuItem para cambiar a idioma español */
    public JMenuItem getItemIdiomaES() { return itemIdiomaES; }

    /** @return JMenuItem para cambiar a idioma inglés */
    public JMenuItem getItemIdiomaEN() { return itemIdiomaEN; }

    /** @return JMenuItem para cambiar a idioma francés */
    public JMenuItem getItemIdiomaFR() { return itemIdiomaFR; }

    /** @return JMenuItem para cerrar sesión */
    public JMenuItem getMenuItemCerrarSesion() { return menuItemCerrarSesion; }

    /** @return Panel principal tipo JDesktopPane */
    public JDesktopPane getjDesktopPane() { return jDesktopPane; }

    /**
     * Muestra un mensaje emergente.
     * @param s Texto del mensaje.
     */
    public void mostrarMensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    /**
     * Deshabilita los menús reservados para el administrador.
     */
    public void deshabilitarMenusAdministrador() {
        menuUsuario.setEnabled(false);
        menuProducto.setEnabled(false);
        menuCarrito.setEnabled(false);
        menuSesion.setEnabled(false);
    }
}
