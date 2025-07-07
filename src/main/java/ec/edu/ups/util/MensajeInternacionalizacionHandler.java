package ec.edu.ups.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MensajeInternacionalizacionHandler {

    private ResourceBundle mensajes;

    public MensajeInternacionalizacionHandler(String idioma, String pais) {
        Locale locale = new Locale(idioma, pais);
        mensajes = ResourceBundle.getBundle("mensajes", locale);
    }

    public void setLenguaje(String idioma, String pais) {
        Locale locale = new Locale(idioma, pais);
        mensajes = ResourceBundle.getBundle("mensajes", locale);
    }

    public String get(String clave) {
        return mensajes.getString(clave);
    }
}