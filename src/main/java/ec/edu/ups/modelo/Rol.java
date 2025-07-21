package ec.edu.ups.modelo;

/**
 * Enum que representa los roles disponibles dentro del sistema.
 *
 * <ul>
 *   <li><b>ADMINISTRADOR</b>: Tiene acceso completo al sistema, puede gestionar usuarios, productos y carritos.</li>
 *   <li><b>USUARIO</b>: Tiene acceso limitado, generalmente a funcionalidades de compra y visualización.</li>
 * </ul>
 */
public enum Rol {
    /** Rol con privilegios completos. */
    ADMINISTRADOR,

    /** Rol limitado al uso normal del sistema. */
    USUARIO
}
