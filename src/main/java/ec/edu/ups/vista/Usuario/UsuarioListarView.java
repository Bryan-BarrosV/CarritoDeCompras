package ec.edu.ups.vista.Usuario;

import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UsuarioListarView extends JInternalFrame {

    private JPanel panelPrincipal;
    private JTextField txtBuscar;
    private JTable tblUsuarios;
    private JButton btnListar;
    private JButton btnBuscar;
    private JLabel lblId;
    private DefaultTableModel modelo;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public UsuarioListarView(MensajeInternacionalizacionHandler handler) {
        this.mensajeInternacionalizacionHandler= handler;
        setTitle(handler.get("usuario.listar.titulo"));
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setVisible(false);

        modelo = new DefaultTableModel();
        String[] columnas = {
                handler.get("usuario.label.username"),
                handler.get("usuario.label.nombre"),
                handler.get("usuario.label.correo"),
                handler.get("usuario.label.telefono"),
                handler.get("usuario.label.rol")
        };
        modelo.setColumnIdentifiers(columnas);
        tblUsuarios.setModel(modelo);

        actualizarTextos();
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

    public void actualizarTextos() {
        setTitle(mensajeInternacionalizacionHandler.get("usuario.listar.titulo"));
        lblId.setText(mensajeInternacionalizacionHandler.get("usuario.label.username"));
        btnBuscar.setText(mensajeInternacionalizacionHandler.get("boton.buscar"));
        btnListar.setText(mensajeInternacionalizacionHandler.get("boton.listar"));

        modelo.setColumnIdentifiers(new Object[]{
                mensajeInternacionalizacionHandler.get("usuario.label.username"),
                mensajeInternacionalizacionHandler.get("usuario.label.nombre"),
                mensajeInternacionalizacionHandler.get("usuario.label.correo"),
                mensajeInternacionalizacionHandler.get("usuario.label.telefono"),
                mensajeInternacionalizacionHandler.get("usuario.label.rol")
        });
    }
}
