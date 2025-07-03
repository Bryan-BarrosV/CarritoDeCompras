package ec.edu.ups.vista;

import ec.edu.ups.modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UsuarioListarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtBuscar;
    private JTable tblUsuarios;
    private JButton btnListar;
    private JButton btnBuscar;
    private DefaultTableModel modelo;

    public UsuarioListarView() {
        setTitle("Listar Usuarios");
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(false);

        modelo = new DefaultTableModel();
        String[] columnas = {"Username", "Nombre Completo", "Correo", "Teléfono", "Rol"};
        modelo.setColumnIdentifiers(columnas);
        tblUsuarios.setModel(modelo);
    }

    public void cargarDatosTabla(List<Usuario> listaUsuarios) {
        modelo.setRowCount(0);
        for (Usuario u : listaUsuarios) {
            Object[] fila = {
                    u.getUsername(),
                    u.getNombreCompleto(),
                    u.getCorreo(),
                    u.getTelefono(),
                    u.getRol().name()
            };
            modelo.addRow(fila);
        }
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JTable getTblUsuarios() {
        return tblUsuarios;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
}
