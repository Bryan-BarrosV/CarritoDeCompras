package ec.edu.ups.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MensajeInternacionalizacionHandler {

    private static MensajeInternacionalizacionHandler instancia;

    private ResourceBundle mensajes;

    private MensajeInternacionalizacionHandler(String idioma, String pais) {
        Locale locale = new Locale(idioma, pais);
        mensajes = ResourceBundle.getBundle("mensajes", locale);
    }

    public static MensajeInternacionalizacionHandler getInstance() {
        if (instancia == null) {
            instancia = new MensajeInternacionalizacionHandler("es", "EC");
        }
        return instancia;
    }
    public void setLenguaje(String idioma, String pais) {
        Locale locale = new Locale(idioma, pais);
        mensajes = ResourceBundle.getBundle("mensajes", locale);
    }

    public String get(String clave) {
        return mensajes.getString(clave);
    }
}
