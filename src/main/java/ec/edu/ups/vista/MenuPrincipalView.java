package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MenuPrincipalView extends JFrame {

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

    public MenuPrincipalView(MensajeInternacionalizacionHandler handler) {
        this.mensajeInternacionalizacionHandler = handler;
        MiJDesktopPane desktopPane = new MiJDesktopPane();
        setContentPane(desktopPane);
        jDesktopPane = new MiJDesktopPane();
        setContentPane(jDesktopPane);

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

        URL productoURL = MenuPrincipalView.class.getClassLoader().getResource("imagenes/productomenu.png");
        if (productoURL != null) {
            menuProducto.setIcon(new ImageIcon(new ImageIcon(productoURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró productomenu.png");
        }

        URL carritoURL = MenuPrincipalView.class.getClassLoader().getResource("imagenes/carritomenu.png");
        if (carritoURL != null) {
            menuCarrito.setIcon(new ImageIcon(new ImageIcon(carritoURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró carritomenu.png");
        }

        URL usuarioURL = MenuPrincipalView.class.getClassLoader().getResource("imagenes/usuariomenu.png");
        if (usuarioURL != null) {
            menuUsuario.setIcon(new ImageIcon(new ImageIcon(usuarioURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró usuario.png");
        }

        URL cerrarSesionURL = MenuPrincipalView.class.getClassLoader().getResource("imagenes/cerrarsesionmenu.png");
        if (cerrarSesionURL != null) {
            menuSesion.setIcon(new ImageIcon(new ImageIcon(cerrarSesionURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró cerrarsesionmenu.png");
        }

        URL idiomaURL = MenuPrincipalView.class.getClassLoader().getResource("imagenes/idiomas.png");
        if (idiomaURL != null) {
            menuIdioma.setIcon(new ImageIcon(new ImageIcon(idiomaURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró idiomas.png");
        }

        URL espanolURL = MenuPrincipalView.class.getClassLoader().getResource("imagenes/ecuador.png");
        if (espanolURL != null) {
            itemIdiomaES.setIcon(new ImageIcon(new ImageIcon(espanolURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró ecuador.png");
        }

        URL inglesURL = MenuPrincipalView.class.getClassLoader().getResource("imagenes/england.png");
        if (inglesURL != null) {
            itemIdiomaEN.setIcon(new ImageIcon(new ImageIcon(inglesURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró england.png");
        }

        URL francesURL = MenuPrincipalView.class.getClassLoader().getResource("imagenes/francia.png");
        if (francesURL != null) {
            itemIdiomaFR.setIcon(new ImageIcon(new ImageIcon(francesURL).getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
        } else {
            System.err.println("No se encontró francia.png");
        }



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

    public void actualizarTexto() {
        setTitle(mensajeInternacionalizacionHandler.get("app.titulo"));

        menuProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto"));
        menuItemCrearProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.crear"));
        menuItemEliminarProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.eliminar"));
        menuItemActualizarProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.actualizar"));
        menuItemBuscarProducto.setText(mensajeInternacionalizacionHandler.get("menu.producto.buscar"));

        menuCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito"));
        menuItemCrearCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.crear"));
        menuItemListarCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.listar"));
        menuItemEliminarCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.eliminar"));
        menuItemModificarCarrito.setText(mensajeInternacionalizacionHandler.get("menu.carrito.modificar"));

        menuUsuario.setText("Usuario");
        menuItemCrearUsuario.setText(mensajeInternacionalizacionHandler.get("menu.usuario.crear"));
        menuItemListarUsuario.setText(mensajeInternacionalizacionHandler.get("menu.usuario.listar"));
        menuItemModificarUsuario.setText(mensajeInternacionalizacionHandler.get("menu.usuario.modificar"));
        menuItemEliminarUsuario.setText(mensajeInternacionalizacionHandler.get("menu.usuario.eliminar"));

        menuSesion.setText(mensajeInternacionalizacionHandler.get("menu.sesion"));
        menuItemCerrarSesion.setText(mensajeInternacionalizacionHandler.get("menu.salir.cerrar"));

        menuIdioma.setText(mensajeInternacionalizacionHandler.get("menu.idiomas"));
        itemIdiomaES.setText(mensajeInternacionalizacionHandler.get("menu.idioma.es"));
        itemIdiomaEN.setText(mensajeInternacionalizacionHandler.get("menu.idioma.en"));
        itemIdiomaFR.setText(mensajeInternacionalizacionHandler.get("menu.idioma.fr"));
    }

    public void configurarOpcionesPorRol(String rol) {
        if ("ADMIN".equalsIgnoreCase(rol) || "ADMINISTRADOR".equalsIgnoreCase(rol)) {
            menuUsuario.setVisible(true);
            menuProducto.setVisible(true);
            menuCarrito.setVisible(true);
            menuSesion.setVisible(true);

            menuUsuario.setEnabled(true);
            menuProducto.setEnabled(true);
            menuCarrito.setEnabled(true);
            menuSesion.setEnabled(true);

            menuItemCrearUsuario.setEnabled(true);
            menuItemListarUsuario.setEnabled(true);
            menuItemModificarUsuario.setEnabled(true);
            menuItemEliminarUsuario.setEnabled(true);

            menuItemCrearProducto.setEnabled(true);
            menuItemEliminarProducto.setEnabled(true);
            menuItemActualizarProducto.setEnabled(true);
            menuItemBuscarProducto.setEnabled(true);

            menuItemCrearCarrito.setEnabled(true);
            menuItemListarCarrito.setEnabled(true);
            menuItemEliminarCarrito.setEnabled(true);
            menuItemModificarCarrito.setEnabled(true);
        } else {
            menuUsuario.setVisible(false);
            menuProducto.setVisible(true);
            menuCarrito.setVisible(true);
            menuSesion.setVisible(true);

            menuProducto.setEnabled(true);
            menuItemCrearProducto.setEnabled(false);
            menuItemEliminarProducto.setEnabled(false);
            menuItemActualizarProducto.setEnabled(false);
            menuItemBuscarProducto.setEnabled(true);

            menuCarrito.setEnabled(true);
            menuItemCrearCarrito.setEnabled(true);
            menuItemListarCarrito.setEnabled(true);
            menuItemEliminarCarrito.setEnabled(true);
            menuItemModificarCarrito.setEnabled(true);

            menuSesion.setEnabled(true);
            menuItemCerrarSesion.setEnabled(true);
        }
    }

    public JMenuItem getMenuItemCrearUsuario() { return menuItemCrearUsuario; }
    public JMenuItem getMenuItemListarUsuario() { return menuItemListarUsuario; }
    public JMenuItem getMenuItemModificarUsuario() { return menuItemModificarUsuario; }
    public JMenuItem getMenuItemEliminarUsuario() { return menuItemEliminarUsuario; }

    public JMenuItem getMenuItemCrearProducto() { return menuItemCrearProducto; }
    public JMenuItem getMenuItemEliminarProducto() { return menuItemEliminarProducto; }
    public JMenuItem getMenuItemActualizarProducto() { return menuItemActualizarProducto; }
    public JMenuItem getMenuItemBuscarProducto() { return menuItemBuscarProducto; }

    public JMenuItem getMenuItemCrearCarrito() { return menuItemCrearCarrito; }
    public JMenuItem getMenuItemListarCarrito() { return menuItemListarCarrito; }
    public JMenuItem getMenuItemEliminarCarrito() { return menuItemEliminarCarrito; }
    public JMenuItem getMenuItemModificarCarrito() {
        return menuItemModificarCarrito;
    }


    public JMenuItem getItemIdiomaES() {
        return itemIdiomaES;
    }

    public JMenuItem getItemIdiomaEN() {
        return itemIdiomaEN;
    }

    public JMenuItem getItemIdiomaFR() {
        return itemIdiomaFR;
    }

    public JMenuItem getMenuItemCerrarSesion() { return menuItemCerrarSesion; }

    public JDesktopPane getjDesktopPane() { return jDesktopPane; }

    public void mostrarMensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    public void deshabilitarMenusAdministrador() {
        menuUsuario.setEnabled(false);
        menuProducto.setEnabled(false);
        menuCarrito.setEnabled(false);
        menuSesion.setEnabled(false);
    }
}
