package ec.edu.ups.dao.impl.Texto;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOTexto implements ProductoDAO {

    private List<Producto> listaProductos;
    private File archivo;

    public ProductoDAOTexto(String rutaArchivo) throws IOException {
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
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    double precio = Double.parseDouble(partes[2]);
                    Producto producto = new Producto(codigo, nombre, precio);
                    listaProductos.add(producto);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer productos: " + e.getMessage());
        }
    }

    private void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Producto producto : listaProductos) {
                bw.write(producto.getCodigo() + ";" + producto.getNombre() + ";" + producto.getPrecio());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar productos: " + e.getMessage());
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
        List<Producto> resultados = new ArrayList<>();
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                resultados.add(producto);
            }
        }
        return resultados;
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
