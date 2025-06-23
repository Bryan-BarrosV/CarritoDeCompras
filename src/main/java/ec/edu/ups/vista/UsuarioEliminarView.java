package ec.edu.ups.vista;

import javax.swing.*;

public class UsuarioEliminarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField textID;
    private JButton buscarButton;
    private JTable tblProductos;
    private JButton eliminarButton;

    public UsuarioEliminarView() {
        setTitle("Eliminar Usuario");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(700, 400);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public JTextField getTextID() {
        return textID;
    }

    public JButton getBuscarButton() {
        return buscarButton;
    }

    public JTable getTblProductos() {
        return tblProductos;
    }

    public JButton getEliminarButton() {
        return eliminarButton;
    }

    public void mostrarMensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }
}
