package ec.edu.ups.controlador;

import ec.edu.ups.dao.ContrasenaDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Contrasena;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class UsuarioController {

    private final UsuarioDAO usuarioDAO;
    private final ContrasenaDAO contrasenaDAO;

    private final MenuPrincipalView principalView;
    private final UsuarioAnadirView usuarioAnadirView;
    private final UsuarioListarView usuarioListarView;
    private final UsuarioModificarView usuarioModificarView;
    private final UsuarioEliminarView usuarioEliminarView;
    private final LoginView loginView;
    private final ContrasenaView contrasenaView;
    private final ContrasenaPreguntaView contrasenaPreguntaView;

    private Usuario usuarioTemporal; // para almacenar antes de guardar
    private boolean esperandoPreguntas = false;
    private Usuario usuarioAutenticado;

    public UsuarioController(
            UsuarioDAO usuarioDAO,
            ContrasenaDAO contrasenaDAO,
            MenuPrincipalView principalView,
            UsuarioAnadirView usuarioAnadirView,
            UsuarioListarView usuarioListarView,
            UsuarioModificarView usuarioModificarView,
            UsuarioEliminarView usuarioEliminarView,
            LoginView loginView,
            ContrasenaView contrasenaView,
            ContrasenaPreguntaView contrasenaPreguntaView
    ) {
        this.usuarioDAO = usuarioDAO;
        this.contrasenaDAO = contrasenaDAO;
        this.principalView = principalView;
        this.usuarioAnadirView = usuarioAnadirView;
        this.usuarioListarView = usuarioListarView;
        this.usuarioModificarView = usuarioModificarView;
        this.usuarioEliminarView = usuarioEliminarView;
        this.loginView = loginView;
        this.contrasenaView = contrasenaView;
        this.contrasenaPreguntaView = contrasenaPreguntaView;

        configurarEventos();
    }

    private void configurarEventos() {
        // Registro
        usuarioAnadirView.getBtnRegistrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarFlujoRegistro();
            }
        });

        // Guardar preguntas
        contrasenaPreguntaView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPreguntasYFinalizarRegistro();
            }
        });

        // Inicio de sesión
        loginView.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginView.getTxtUsername().getText().trim();
                String contrasenia = new String(loginView.getTxtContrasenia().getPassword()).trim();

                if (username.isEmpty() || contrasenia.isEmpty()) {
                    loginView.mostrarMensaje("Debe completar todos los campos.");
                    return;
                }

                Usuario usuario = usuarioDAO.autenticar(username, contrasenia);
                if (usuario != null) {
                    usuarioAutenticado = usuario;
                    loginView.dispose(); // Cierra login (esto activa windowClosed en Main)
                } else {
                    loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
                }
            }
        });
    }

    private void iniciarFlujoRegistro() {
        String username = usuarioAnadirView.getTxtUsername().getText();
        String contrasenia = new String(usuarioAnadirView.getTxtContrasenia().getPassword());

        if (username.isEmpty() || contrasenia.isEmpty()) {
            usuarioAnadirView.mostrarMensaje("Complete todos los campos");
            return;
        }

        if (usuarioDAO.buscarPorUsername(username) != null) {
            usuarioAnadirView.mostrarMensaje("El nombre de usuario ya está en uso");
            return;
        }

        usuarioTemporal = new Usuario();
        usuarioTemporal.setUsername(username);
        usuarioTemporal.setContrasenia(contrasenia);
        usuarioTemporal.setRol(Rol.USUARIO);

        contrasenaPreguntaView.setUsername(username);
        loginView.getLayeredPane().add(contrasenaPreguntaView);
        contrasenaPreguntaView.setVisible(true);
        contrasenaPreguntaView.toFront();
        esperandoPreguntas = true;
    }

    private void guardarPreguntasYFinalizarRegistro() {
        if (usuarioTemporal == null || !esperandoPreguntas) {
            contrasenaPreguntaView.mostrarMensaje("Error interno. Intente registrar nuevamente.");
            return;
        }

        String r1 = contrasenaPreguntaView.getTxtRespuesta1().getText().trim();
        String r2 = contrasenaPreguntaView.getTxtRespuesta2().getText().trim();

        if (r1.isEmpty() || r2.isEmpty()) {
            contrasenaPreguntaView.mostrarMensaje("Debe completar ambas respuestas.");
            return;
        }

        usuarioDAO.crear(usuarioTemporal);

        Contrasena contrasena = new Contrasena(
                usuarioTemporal.getUsername(),
                Arrays.asList("¿Cuál es el nombre de tu madre?", "¿Cuál es tu equipo favorito de fútbol?"),
                Arrays.asList(r1, r2)
        );
        contrasenaDAO.guardar(contrasena);

        contrasenaPreguntaView.setVisible(false);
        usuarioAnadirView.setVisible(false);
        usuarioAnadirView.limpiarCampos();
        contrasenaPreguntaView.getTxtRespuesta1().setText("");
        contrasenaPreguntaView.getTxtRespuesta2().setText("");

        loginView.mostrarMensaje("Usuario registrado correctamente");
        esperandoPreguntas = false;
        usuarioTemporal = null;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
}
