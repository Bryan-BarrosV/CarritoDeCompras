package ec.edu.ups.modelo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

/**
 * Representa un carrito de compras que contiene productos seleccionados por un usuario.
 * Cada carrito tiene una fecha de creación, un código único, y una lista de ítems (productos con cantidades).
 */
public class Carrito {

    /** Porcentaje de IVA aplicado a las compras. */
    private final double IVA = 0.12;

    /** Contador estático para asignar códigos únicos a cada carrito. */
    private static int contador = 1;

    /** Código único del carrito. */
    private int codigo;

    /** Fecha en que se creó el carrito. */
    private GregorianCalendar fechaCreacion;

    /** Lista de ítems del carrito (productos con su respectiva cantidad). */
    private List<ItemCarrito> items;

    /** Usuario dueño del carrito. */
    private Usuario usuario;

    /**
     * Constructor por defecto. Inicializa el carrito con un código único, fecha de creación actual
     * y una lista vacía de ítems.
     */
    public Carrito() {
        codigo = contador++;
        items = new ArrayList<>();
        fechaCreacion = new GregorianCalendar();
        this.usuario = null;
    }

    /**
     * Retorna el usuario dueño del carrito.
     * @return el usuario asignado.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Asigna el usuario al carrito.
     * @param usuario el usuario autenticado.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Retorna el código único del carrito.
     * @return código del carrito.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Permite cambiar el código del carrito manualmente (generalmente no recomendado).
     * @param codigo nuevo código.
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene la fecha de creación del carrito.
     * @return fecha en formato GregorianCalendar.
     */
    public GregorianCalendar getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Asigna una nueva fecha de creación.
     * @param fechaCreacion nueva fecha.
     */
    public void setFechaCreacion(GregorianCalendar fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Agrega un producto con cierta cantidad al carrito.
     * @param producto producto seleccionado.
     * @param cantidad cantidad deseada.
     */
    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemCarrito(producto, cantidad));
    }

    /**
     * Elimina un producto del carrito por su código.
     * @param codigoProducto código del producto a eliminar.
     */
    public void eliminarProducto(int codigoProducto) {
        Iterator<ItemCarrito> it = items.iterator();
        while (it.hasNext()) {
            if (it.next().getProducto().getCodigo() == codigoProducto) {
                it.remove();
                break;
            }
        }
    }

    /**
     * Vacía completamente el carrito (elimina todos los ítems).
     */
    public void vaciarCarrito() {
        items.clear();
    }

    /**
     * Retorna la lista de ítems actualmente en el carrito.
     * @return lista de objetos ItemCarrito.
     */
    public List<ItemCarrito> obtenerItems() {
        return items;
    }

    /**
     * Verifica si el carrito está vacío.
     * @return true si no hay productos, false si contiene al menos uno.
     */
    public boolean estaVacio() {
        return items.isEmpty();
    }

    /**
     * Calcula el subtotal de los productos (precio * cantidad sin IVA).
     * @return valor subtotal.
     */
    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getProducto().getPrecio() * item.getCantidad();
        }
        return subtotal;
    }

    /**
     * Calcula el valor del IVA a partir del subtotal.
     * @return monto de IVA.
     */
    public double calcularIVA() {
        double subtotal = calcularSubtotal();
        return subtotal * IVA;
    }

    /**
     * Calcula el total a pagar (subtotal + IVA).
     * @return monto total.
     */
    public double calcularTotal() {
        return calcularSubtotal() + calcularIVA();
    }

    /**
     * Representación textual del carrito (solo para depuración).
     * @return descripción del carrito.
     */
    @Override
    public String toString() {
        return "Carrito{" +
                "IVA=" + IVA +
                ", codigo=" + codigo +
                ", fechaCreacion=" + fechaCreacion +
                ", items=" + items +
                '}';
    }

    /**
     * Elimina todos los productos del carrito sin afectar su código o fecha.
     */
    public void limpiar() {
        this.items.clear();
    }
}
