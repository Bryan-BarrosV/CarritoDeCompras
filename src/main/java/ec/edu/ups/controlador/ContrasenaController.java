package ec.edu.ups.controlador;

import ec.edu.ups.dao.ContrasenaDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Contrasena;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.ContrasenaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ContrasenaController {

    private final ContrasenaDAO contrasenaDAO;
    private final UsuarioDAO usuarioDAO;
    private final ContrasenaView contrasenaView;
    private List<Integer> indicesPreguntasSeleccionadas;

    public ContrasenaController(ContrasenaDAO contrasenaDAO, UsuarioDAO usuarioDAO, ContrasenaView contrasenaView) {
        this.contrasenaDAO = contrasenaDAO;
        this.usuarioDAO = usuarioDAO;
        this.contrasenaView = contrasenaView;

        contrasenaView.getBtnValidarUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = contrasenaView.getTxtUsername().getText().trim();
                Contrasena contrasena = contrasenaDAO.buscarPorUsername(username);

                if (contrasena == null || contrasena.getPreguntas().size() < 3) {
                    contrasenaView.mostrarMensaje("Usuario no encontrado o no tiene suficientes preguntas registradas.");
                    return;
                }

                indicesPreguntasSeleccionadas = obtenerTresIndicesAleatorios(contrasena.getPreguntas().size());

                contrasenaView.setPreguntas(
                        contrasena.getPreguntas().get(indicesPreguntasSeleccionadas.get(0)),
                        contrasena.getPreguntas().get(indicesPreguntasSeleccionadas.get(1)),
                        contrasena.getPreguntas().get(indicesPreguntasSeleccionadas.get(2))
                );
            }
        });

        contrasenaView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = contrasenaView.getTxtUsername().getText().trim();
                String nuevaContrasena = new String(contrasenaView.getTxtNuevaContrasena().getPassword()).trim();

                if (username.isEmpty() || nuevaContrasena.isEmpty()) {
                    contrasenaView.mostrarMensaje("Debe completar todos los campos.");
                    return;
                }

                Contrasena contrasena = contrasenaDAO.buscarPorUsername(username);
                if (contrasena == null || indicesPreguntasSeleccionadas == null || indicesPreguntasSeleccionadas.size() < 3) {
                    contrasenaView.mostrarMensaje("No se puede verificar. Intente validar el usuario primero.");
                    return;
                }

                List<String> respuestasUsuario = Arrays.asList(
                        contrasenaView.getTxtRespuesta1().getText().trim(),
                        contrasenaView.getTxtRespuesta2().getText().trim(),
                        contrasenaView.getTxtRespuesta3().getText().trim()
                );

                List<String> respuestasCorrectas = new ArrayList<>();
                for (int idx : indicesPreguntasSeleccionadas) {
                    respuestasCorrectas.add(contrasena.getRespuestas().get(idx));
                }

                int correctas = 0;
                for (int i = 0; i < 3; i++) {
                    if (respuestasCorrectas.get(i).equalsIgnoreCase(respuestasUsuario.get(i))) {
                        correctas++;
                    }
                }

                if (correctas < 2) {
                    contrasenaView.mostrarMensaje("Respuestas incorrectas. Debe acertar al menos 2.");
                    return;
                }

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
        });
    }

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
}
