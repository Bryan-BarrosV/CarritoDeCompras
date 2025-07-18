package ec.edu.ups.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Clase encargada de gestionar la internacionalización (i18n) del sistema mediante archivos de propiedades.
 * <p>
 * Utiliza {@link ResourceBundle} y {@link Locale} para obtener mensajes traducidos en función
 * del idioma y país configurados.
 * <p>
 * Esta clase permite cambiar dinámicamente el idioma de la interfaz gráfica.
 */
public class MensajeInternacionalizacionHandler {

    private ResourceBundle mensajes;
    private Locale locale;

    /**
     * Constructor que inicializa la configuración regional y carga el archivo de mensajes correspondiente.
     *
     * @param idioma el código del idioma (ej. "es", "en", "fr").
     * @param pais   el código del país (ej. "EC", "US", "FR").
     */
    public MensajeInternacionalizacionHandler(String idioma, String pais) {
        this.locale = new Locale(idioma, pais);
        mensajes = ResourceBundle.getBundle("mensajes", this.locale);
    }

    /**
     * Cambia el idioma y país actual, actualizando el archivo de recursos.
     *
     * @param idioma el nuevo código de idioma.
     * @param pais   el nuevo código de país.
     */
    public void setLenguaje(String idioma, String pais) {
        this.locale = new Locale(idioma, pais);
        mensajes = ResourceBundle.getBundle("mensajes", this.locale);
    }

    /**
     * Obtiene el mensaje traducido asociado a una clave específica.
     *
     * @param clave la clave del mensaje a obtener (debe estar definida en el archivo .properties).
     * @return el mensaje traducido correspondiente a la clave.
     */
    public String get(String clave) {
        return mensajes.getString(clave);
    }

    /**
     * Retorna el objeto {@link Locale} actual utilizado para la internacionalización.
     *
     * @return el locale activo.
     */
    public Locale getLocale() {
        return this.locale;
    }
}
