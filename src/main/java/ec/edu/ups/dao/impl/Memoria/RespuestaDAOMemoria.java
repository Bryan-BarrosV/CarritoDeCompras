package ec.edu.ups.dao.impl.Memoria;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Respuesta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RespuestaDAOMemoria implements RespuestaDAO {
    private Map<String, List<Respuesta>> mapaRespuestas = new HashMap<>();

    @Override
    public void guardarRespuestas(String username, List<Respuesta> respuestas) {
        mapaRespuestas.put(username, respuestas);
    }

    @Override
    public List<Respuesta> obtenerRespuestas(String username) {
        return mapaRespuestas.getOrDefault(username, new ArrayList<>());
    }

    @Override
    public boolean verificarRespuestas(String username, List<Respuesta> respuestasUsuario) {
        List<Respuesta> correctas = obtenerRespuestas(username);
        int aciertos = 0;
        for (int i = 0; i < correctas.size(); i++) {
            if (i < respuestasUsuario.size() &&
                    correctas.get(i).getRespuesta().equalsIgnoreCase(respuestasUsuario.get(i).getRespuesta())) {
                aciertos++;
            }
        }
        return aciertos >= 2;
    }
}

