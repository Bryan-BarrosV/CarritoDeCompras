package ec.edu.ups.modelo;

/**
 * Representa una pregunta de seguridad con un identificador único y su texto asociado.
 */
public class Pregunta {

    /** Identificador único de la pregunta. */
    private int id;

    /** Texto de la pregunta de seguridad. */
    private String texto;

    /**
     * Construye una nueva instancia de Pregunta.
     *
     * @param id     identificador único de la pregunta
     * @param texto  texto de la pregunta de seguridad
     */
    public Pregunta(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    /**
     * Obtiene el identificador de la pregunta.
     *
     * @return el id de la pregunta
     */
    public int getId() {
        return id;
    }

    /**
     * Establece un nuevo identificador para la pregunta.
     *
     * @param id  nuevo id de la pregunta
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el texto de la pregunta de seguridad.
     *
     * @return el texto de la pregunta
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Actualiza el texto de la pregunta de seguridad.
     *
     * @param texto  nuevo texto de la pregunta
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }
}
