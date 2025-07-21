package ec.edu.ups.modelo;

import java.io.Serializable;

/**
 * Representa una respuesta de seguridad asociada a una pregunta específica.
 */
public class Respuesta implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Pregunta de seguridad relacionada a esta respuesta. */
    private Pregunta pregunta;

    /** Texto de la respuesta proporcionada por el usuario. */
    private String respuesta;

    /**
     * Construye una nueva instancia de Respuesta.
     *
     * @param pregunta  la pregunta de seguridad asociada
     * @param respuesta el texto de la respuesta a la pregunta
     */
    public Respuesta(Pregunta pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    /**
     * Obtiene la pregunta de seguridad asociada.
     *
     * @return la pregunta relacionada
     */
    public Pregunta getPregunta() {
        return pregunta;
    }

    /**
     * Establece la pregunta de seguridad asociada a esta respuesta.
     *
     * @param pregunta nueva pregunta relacionada
     */
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * Obtiene el texto de la respuesta proporcionada.
     *
     * @return el texto de la respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Actualiza el texto de la respuesta.
     *
     * @param respuesta nuevo texto de la respuesta
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
