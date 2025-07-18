package ec.edu.ups.dao.impl.Memoria;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import java.util.ArrayList;
import java.util.List;

public class PreguntaDAOMemoria implements PreguntaDAO {

    private MensajeInternacionalizacionHandler mensajeHandler;

    public PreguntaDAOMemoria(MensajeInternacionalizacionHandler mensajeHandler) {
        this.mensajeHandler = mensajeHandler;
    }

    @Override
    public List<Pregunta> obtenerTodas() {
        List<Pregunta> preguntas = new ArrayList<>();
        preguntas.add(new Pregunta(1, mensajeHandler.get("pregunta.1")));
        preguntas.add(new Pregunta(2, mensajeHandler.get("pregunta.2")));
        preguntas.add(new Pregunta(3, mensajeHandler.get("pregunta.3")));
        preguntas.add(new Pregunta(4, mensajeHandler.get("pregunta.4")));
        preguntas.add(new Pregunta(5, mensajeHandler.get("pregunta.5")));
        preguntas.add(new Pregunta(6, mensajeHandler.get("pregunta.6")));
        preguntas.add(new Pregunta(7, mensajeHandler.get("pregunta.7")));
        preguntas.add(new Pregunta(8, mensajeHandler.get("pregunta.8")));
        preguntas.add(new Pregunta(9, mensajeHandler.get("pregunta.9")));
        preguntas.add(new Pregunta(10, mensajeHandler.get("pregunta.10")));
        return preguntas;
    }
}
