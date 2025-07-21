package ec.edu.ups.controlador;

import ec.edu.ups.dao.ContrasenaDAO;
import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.exception.CedulaValidationException;
import ec.edu.ups.exception.PasswordValidationException;
import ec.edu.ups.exception.UsuarioException;
import ec.edu.ups.modelo.Contrasena;
import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.vista.*;
import ec.edu.ups.vista.Contrasena.ContrasenaPreguntaView;
import ec.edu.ups.vista.Contrasena.ContrasenaView;
import ec.edu.ups.vista.Usuario.UsuarioAnadirView;
import ec.edu.ups.vista.Usuario.UsuarioEliminarView;
import ec.edu.ups.vista.Usuario.UsuarioListarView;
import ec.edu.ups.vista.Usuario.UsuarioModificarView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Controlador encargado de gestionar toda la lógica relacionada con la entidad Usuario,
 * incluyendo registro, autenticación, modificación, eliminación, validaciones y manejo de contraseñas.
 */
public class UsuarioController {

    private UsuarioDAO usuarioDAO;
    private final ContrasenaDAO contrasenaDAO;
    private final MenuPrincipalView principalView;
    private final UsuarioAnadirView usuarioAnadirView;
    private final UsuarioListarView usuarioListarView;
    private final UsuarioModificarView usuarioModificarView;
    private final UsuarioEliminarView usuarioEliminarView;
    private final LoginView loginView;
    private final ContrasenaView contrasenaView;
    private final ContrasenaPreguntaView contrasenaPreguntaView;
    private final PreguntaDAO preguntaDAO;

    private Usuario usuarioTemporal;
    private boolean esperandoPreguntas = false;
    private Usuario usuarioAutenticado;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;
    private String modoAlmacenamiento = "Memoria";

    /**
     * Constructor del controlador de usuarios que inicializa DAOs, vistas y eventos.
     */
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
            ContrasenaPreguntaView contrasenaPreguntaView,
            PreguntaDAO preguntaDAO,
            MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler
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
        this.preguntaDAO = preguntaDAO;
        this.mensajeInternacionalizacionHandler = mensajeInternacionalizacionHandler;

        configurarEventos();
    }

    /**
     * Configura los eventos de todas las vistas relacionadas al usuario.
     */
    private void configurarEventos() {
        // Registro
        usuarioAnadirView.getBtnRegistrar().addActionListener(e -> iniciarFlujoRegistro());
        usuarioAnadirView.getBtnLimpiar().addActionListener(e -> {
            try {
                usuarioAnadirView.limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(usuarioAnadirView,
                        "Error al limpiar los campos: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Eliminación
        usuarioEliminarView.getBtnEliminar().addActionListener(e -> eliminarUsuario());
        usuarioEliminarView.getBtnBuscar().addActionListener(e -> buscarUsuarioParaEliminar());

        // Listado
        usuarioListarView.getBtnBuscar().addActionListener(e -> buscarUsuarioPorUsername());
        usuarioListarView.getBtnListar().addActionListener(e -> {
            List<Usuario> listaUsuarios = usuarioDAO.listarTodos();
            usuarioListarView.cargarDatosTabla(listaUsuarios);
        });

        // Modificación
        usuarioModificarView.getBtnModificar().addActionListener(e -> modificarUsuario());
        usuarioModificarView.getBtnBuscar().addActionListener(e -> buscarUsuarioParaModificar());

        // Guardar preguntas de seguridad
        contrasenaPreguntaView.getBtnGuardar().addActionListener(e -> guardarPreguntasYFinalizarRegistro());

        // Autenticación
        loginView.getBtnIniciarSesion().addActionListener(e -> {
            String username = loginView.getTxtUsername().getText().trim();
            String contrasenia = new String(loginView.getTxtContrasenia().getPassword()).trim();

            if (username.isEmpty() || contrasenia.isEmpty()) {
                loginView.mostrarMensaje("Debe completar todos los campos.");
                return;
            }

            Usuario usuario = usuarioDAO.autenticar(username, contrasenia);
            if (usuario != null) {
                usuarioAutenticado = usuario;
                loginView.dispose();
            } else {
                loginView.mostrarMensaje("Usuario o contraseña incorrectos.");
            }
        });

        // Cambio de idioma
        loginView.getItemIdiomaES().addActionListener(e -> {
            mensajeInternacionalizacionHandler.setLenguaje("es", "EC");
            loginView.actualizarTextosLogin();
        });
        loginView.getItemIdiomaEN().addActionListener(e -> {
            mensajeInternacionalizacionHandler.setLenguaje("en", "US");
            loginView.actualizarTextosLogin();
        });
        loginView.getItemIdiomaFR().addActionListener(e -> {
            mensajeInternacionalizacionHandler.setLenguaje("fr", "FR");
            loginView.actualizarTextosLogin();
        });

        // Cambio de almacenamiento
        loginView.getItemMemoria().addActionListener(e -> {
            modoAlmacenamiento = "Memoria";
            loginView.mostrarMensaje("Modo almacenamiento: Memoria");
        });
        loginView.getItemTexto().addActionListener(e -> {
            modoAlmacenamiento = "Archivo de Texto";
            loginView.mostrarMensaje("Modo almacenamiento: Archivo de Texto");
        });
        loginView.getItemBinario().addActionListener(e -> {
            modoAlmacenamiento = "Archivo Binario";
            loginView.mostrarMensaje("Modo almacenamiento: Archivo Binario");
        });
    }

    /**
     * Inicia el flujo de registro de usuario validando datos y mostrando vista de preguntas.
     */
    private void iniciarFlujoRegistro() {
        try {
            String username = usuarioAnadirView.getTxtUsername().getText().trim();
            String contrasenia = new String(usuarioAnadirView.getTxtContrasenia().getPassword()).trim();

            if (username.isEmpty() || contrasenia.isEmpty()) {
                throw new UsuarioException("Complete todos los campos");
            }
            if (!validarCedulaEcuatoriana(username)) {
                throw new CedulaValidationException("La cédula ingresada no es válida");
            }
            if (!validarPassword(contrasenia)) {
                throw new PasswordValidationException("La contraseña debe tener al menos 6 caracteres, una mayúscula, una minúscula y uno de los caracteres @, _, -");
            }
            if (usuarioDAO.buscarPorUsername(username) != null) {
                throw new UsuarioException("El nombre de usuario ya está en uso");
            }

            String nombreCompleto = usuarioAnadirView.getTxtNombreCompleto().getText().trim();
            String correo = usuarioAnadirView.getTxtCorreo().getText().trim();
            String telefono = usuarioAnadirView.getTxtTelefono().getText().trim();

            if (nombreCompleto.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
                throw new UsuarioException("Debe completar todos los campos del formulario.");
            }

            usuarioTemporal = new Usuario();
            usuarioTemporal.setUsername(username);
            usuarioTemporal.setContrasenia(contrasenia);
            usuarioTemporal.setRol(Rol.USUARIO);
            usuarioTemporal.setNombreCompleto(nombreCompleto);
            usuarioTemporal.setCorreo(correo);
            usuarioTemporal.setTelefono(telefono);

            contrasenaPreguntaView.setUsername(username);
            loginView.getLayeredPane().add(contrasenaPreguntaView);
            contrasenaPreguntaView.setVisible(true);
            contrasenaPreguntaView.toFront();
            esperandoPreguntas = true;

        } catch (CedulaValidationException | PasswordValidationException | UsuarioException ex) {
            usuarioAnadirView.mostrarMensaje(ex.getMessage());
        } catch (Exception ex) {
            usuarioAnadirView.mostrarMensaje("Error inesperado: " + ex.getMessage());
        }
    }

    /**
     * Guarda las preguntas de seguridad y finaliza el registro del usuario.
     */
    private void guardarPreguntasYFinalizarRegistro() {
        if (usuarioTemporal == null || !esperandoPreguntas) {
            contrasenaPreguntaView.mostrarMensaje("Error interno. Intente registrar nuevamente.");
            return;
        }
        List<Pregunta> preguntas = preguntaDAO.obtenerTodas();
        List<String> respuestas = contrasenaPreguntaView.getRespuestas();

        for (String r : respuestas) {
            if (r.isEmpty()) {
                contrasenaPreguntaView.mostrarMensaje("Debe completar todas las respuestas.");
                return;
            }
        }

        usuarioDAO.crear(usuarioTemporal);
        Contrasena contrasena = new Contrasena(usuarioTemporal.getUsername(), preguntas, respuestas);
        contrasenaDAO.guardar(contrasena);

        contrasenaPreguntaView.setVisible(false);
        usuarioAnadirView.setVisible(false);
        usuarioAnadirView.limpiarCampos();
        contrasenaPreguntaView.limpiarCampos();

        loginView.mostrarMensaje("Usuario registrado correctamente");
        esperandoPreguntas = false;
        usuarioTemporal = null;
    }

    /**
     * Elimina un usuario si el nombre de usuario y contraseña coinciden.
     */
    private void eliminarUsuario() {
        String username = usuarioEliminarView.getTextCodigo().getText().trim();
        String contrasenia = new String(usuarioEliminarView.getTextContrasenia().getPassword()).trim();

        if (username.isEmpty() || contrasenia.isEmpty()) {
            usuarioEliminarView.mostrarMensaje("Ingrese todos los campos.");
            return;
        }

        Usuario usuario = usuarioDAO.buscarPorUsername(username);
        if (usuario != null) {
            if (usuario.getContrasenia().equals(contrasenia)) {
                usuarioDAO.eliminar(username);
                usuarioEliminarView.mostrarMensaje("Usuario eliminado exitosamente.");
                usuarioEliminarView.limpiarCampos();
            } else {
                usuarioEliminarView.mostrarMensaje("Contraseña incorrecta.");
            }
        } else {
            usuarioEliminarView.mostrarMensaje("Usuario no encontrado.");
        }
    }

    /**
     * Busca un usuario para eliminarlo y muestra sus datos.
     */
    private void buscarUsuarioParaEliminar() {
        String username = usuarioEliminarView.getTextCodigo().getText().trim();

        if (username.isEmpty()) {
            usuarioEliminarView.mostrarMensaje("Ingrese el nombre de usuario.");
            return;
        }

        Usuario usuario = usuarioDAO.buscarPorUsername(username);
        if (usuario != null) {
            usuarioEliminarView.mostrarUsuario(usuario);
        } else {
            usuarioEliminarView.mostrarMensaje("Usuario no encontrado.");
        }
    }

    /**
     * Busca un usuario por su nombre de usuario y lo muestra en la tabla de la vista.
     */
    private void buscarUsuarioPorUsername() {
        String username = usuarioListarView.getTxtBuscar().getText().trim();

        if (username.isEmpty()) {
            usuarioListarView.mostrarMensaje("Ingrese un nombre de usuario.");
            return;
        }

        Usuario usuario = usuarioDAO.buscarPorUsername(username);
        if (usuario != null) {
            usuarioListarView.cargarDatosTabla(List.of(usuario));
        } else {
            usuarioListarView.mostrarMensaje("Usuario no encontrado.");
            usuarioListarView.limpiarTabla();
        }
    }

    /**
     * Modifica el rol de un usuario existente.
     */
    private void modificarUsuario() {
        String username = usuarioModificarView.getTxtNombre().getText().trim();
        Usuario usuario = usuarioDAO.buscarPorUsername(username);

        if (usuario != null) {
            Rol rolSeleccionado = (Rol) usuarioModificarView.getCmbRol().getSelectedItem();
            usuario.setRol(rolSeleccionado);
            usuarioDAO.actualizar(usuario);
            usuarioModificarView.mostrarMensaje("Usuario actualizado correctamente.");
        } else {
            usuarioModificarView.mostrarMensaje("Usuario no encontrado.");
        }
    }

    /**
     * Busca un usuario para modificarlo y muestra su información en la vista.
     */
    private void buscarUsuarioParaModificar() {
        String username = usuarioModificarView.getTxtNombre().getText().trim();

        if (username.isEmpty()) {
            usuarioModificarView.mostrarMensaje("Ingrese el nombre de usuario.");
            return;
        }

        Usuario usuario = usuarioDAO.buscarPorUsername(username);
        if (usuario != null) {
            usuarioModificarView.getTxtContrasenia().setText(usuario.getContrasenia());
            usuarioModificarView.getCmbRol().setSelectedItem(usuario.getRol());
        } else {
            usuarioModificarView.mostrarMensaje("Usuario no encontrado.");
            usuarioModificarView.limpiarCampos();
        }
    }

    /**
     * Valida si una cédula ecuatoriana es válida según su algoritmo de verificador.
     */
    private boolean validarCedulaEcuatoriana(String cedula) {
        if (cedula == null || cedula.length() != 10) return false;
        int suma = 0;
        int digitoVerificador = Character.getNumericValue(cedula.charAt(9));
        for (int i = 0; i < 9; i++) {
            int num = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                num *= 2;
                if (num > 9) num -= 9;
            }
            suma += num;
        }
        int decenaSuperior = (int) (Math.ceil(suma / 10.0) * 10);
        int resultado = decenaSuperior - suma;
        if (resultado == 10) resultado = 0;
        return resultado == digitoVerificador;
    }

    /**
     * Valida si una contraseña cumple con los requisitos de seguridad.
     */
    private boolean validarPassword(String password) {
        return password.length() >= 6 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[@_-].*");
    }

    /**
     * Retorna el usuario que ha iniciado sesión correctamente.
     */
    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    /**
     * Establece una nueva instancia del DAO de usuarios.
     */
    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
}
