package ec.edu.ups.vista;

import ec.edu.ups.controlador.ProductoController;
import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.dao.impl.ProductoDAOMemoria;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    @SuppressWarnings("all")
    public static void main(String [] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                ProductoAnadirView productoAnadirView = new ProductoAnadirView();
                ProductoListaView productoListaView = new ProductoListaView();
                ModificarProductoView modificarProductoView = new ModificarProductoView();

                ProductoDAO productoDAO = new ProductoDAOMemoria();

                new ProductoController(productoDAO, productoAnadirView, productoListaView, modificarProductoView);

                modificarProductoView.setVisible(true);
            }
        });
    }
}