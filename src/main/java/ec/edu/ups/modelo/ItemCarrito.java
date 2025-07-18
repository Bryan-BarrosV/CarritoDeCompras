package ec.edu.ups.modelo;

/**
 * Clase que representa un ítem dentro del carrito de compras.
 * Cada ítem contiene un producto y la cantidad deseada del mismo.
 */
public class ItemCarrito {

    /** Producto asociado al ítem. */
    private Producto producto;

    /** Cantidad del producto seleccionada por el usuario. */
    private int cantidad;

    /**
     * Constructor vacío. Necesario para ciertos frameworks o serialización.
     */
    public ItemCarrito() {
    }

    /**
     * Constructor que inicializa el ítem con un producto y una cantidad.
     *
     * @param producto Producto agregado al carrito.
     * @param cantidad Cantidad seleccionada.
     */
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Establece el producto asociado al ítem.
     *
     * @param producto Producto a asignar.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Establece la cantidad del producto.
     *
     * @param cantidad nueva cantidad.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Retorna el producto asociado al ítem.
     *
     * @return producto del ítem.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Retorna la cantidad del producto en el ítem.
     *
     * @return cantidad del producto.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Calcula el subtotal del ítem (precio del producto * cantidad).
     *
     * @return subtotal del ítem.
     */
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    /**
     * Devuelve una representación textual del ítem.
     *
     * @return descripción con formato: [Producto] x [cantidad] = $[subtotal]
     */
    @Override
    public String toString() {
        return producto.toString() + " x " + cantidad + " = $" + getSubtotal();
    }
}
