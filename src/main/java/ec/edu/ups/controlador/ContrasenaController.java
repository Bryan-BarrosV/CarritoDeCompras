package ec.edu.ups.controlador;

import ec.edu.ups.dao.ContrasenaDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Contrasena;
import ec.edu.ups.modelo.Usuario;
import ec.edu.ups.vista.ContrasenaView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class ContrasenaController {

    private final ContrasenaDAO contrasenaDAO;
    private final UsuarioDAO usuarioDAO;
    private final ContrasenaView contrasenaView;

    public ContrasenaController(ContrasenaDAO contrasenaDAO, UsuarioDAO usuarioDAO, ContrasenaView contrasenaView) {
        this.contrasenaDAO = contrasenaDAO;
        this.usuarioDAO = usuarioDAO;
        this.contrasenaView = contrasenaView;

        contrasenaView.getBtnValidarUsuario().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = contrasenaView.getTxtUsername().getText().trim();
                Contrasena contrasena = contrasenaDAO.buscarPorUsername(username);

                if (contrasena == null || contrasena.getPreguntas().size() < 2) {
                    contrasenaView.mostrarMensaje("Usuario no encontrado o no tiene preguntas registradas.");
                    return;
                }

                contrasenaView.setPreguntas(
                        contrasena.getPreguntas().get(0),
                        contrasena.getPreguntas().get(1)
                );
            }
        });

        contrasenaView.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = contrasenaView.getTxtUsername().getText().trim();
                String respuesta1 = contrasenaView.getTxtRespuesta1().getText().trim();
                String respuesta2 = contrasenaView.getTxtRespuesta2().getText().trim();
                String nueva = new String(contrasenaView.getTxtNuevaContrasena().getPassword()).trim();

                if (username.isEmpty() || respuesta1.isEmpty() || respuesta2.isEmpty() || nueva.isEmpty()) {
                    contrasenaView.mostrarMensaje("Debe completar todos los campos.");
                    return;
                }

                Contrasena contrasena = contrasenaDAO.buscarPorUsername(username);
                if (contrasena == null) {
                    contrasenaView.mostrarMensaje("No se encontraron preguntas para este usuario.");
                    return;
                }

                boolean validado = contrasena.verificarRespuestas(Arrays.asList(respuesta1, respuesta2));
                if (!validado) {
                    contrasenaView.mostrarMensaje("Respuestas incorrectas. Intente nuevamente.");
                    return;
                }

                Usuario usuario = usuarioDAO.buscarPorUsername(username);
                if (usuario == null) {
                    contrasenaView.mostrarMensaje("Usuario no registrado en el sistema.");
                    return;
                }

                usuario.setContrasenia(nueva);
                usuarioDAO.actualizar(usuario);
                contrasenaView.mostrarMensaje("Contraseña actualizada correctamente.");
                contrasenaView.dispose();
            }
        });
    }
}
