package ec.edu.ups.dao;

import ec.edu.ups.modelo.Contrasena;

/**
 * Interfaz que define las operaciones para acceder y manipular datos de contraseñas
 * asociadas a usuarios en el sistema.
 */
public interface ContrasenaDAO {

    /**
     * Guarda una nueva instancia de {@link Contrasena} en el sistema.
     *
     * @param contrasena Objeto de tipo {@link Contrasena} que contiene preguntas y respuestas.
     */
    void guardar(Contrasena contrasena);

    /**
     * Busca la información de contraseñas de un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario a buscar.
     * @return Objeto {@link Contrasena} correspondiente al usuario, o null si no existe.
     */
    Contrasena buscarPorUsername(String username);
}
