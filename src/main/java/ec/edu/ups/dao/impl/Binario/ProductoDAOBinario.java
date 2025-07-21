package ec.edu.ups.dao.impl.Binario;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOBinario implements ProductoDAO {

    private List<Producto> listaProductos;
    private File archivo;

    public ProductoDAOBinario(String rutaArchivo) throws IOException {
        this.archivo = new File(rutaArchivo);
        this.listaProductos = new ArrayList<>();

        if (!archivo.exists()) {
            archivo.getParentFile().mkdirs();
            archivo.createNewFile();
        }

        cargarDesdeArchivo();
    }

    private void cargarDesdeArchivo() {
        listaProductos.clear();
        if (archivo.length() == 0) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            listaProductos = (List<Producto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar productos binarios: " + e.getMessage());
        }
    }

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(listaProductos);
        } catch (IOException e) {
            System.err.println("Error al guardar productos binarios: " + e.getMessage());
        }
    }

    @Override
    public void crear(Producto producto) {
        if (buscarPorCodigo(producto.getCodigo()) == null) {
            listaProductos.add(producto);
            guardarEnArchivo();
        }
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto producto : listaProductos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> encontrados = new ArrayList<>();
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                encontrados.add(producto);
            }
        }
        return encontrados;
    }

    @Override
    public void actualizar(Producto productoActualizado) {
        for (int i = 0; i < listaProductos.size(); i++) {
            Producto actual = listaProductos.get(i);
            if (actual.getCodigo() == productoActualizado.getCodigo()) {
                listaProductos.set(i, productoActualizado);
                guardarEnArchivo();
                break;
            }
        }
    }

    @Override
    public void eliminar(int codigo) {
        Producto producto = buscarPorCodigo(codigo);
        if (producto != null) {
            listaProductos.remove(producto);
            guardarEnArchivo();
        }
    }

    @Override
    public List<Producto> listarTodos() {
        return new ArrayList<>(listaProductos);
    }
}
