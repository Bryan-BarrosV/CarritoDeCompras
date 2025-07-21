package ec.edu.ups.exception;

/**
 * Excepción personalizada que se lanza cuando la validación de una cédula ecuatoriana falla.
 */
public class CedulaValidationException extends Exception {

  /**
   * Constructor que permite crear la excepción con un mensaje personalizado.
   *
   * @param mensaje Mensaje descriptivo del error de validación.
   */
  public CedulaValidationException(String mensaje) {
    super(mensaje);
  }
}
