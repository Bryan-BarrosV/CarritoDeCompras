package ec.edu.ups.exception;

/**
 * Excepción personalizada que representa errores relacionados con la entidad Usuario.
 * Puede ser lanzada durante operaciones como registro, validación, modificación, etc.
 */
public class UsuarioException extends Exception {

    /**
     * Crea una nueva instancia de UsuarioException con un mensaje específico.
     *
     * @param mensaje Mensaje descriptivo del error relacionado con el usuario.
     */
    public UsuarioException(String mensaje) {
        super(mensaje);
    }
}
