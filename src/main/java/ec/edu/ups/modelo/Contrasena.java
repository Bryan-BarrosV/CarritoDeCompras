package ec.edu.ups.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representa el objeto de gestión de preguntas y respuestas de seguridad
 * asociadas a un usuario para recuperación de contraseña.
 * Implementa {@link Serializable} para permitir su persistencia.
 */
public class Contrasena implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Nombre de usuario al que pertenecen las preguntas y respuestas. */
    private String username;

    /**
     * Lista de textos de las preguntas de seguridad.
     * Cada elemento corresponde al texto original de un objeto {@link Pregunta}.
     */
    private List<String> preguntas;

    /** Lista de respuestas correctas correspondientes a las preguntas de seguridad. */
    private List<String> respuestas;

    /**
     * Construye un objeto Contrasena para el usuario dado, a partir de una lista
     * de objetos {@link Pregunta} y sus respuestas asociadas.
     *
     * @param username  nombre de usuario dueño de las preguntas
     * @param preguntas lista de objetos Pregunta; se extrae su texto interno
     * @param respuestas lista de respuestas correctas, en el mismo orden que las preguntas
     */
    public Contrasena(String username, List<Pregunta> preguntas, List<String> respuestas) {
        this.username = username;
        this.preguntas = preguntas.stream()
                .map(Pregunta::getTexto)
                .toList();
        this.respuestas = respuestas;
    }

    /**
     * Retorna el nombre de usuario asociado.
     *
     * @return username del usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Actualiza el nombre de usuario asociado.
     *
     * @param username nuevo nombre de usuario
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la lista de preguntas de seguridad en formato de texto.
     *
     * @return lista de textos de preguntas
     */
    public List<String> getPreguntas() {
        return preguntas;
    }

    /**
     * Establece la lista de preguntas de seguridad.
     *
     * @param preguntas lista de textos de preguntas a asignar
     */
    public void setPreguntas(List<String> preguntas) {
        this.preguntas = preguntas;
    }

    /**
     * Obtiene la lista de respuestas correctas.
     *
     * @return lista de respuestas asociadas
     */
    public List<String> getRespuestas() {
        return respuestas;
    }

    /**
     * Establece la lista de respuestas correctas.
     *
     * @param respuestas lista de respuestas a asignar
     */
    public void setRespuestas(List<String> respuestas) {
        this.respuestas = respuestas;
    }

    /**
     * Verifica las respuestas proporcionadas por el usuario contra las respuestas
     * almacenadas. La verificación es insensible a mayúsculas/minúsculas y considera
     * correcto si al menos dos respuestas coinciden.
     *
     * @param respuestasUsuario lista de respuestas ingresadas por el usuario
     * @return {@code true} si el número de respuestas correctas es mayor o igual a 2; {@code false} en caso contrario
     */
    public boolean verificarRespuestas(List<String> respuestasUsuario) {
        if (respuestasUsuario.size() != respuestas.size()) {
            return false;
        }
        int correctas = 0;
        for (int i = 0; i < respuestas.size(); i++) {
            if (respuestas.get(i).equalsIgnoreCase(respuestasUsuario.get(i))) {
                correctas++;
            }
        }
        return correctas >= 2;
    }
}
