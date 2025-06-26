package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalView extends JFrame {
    private JMenuBar menuBar;

    private JMenu menuProducto;
    private JMenu menuCarrito;
    private JMenu menuUsuario;
    private JMenu menuSesion;

    private JMenuItem menuItemCrearProducto;
    private JMenuItem menuItemEliminarProducto;
    private JMenuItem menuItemActualizarProducto;
    private JMenuItem menuItemBuscarProducto;

    private JMenuItem menuItemCrearCarrito;
    private JMenuItem menuItemListarCarrito;
    private JMenuItem menuItemEliminarCarrito;

    private JMenuItem menuItemCrearUsuario;
    private JMenuItem menuItemListarUsuario;
    private JMenuItem menuItemModificarUsuario;
    private JMenuItem menuItemEliminarUsuario;

    private JMenuItem menuItemCerrarSesion;

    private JDesktopPane jDesktopPane;

    public MenuPrincipalView() {
        jDesktopPane = new JDesktopPane();
        menuBar = new JMenuBar();

        menuProducto = new JMenu("Producto");
        menuCarrito = new JMenu("Carrito");
        menuUsuario = new JMenu("Usuario");
        menuSesion = new JMenu("Sesión");

        menuItemCrearProducto = new JMenuItem("Crear Producto");
        menuItemEliminarProducto = new JMenuItem("Eliminar Producto");
        menuItemActualizarProducto = new JMenuItem("Modificar Producto");
        menuItemBuscarProducto = new JMenuItem("Buscar Producto");

        menuItemCrearCarrito = new JMenuItem("Crear Carrito");
        menuItemListarCarrito= new JMenuItem("Listar Carrito");
        menuItemEliminarCarrito = new JMenuItem("Eliminar Carrito");

        menuItemCrearUsuario = new JMenuItem("Crear Usuario");
        menuItemListarUsuario = new JMenuItem("Listar Usuarios");
        menuItemModificarUsuario = new JMenuItem("Modificar Usuario");
        menuItemEliminarUsuario = new JMenuItem("Eliminar Usuario");

        menuItemCerrarSesion = new JMenuItem("Cerrar Sesión");

        menuBar.add(menuProducto);
        menuBar.add(menuCarrito);
        menuBar.add(menuUsuario);
        menuBar.add(menuSesion);

        menuProducto.add(menuItemCrearProducto);
        menuProducto.add(menuItemEliminarProducto);
        menuProducto.add(menuItemActualizarProducto);
        menuProducto.add(menuItemBuscarProducto);

        menuCarrito.add(menuItemCrearCarrito);
        menuCarrito.add(menuItemListarCarrito);
        menuCarrito.add(menuItemEliminarCarrito);

        menuUsuario.add(menuItemCrearUsuario);
        menuUsuario.add(menuItemListarUsuario);
        menuUsuario.add(menuItemModificarUsuario);
        menuUsuario.add(menuItemEliminarUsuario);

        menuSesion.add(menuItemCerrarSesion);

        setJMenuBar(menuBar);

        setContentPane(jDesktopPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Carrito de Compras");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(false);
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
        } else {
            menuUsuario.setVisible(true);
            menuSesion.setVisible(true);

            menuProducto.setVisible(true);
            menuProducto.setEnabled(true);
            menuItemCrearProducto.setEnabled(true);
            menuItemEliminarProducto.setEnabled(true);
            menuItemActualizarProducto.setEnabled(true);
            menuItemBuscarProducto.setEnabled(true);

            menuCarrito.setVisible(true);
            menuCarrito.setEnabled(true);
            menuItemCrearCarrito.setEnabled(true);
            menuItemListarCarrito.setEnabled(true);
            menuItemEliminarCarrito.setEnabled(true);
        }

    }
    public JMenuItem getMenuItemCrearUsuario() {
        return menuItemCrearUsuario;
    }

    public JMenuItem getMenuItemListarUsuario() {
        return menuItemListarUsuario;
    }

    public JMenuItem getMenuItemModificarUsuario() {
        return menuItemModificarUsuario;
    }

    public JMenuItem getMenuItemEliminarUsuario() {
        return menuItemEliminarUsuario;
    }

    public JMenuItem getMenuItemCrearProducto() {
        return menuItemCrearProducto;
    }

    public JMenuItem getMenuItemEliminarProducto() {
        return menuItemEliminarProducto;
    }

    public JMenuItem getMenuItemActualizarProducto() {
        return menuItemActualizarProducto;
    }

    public JMenuItem getMenuItemBuscarProducto() {
        return menuItemBuscarProducto;
    }

    public JMenuItem getMenuItemCrearCarrito() {
        return menuItemCrearCarrito;
    }

    public JMenuItem getMenuItemListarCarrito() {
        return menuItemListarCarrito;
    }

    public JMenuItem getMenuItemEliminarCarrito() {
        return menuItemEliminarCarrito;
    }

    public JMenuItem getMenuItemCerrarSesion() {
        return menuItemCerrarSesion;
    }

    public JDesktopPane getjDesktopPane() {
        return jDesktopPane;
    }

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