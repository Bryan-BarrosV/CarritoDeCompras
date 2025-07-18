package ec.edu.ups.dao;

import ec.edu.ups.modelo.Respuesta;

import java.util.List;

public interface RespuestaDAO {
    void guardarRespuestas(String username, List<Respuesta> respuestas);
    List<Respuesta> obtenerRespuestas(String username);
    boolean verificarRespuestas(String username, List<Respuesta> respuestasUsuario);
}
