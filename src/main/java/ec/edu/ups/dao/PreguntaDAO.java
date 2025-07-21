package ec.edu.ups.dao;

import ec.edu.ups.modelo.Pregunta;

import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos relacionadas con las preguntas
 * de seguridad utilizadas en el sistema.
 */
public interface PreguntaDAO {

    /**
     * Retorna una lista con todas las preguntas de seguridad disponibles en el sistema.
     *
     * @return Lista de objetos {@link Pregunta}.
     */
    List<Pregunta> obtenerTodas();
}
