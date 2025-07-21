package ec.edu.ups.exception;

/**
 * Excepción personalizada que se lanza cuando una contraseña no cumple con los requisitos de validación.
 */
public class PasswordValidationException extends Exception {

    /**
     * Crea una nueva instancia de PasswordValidationException con un mensaje específico.
     *
     * @param mensaje Mensaje descriptivo del error de validación de la contraseña.
     */
    public PasswordValidationException(String mensaje) {
        super(mensaje);
    }
}
