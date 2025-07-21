package ec.edu.ups.validacion;

import ec.edu.ups.exception.*;

/**
 * Clase utilitaria encargada de realizar validaciones sobre los datos del usuario,
 * como cédula, contraseña y campos obligatorios.
 */
public class ValidadorUsuario {

    /**
     * Valida si una cédula ecuatoriana es válida.
     *
     * @param cedula la cédula a validar (debe tener exactamente 10 dígitos).
     * @throws CedulaValidationException si la cédula no cumple con el formato o no es válida.
     */
    public static void validarCedula(String cedula) throws CedulaValidationException {
        if (cedula == null || !cedula.matches("\\d{10}")) {
            throw new CedulaValidationException("La cédula debe tener exactamente 10 dígitos.");
        }

        int suma = 0;
        for (int i = 0; i < cedula.length() - 1; i++) {
            int digito = Character.getNumericValue(cedula.charAt(i));
            if (i % 2 == 0) {
                digito *= 2;
                if (digito > 9) digito -= 9;
            }
            suma += digito;
        }

        int verificador = (suma % 10 == 0) ? 0 : 10 - (suma % 10);
        int ultimoDigito = Character.getNumericValue(cedula.charAt(9));

        if (verificador != ultimoDigito) {
            throw new CedulaValidationException("La cédula no es válida.");
        }
    }

    /**
     * Valida si una contraseña cumple con los requisitos mínimos de seguridad.
     *
     * @param password la contraseña a validar.
     * @throws PasswordValidationException si la contraseña no cumple con los requisitos:
     *                                     al menos 6 caracteres, una letra mayúscula,
     *                                     una letra minúscula y uno de los caracteres @_ -
     */
    public static void validarPassword(String password) throws PasswordValidationException {
        if (password.length() < 6 ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[@_\\-].*")) {
            throw new PasswordValidationException("La contraseña debe tener al menos 6 caracteres, una mayúscula, una minúscula y uno de los caracteres @_ -");
        }
    }

    /**
     * Verifica que un campo obligatorio no esté vacío.
     *
     * @param campo  el contenido del campo a validar.
     * @param nombre el nombre del campo (para mostrar en el mensaje).
     * @throws UsuarioException si el campo es nulo o está vacío.
     */
    public static void validarCampoObligatorio(String campo, String nombre) throws UsuarioException {
        if (campo == null || campo.trim().isEmpty()) {
            throw new UsuarioException("El campo '" + nombre + "' es obligatorio.");
        }
    }
}
