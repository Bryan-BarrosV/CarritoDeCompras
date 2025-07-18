package ec.edu.ups.util;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Clase utilitaria que proporciona métodos para dar formato a valores numéricos y fechas
 * según una configuración regional (locale) específica.
 * <p>
 * Se utiliza principalmente para representar monedas y fechas de forma legible
 * y adaptada al idioma y región del usuario.
 */
public class FormateadorUtils {

    /**
     * Da formato de moneda a un valor numérico según la configuración regional dada.
     *
     * @param cantidad la cantidad numérica a formatear.
     * @param locale   el {@link Locale} que determina el formato (por ejemplo, Locale.US o Locale.FRANCE).
     * @return una cadena con el valor formateado como moneda (ej. "$1,234.56").
     */
    public static String formatearMoneda(double cantidad, Locale locale) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(locale);
        return formatoMoneda.format(cantidad);
    }

    /**
     * Da formato de fecha a un objeto {@link Date} usando el estilo MEDIUM
     * según la configuración regional dada.
     *
     * @param fecha  la fecha a formatear.
     * @param locale el {@link Locale} que determina el formato (por ejemplo, Locale.US o Locale.FRANCE).
     * @return una cadena con la fecha formateada (ej. "17 jul. 2025").
     */
    public static String formatearFecha(Date fecha, Locale locale) {
        DateFormat formato = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        return formato.format(fecha);
    }

}
