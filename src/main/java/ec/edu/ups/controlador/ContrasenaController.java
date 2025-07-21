package ec.edu.ups.controlador;

import ec.edu.ups.dao.ContrasenaDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Contrasena;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.Contrasena.ContrasenaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Controlador encargado de gestionar la recuperación y actualización de
 * la contraseña de usuarios mediante preguntas de seguridad.
 * <p>
 * Coordina la obtención de preguntas aleatorias, validación de respuestas
 * y actualización de la contraseña en el sistema.
 * </p>
 */
public class ContrasenaController {

    /** DAO para persistencia de datos de preguntas y respuestas de seguridad. */
    private ContrasenaDAO contrasenaDAO;
    /** DAO para operación sobre la entidad Usuario. */
    private final UsuarioDAO usuarioDAO;
    /** Vista asociada al flujo de recuperación de contraseña. */
    private final ContrasenaView contrasenaView;
    /** Índices de las preguntas de seguridad seleccionadas aleatoriamente. */
    private List<Integer> indicesPreguntasSeleccionadas;

    /**
     * Construye el controlador y registra los listeners para
     * validar usuario y guardar nueva contraseña.
     *
     * @param contrasenaDAO DAO para gestión de preguntas de seguridad
     * @param usuarioDAO    DAO para gestión de usuarios
     * @param contrasenaView vista que interactúa con el usuario
     */
    public ContrasenaController(
            ContrasenaDAO contrasenaDAO,
            UsuarioDAO usuarioDAO,
            ContrasenaView contrasenaView
    ) {
        this.contrasenaDAO = contrasenaDAO;
        this.usuarioDAO = usuarioDAO;
        this.contrasenaView = contrasenaView;

        // Listener para validar existencia de usuario y preguntas
        contrasenaView.getBtnValidarUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarUsuario();
            }
        });

        // Listener para verificar respuestas y actualizar contraseña
        contrasenaView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarNuevaContrasena();
            }
        });
    }

    /**
     * Valida que el usuario exista y tenga al menos tres preguntas
     * registradas, selecciona tres de forma aleatoria.
     */
    private void validarUsuario() {
        String username = contrasenaView.getTxtUsername().getText().trim();
        Contrasena contrasena = contrasenaDAO.buscarPorUsername(username);

        if (contrasena == null || contrasena.getPreguntas().size() < 3) {
            contrasenaView.mostrarMensaje(
                    "Usuario no encontrado o no tiene suficientes preguntas registradas."
            );
            return;
        }

        indicesPreguntasSeleccionadas =
                obtenerTresIndicesAleatorios(contrasena.getPreguntas().size());

        contrasenaView.setPreguntas(
                contrasena.getPreguntas().get(indicesPreguntasSeleccionadas.get(0)),
                contrasena.getPreguntas().get(indicesPreguntasSeleccionadas.get(1)),
                contrasena.getPreguntas().get(indicesPreguntasSeleccionadas.get(2))
        );
    }

    /**
     * Verifica al menos dos respuestas correctas y actualiza la contraseña.
     */
    private void guardarNuevaContrasena() {
        String username = contrasenaView.getTxtUsername().getText().trim();
        String nuevaContrasena =
                new String(contrasenaView.getTxtNuevaContrasena().getPassword()).trim();

        if (username.isEmpty() || nuevaContrasena.isEmpty()) {
            contrasenaView.mostrarMensaje("Debe completar todos los campos.");
            return;
        }

        Contrasena contrasena = contrasenaDAO.buscarPorUsername(username);
        if (contrasena == null
                || indicesPreguntasSeleccionadas == null
                || indicesPreguntasSeleccionadas.size() < 3) {
            contrasenaView.mostrarMensaje(
                    "No se puede verificar. Intente validar el usuario primero."
            );
            return;
        }

        // Recopila respuestas del usuario y las correctas según índices seleccionados
        List<String> respuestasUsuario = Arrays.asList(
                contrasenaView.getTxtRespuesta1().getText().trim(),
                contrasenaView.getTxtRespuesta2().getText().trim(),
                contrasenaView.getTxtRespuesta3().getText().trim()
        );
        List<String> respuestasCorrectas = new ArrayList<>();
        for (int idx : indicesPreguntasSeleccionadas) {
            respuestasCorrectas.add(contrasena.getRespuestas().get(idx));
        }

        // Cuenta cuántas respuestas coinciden (ignora mayúsculas)
        int correctas = 0;
        for (int i = 0; i < respuestasCorrectas.size(); i++) {
            if (respuestasCorrectas.get(i)
                    .equalsIgnoreCase(respuestasUsuario.get(i))) {
                correctas++;
            }
        }

        if (correctas < 2) {
            contrasenaView.mostrarMensaje(
                    "Respuestas incorrectas. Debe acertar al menos 2."
            );
            return;
        }

        // Actualiza contraseña en la entidad Usuario
        Usuario usuario = usuarioDAO.buscarPorUsername(username);
        if (usuario == null) {
            contrasenaView.mostrarMensaje("Usuario no registrado en el sistema.");
            return;
        }

        usuario.setContrasenia(nuevaContrasena);
        usuarioDAO.actualizar(usuario);
        contrasenaView.mostrarMensaje("Contraseña actualizada correctamente.");
        contrasenaView.dispose();
    }

    /**
     * Genera una lista de tres índices únicos aleatorios en el rango [0, max).
     *
     * @param max tamaño máximo (exclusive) para los índices
     * @return lista de tres indices únicos
     */
    private List<Integer> obtenerTresIndicesAleatorios(int max) {
        List<Integer> indices = new ArrayList<>();
        Random random = new Random();
        while (indices.size() < 3) {
            int rand = random.nextInt(max);
            if (!indices.contains(rand)) {
                indices.add(rand);
            }
        }
        return indices;
    }

    /**
     * Permite actualizar el DAO de contraseñas en tiempo de ejecución.
     *
     * @param contrasenaDAO nueva implementación de {@link ContrasenaDAO}
     */
    public void setContrasenaDAO(ContrasenaDAO contrasenaDAO) {
        this.contrasenaDAO = contrasenaDAO;
    }
}
