package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;
    private MenuPrincipalView menuPrincipalView;
    private UsuarioAnadirView usuarioAnadirView;
    private UsuarioListarView usuarioListarView;
    private UsuarioModificarView usuarioModificarView;
    private UsuarioEliminarView usuarioEliminarView;

    public UsuarioController(MenuPrincipalView menuPrincipalView,
                             UsuarioAnadirView usuarioAnadirView,
                             UsuarioListarView usuarioListarView,
                             UsuarioModificarView usuarioModificarView,
                             UsuarioEliminarView usuarioEliminarView,
                             UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
        this.menuPrincipalView = menuPrincipalView;
        this.usuarioAnadirView = usuarioAnadirView;
        this.usuarioListarView = usuarioListarView;
        this.usuarioModificarView = usuarioModificarView;
        this.usuarioEliminarView = usuarioEliminarView;

        this.menuPrincipalView.getMenuItemCrearUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentana(usuarioAnadirView);
            }
        });

        this.menuPrincipalView.getMenuItemListarUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarUsuarios();
                mostrarVentana(usuarioListarView);
            }
        });

        this.menuPrincipalView.getMenuItemModificarUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentana(usuarioModificarView);
            }
        });

        this.menuPrincipalView.getMenuItemEliminarUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentana(usuarioEliminarView);
            }
        });

        this.usuarioAnadirView.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        this.usuarioAnadirView.getBtnLimpiar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioAnadirView.limpiarCampos();
            }
        });

        this.usuarioListarView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarUsuarios();
            }
        });

        this.usuarioModificarView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuarioModificar();
            }
        });

        this.usuarioModificarView.getBtnModificar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarUsuario();
            }
        });

        this.usuarioEliminarView.getBuscarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarUsuariosEnEliminar();
            }
        });

        this.usuarioEliminarView.getEliminarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuarioSeleccionado();
            }
        });
    }

    private void mostrarVentana(JInternalFrame ventana) {
        if (!ventana.isVisible()) {
            menuPrincipalView.getjDesktopPane().add(ventana);
            ventana.setVisible(true);
        } else {
            ventana.toFront();
        }
    }

    private void registrarUsuario() {
        String idStr = usuarioAnadirView.getTxtID().getText();
        String nombre = usuarioAnadirView.getTxtNombre().getText();
        String contrasenia = new String(usuarioAnadirView.getTxtContrasenia().getPassword());
        String rolStr = (String) usuarioAnadirView.getCbxRol().getSelectedItem();

        if (idStr.isEmpty() || nombre.isEmpty() || contrasenia.isEmpty() || rolStr == null) {
            usuarioAnadirView.mostrarMensaje("Complete todos los campos.");
            return;
        }

        int id = Integer.parseInt(idStr);
        Rol rol = Rol.valueOf(rolStr);

        Usuario usuario = new Usuario(id, nombre, contrasenia, nombre, rol);
        usuarioDAO.crear(usuario);

        usuarioAnadirView.mostrarMensaje("Usuario registrado correctamente.");
        usuarioAnadirView.limpiarCampos();
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        DefaultTableModel modelo = (DefaultTableModel) usuarioListarView.getTblUsuarios().getModel();
        modelo.setRowCount(0);

        for (Usuario u : usuarios) {
            Object[] fila = {u.getId(), u.getUsername(), u.getNombre(), u.getRol().name()};
            modelo.addRow(fila);
        }
    }

    private void buscarUsuarioModificar() {
        String idStr = usuarioModificarView.getTxtID().getText();
        if (idStr.isEmpty()) {
            usuarioModificarView.mostrarMensaje("Ingrese el ID para buscar.");
            return;
        }
        int id = Integer.parseInt(idStr);
        Usuario usuario = usuarioDAO.buscarPorCodigo(id);
        if (usuario == null) {
            usuarioModificarView.mostrarMensaje("Usuario no encontrado.");
            return;
        }

        usuarioModificarView.getTxtNombre().setText(usuario.getNombre());
        usuarioModificarView.getTxtContrasenia().setText(usuario.getContrasenia());
        usuarioModificarView.getCmbRol().setSelectedItem(usuario.getRol().name());
    }

    private void modificarUsuario() {
        String idStr = usuarioModificarView.getTxtID().getText();
        if (idStr.isEmpty()) {
            usuarioModificarView.mostrarMensaje("Ingrese el ID para modificar.");
            return;
        }
        int id = Integer.parseInt(idStr);
        Usuario usuario = usuarioDAO.buscarPorCodigo(id);
        if (usuario == null) {
            usuarioModificarView.mostrarMensaje("Usuario no encontrado.");
            return;
        }

        usuario.setNombre(usuarioModificarView.getTxtNombre().getText());
        usuario.setContrasenia(new String(usuarioModificarView.getTxtContrasenia().getPassword()));
        usuario.setRol(Rol.valueOf((String) usuarioModificarView.getCmbRol().getSelectedItem()));

        usuarioDAO.actualizar(usuario);

        usuarioModificarView.mostrarMensaje("Usuario modificado correctamente.");
        usuarioModificarView.limpiarCampos();
    }

    private void listarUsuariosEnEliminar() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();
        DefaultTableModel modelo = (DefaultTableModel) usuarioEliminarView.getTblProductos().getModel();
        modelo.setRowCount(0);

        for (Usuario u : usuarios) {
            Object[] fila = {u.getId(), u.getUsername(), u.getNombre(), u.getRol().name()};
            modelo.addRow(fila);
        }
    }

    private void eliminarUsuarioSeleccionado() {
        int filaSeleccionada = usuarioEliminarView.getTblProductos().getSelectedRow();
        if (filaSeleccionada == -1) {
            usuarioEliminarView.mostrarMensaje("Debe seleccionar un usuario para eliminar.");
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(usuarioEliminarView,
                "¿Está seguro de eliminar este usuario?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            int id = (int) usuarioEliminarView.getTblProductos().getValueAt(filaSeleccionada, 0);
            usuarioDAO.eliminar(String.valueOf(id));
            usuarioEliminarView.mostrarMensaje("Usuario eliminado correctamente.");
            listarUsuariosEnEliminar();
        }
    }
}
