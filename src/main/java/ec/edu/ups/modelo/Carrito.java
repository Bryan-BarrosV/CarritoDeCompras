package ec.edu.ups.modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<ItemCarrito> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        this.items.add(new ItemCarrito(producto, cantidad));
    }

    public void vaciarCarrito() {
        this.items.clear();
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public List<ItemCarrito> obtenerItems() {
        return new ArrayList<>(items);
    }
}