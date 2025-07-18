package ec.edu.ups.validacion;

import ec.edu.ups.exception.*;

public class ValidadorUsuario {

    public static void validarCedula(String cedula) throws CedulaValidationException {
        if (cedula == null || !cedula.matches("\\d{10}")) {
            throw new CedulaValidationException("La cédula debe Ftener exactamente 10 dígitos.");
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

    public static void validarPassword(String password) throws PasswordValidationException {
        if (password.length() < 6 ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[@_\\-].*")) {
            throw new PasswordValidationException("La contraseña debe tener al menos 6 caracteres, una mayúscula, una minúscula y uno de los caracteres @_ -");
        }
    }

    public static void validarCampoObligatorio(String campo, String nombre) throws UsuarioException {
        if (campo == null || campo.trim().isEmpty()) {
            throw new UsuarioException("El campo '" + nombre + "' es obligatorio.");
        }
    }
}
