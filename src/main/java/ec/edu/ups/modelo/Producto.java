package ec.edu.ups.modelo;

/**
 * Clase que representa un producto disponible en la tienda.
 * Cada producto tiene un código único, un nombre y un precio asociado.
 */
public class Producto {

    /** Código único del producto. */
    private int codigo;

    /** Nombre o descripción del producto. */
    private String nombre;

    /** Precio del producto. */
    private double precio;

    /**
     * Constructor vacío.
     * Requerido para frameworks o procesos de deserialización.
     */
    public Producto() {
    }

    /**
     * Constructor que inicializa todos los campos del producto.
     *
     * @param codigo Código único del producto.
     * @param nombre Nombre o descripción.
     * @param precio Precio del producto.
     */
    public Producto(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Establece el código del producto.
     *
     * @param codigo Código único.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Establece el nombre o descripción del producto.
     *
     * @param nombre Nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio Precio en formato decimal.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el código del producto.
     *
     * @return Código único.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return Nombre o descripción.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return Precio en formato decimal.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Devuelve una representación textual del producto.
     *
     * @return Texto en el formato: "codigo - nombre - $precio"
     */
    @Override
    public String toString() {
        return codigo + " - " + nombre + " - $" + precio;
    }
}
