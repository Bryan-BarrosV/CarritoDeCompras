package ec.edu.ups.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MensajeInternacionalizacionHandler {

    private ResourceBundle mensajes;
    private Locale locale;

    public MensajeInternacionalizacionHandler(String idioma, String pais) {
        this.locale = new Locale(idioma, pais);
        mensajes = ResourceBundle.getBundle("mensajes", this.locale);
    }

    public void setLenguaje(String idioma, String pais) {
        this.locale = new Locale(idioma, pais);
        mensajes = ResourceBundle.getBundle("mensajes", this.locale);
    }

    public String get(String clave) {
        return mensajes.getString(clave);
    }

    public Locale getLocale() {
        return this.locale;
    }
}
