package ec.edu.ups.controlador;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.*;

import java.util.List;

public class UsuarioController {

    private UsuarioDAO usuarioDAO;
    private MenuPrincipalView menuPrincipalView;
    private UsuarioAnadirView usuarioAnadirView;
    private UsuarioListarView usuarioListarView;
    private UsuarioModificarView usuarioModificarView;
    private UsuarioEliminarView usuarioEliminarView;
    private LoginView loginView;
    private Usuario usuarioAutenticado;

    public UsuarioController(UsuarioDAO usuarioDAO,
                             MenuPrincipalView menuPrincipalView,
                             UsuarioAnadirView usuarioAnadirView,
                             UsuarioListarView usuarioListarView,
                             UsuarioModificarView usuarioModificarView,
                             UsuarioEliminarView usuarioEliminarView,
                             LoginView loginView) {

        this.usuarioDAO = usuarioDAO;
        this.menuPrincipalView = menuPrincipalView;
        this.usuarioAnadirView = usuarioAnadirView;
        this.usuarioListarView = usuarioListarView;
        this.usuarioModificarView = usuarioModificarView;
        this.usuarioEliminarView = usuarioEliminarView;
        this.loginView = loginView;

        iniciarEventos();
    }

    private void iniciarEventos() {
        loginView.getBtnIniciarSesion().addActionListener(e -> {
            String username = loginView.getTxtUsername().getText();
            String contrasenia = new String(loginView.getTxtContrasenia().getPassword());

            Usuario usuario = usuarioDAO.autenticar(username, contrasenia);
            if (usuario != null) {
                usuarioAutenticado = usuario;
                loginView.dispose();
            } else {
                loginView.mostrarMensaje("Usuario o contraseña incorrecta");
            }
        });

        usuarioAnadirView.getBtnRegistrar().addActionListener(e -> {
            String username = usuarioAnadirView.getTxtUsername().getText();
            String contrasenia = new String(usuarioAnadirView.getTxtContrasenia().getPassword());
            Rol rol = Rol.USUARIO;

            Usuario nuevo = new Usuario(username, contrasenia, rol);
            if (usuarioDAO.buscarPorUsername(username) != null) {
                usuarioAnadirView.mostrarMensaje("Ya existe un usuario con ese nombre");
            } else {
                usuarioDAO.crear(nuevo);
                usuarioAnadirView.mostrarMensaje("Usuario registrado correctamente");
                usuarioAnadirView.limpiarCampos();
            }
        });

        usuarioListarView.getBtnListar().addActionListener(e -> {
            List<Usuario> lista = usuarioDAO.listarTodos();
            usuarioListarView.cargarDatosTabla(lista);
        });

        usuarioEliminarView.getBuscarButton().addActionListener(e -> {
            String username = usuarioEliminarView.getTextID().getText();
            Usuario u = usuarioDAO.buscarPorUsername(username);
            if (u != null) {
                usuarioEliminarView.mostrarMensaje("Usuario encontrado: " + u.getUsername());
            } else {
                usuarioEliminarView.mostrarMensaje("No se encontró el usuario");
            }
        });

        usuarioEliminarView.getEliminarButton().addActionListener(e -> {
            String username = usuarioEliminarView.getTextID().getText();
            Usuario u = usuarioDAO.buscarPorUsername(username);
            if (u != null) {
                usuarioDAO.eliminar(username);
                usuarioEliminarView.mostrarMensaje("Usuario eliminado");
            } else {
                usuarioEliminarView.mostrarMensaje("Usuario no encontrado");
            }
        });

        usuarioModificarView.getBtnBuscar().addActionListener(e -> {
            String username = usuarioModificarView.getTxtID().getText();
            Usuario u = usuarioDAO.buscarPorUsername(username);
            if (u != null) {
                usuarioModificarView.getTxtNombre().setText(u.getUsername());
                usuarioModificarView.getTxtContrasenia().setText(u.getContrasenia());
                usuarioModificarView.getCmbRol().setSelectedItem(u.getRol().name());
            } else {
                usuarioModificarView.mostrarMensaje("No se encontró usuario");
            }
        });

        usuarioModificarView.getBtnModificar().addActionListener(e -> {
            String username = usuarioModificarView.getTxtID().getText();
            Usuario u = usuarioDAO.buscarPorUsername(username);
            if (u != null) {
                u.setUsername(usuarioModificarView.getTxtNombre().getText());
                u.setContrasenia(new String(usuarioModificarView.getTxtContrasenia().getPassword()));
                u.setRol(Rol.valueOf(usuarioModificarView.getCmbRol().getSelectedItem().toString()));
                usuarioDAO.actualizar(u);
                usuarioModificarView.mostrarMensaje("Usuario modificado correctamente");
            } else {
                usuarioModificarView.mostrarMensaje("Error al modificar");
            }
        });
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
}
