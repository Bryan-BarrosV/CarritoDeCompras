package ec.edu.ups.dao.impl.Texto;

import ec.edu.ups.dao.ProductoDAO;
import ec.edu.ups.modelo.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductoDAOTexto implements ProductoDAO {

    private final File archivo;

    public ProductoDAOTexto(String rutaArchivo) {
        this.archivo = new File("C:\\Users\\Bryan\\productos.txt");
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void crear(Producto producto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            String linea = producto.getCodigo() + ";" +
                    producto.getNombre() + ";" +
                    producto.getPrecio();
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Producto buscarPorCodigo(int codigo) {
        for (Producto p : listarTodos()) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> encontrados = new ArrayList<>();
        for (Producto p : listarTodos()) {
            if (p.getNombre().startsWith(nombre)) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    @Override
    public void actualizar(Producto producto) {
        List<Producto> productos = listarTodos();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo() == producto.getCodigo()) {
                productos.set(i, producto);
                break;
            }
        }
        guardarTodos(productos);
    }

    @Override
    public void eliminar(int codigo) {
        List<Producto> productos = listarTodos();
        Iterator<Producto> it = productos.iterator();
        while (it.hasNext()) {
            if (it.next().getCodigo() == codigo) {
                it.remove();
                break;
            }
        }
        guardarTodos(productos);
    }

    @Override
    public List<Producto> listarTodos() {
        List<Producto> productos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    int codigo = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    double precio = Double.parseDouble(partes[2]);
                    productos.add(new Producto(codigo, nombre, precio));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return productos;
    }

    private void guardarTodos(List<Producto> productos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Producto p : productos) {
                String linea = p.getCodigo() + ";" +
                        p.getNombre() + ";" +
                        p.getPrecio();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
