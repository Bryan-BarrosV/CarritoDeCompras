package ec.edu.ups.dao;

import ec.edu.ups.modelo.Respuesta;

import java.util.List;

/**
 * Interfaz que define las operaciones relacionadas con las respuestas de seguridad asociadas a un usuario.
 */
public interface RespuestaDAO {

    /**
     * Guarda las respuestas de seguridad asociadas a un nombre de usuario.
     *
     * @param username   Nombre de usuario al que se le asociarán las respuestas.
     * @param respuestas Lista de respuestas que se desea guardar.
     */
    void guardarRespuestas(String username, List<Respuesta> respuestas);

    /**
     * Obtiene la lista de respuestas almacenadas para un usuario específico.
     *
     * @param username Nombre de usuario cuyas respuestas se desean obtener.
     * @return Lista de respuestas asociadas al usuario.
     */
    List<Respuesta> obtenerRespuestas(String username);

    /**
     * Verifica si las respuestas ingresadas por el usuario coinciden con las respuestas almacenadas.
     *
     * @param username         Nombre de usuario a verificar.
     * @param respuestasUsuario Respuestas proporcionadas por el usuario.
     * @return true si al menos dos respuestas coinciden; false en caso contrario.
     */
    boolean verificarRespuestas(String username, List<Respuesta> respuestasUsuario);
}
