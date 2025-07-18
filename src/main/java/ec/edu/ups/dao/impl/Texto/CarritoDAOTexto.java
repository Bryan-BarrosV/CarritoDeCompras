package ec.edu.ups.dao.impl.Texto;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.*;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CarritoDAOTexto implements CarritoDAO {

    private static final String RUTA_ARCHIVO = "C:\\Users\\Bryan\\carritos.txt";
    private final File archivo;

    public CarritoDAOTexto(String s) throws IOException {
        this.archivo = new File(RUTA_ARCHIVO);
        archivo.getParentFile().mkdirs();
        archivo.createNewFile();
    }

    @Override
    public void crear(Carrito carrito) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            writer.write(formatoGuardar(carrito));
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar carrito: " + e.getMessage());
        }
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        List<Carrito> carritos = listarTodos();
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Carrito carrito) {
        List<Carrito> carritos = listarTodos();
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
        guardarTodos(carritos);
    }

    @Override
    public void eliminar(int codigo) {
        List<Carrito> carritos = listarTodos();
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarTodos(carritos);
    }

    @Override
    public List<Carrito> listarTodos() {
        List<Carrito> carritos = new ArrayList<>();
        if (!archivo.exists()) {
            return carritos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                Carrito carrito = leerDesdeTexto(linea);
                if (carrito != null) {
                    carritos.add(carrito);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
        }
        return carritos;
    }

    @Override
    public List<Carrito> buscarPorUsuario(Usuario usuario) {
        List<Carrito> todos = listarTodos();
        List<Carrito> encontrados = new ArrayList<>();
        for (Carrito c : todos) {
            if (c.getUsuario() != null && c.getUsuario().getUsername().equals(usuario.getUsername())) {
                encontrados.add(c);
            }
        }
        return encontrados;
    }

    private void guardarTodos(List<Carrito> carritos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Carrito c : carritos) {
                writer.write(formatoGuardar(c));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar todos los carritos: " + e.getMessage());
        }
    }

    private String formatoGuardar(Carrito carrito) {
        StringBuilder sb = new StringBuilder();
        sb.append(carrito.getCodigo()).append(";");
        sb.append(carrito.getFechaCreacion().getTimeInMillis()).append(";");
        sb.append(carrito.getUsuario() != null ? carrito.getUsuario().getUsername() : "").append(";");

        List<ItemCarrito> items = carrito.obtenerItems();
        for (int i = 0; i < items.size(); i++) {
            ItemCarrito item = items.get(i);
            sb.append(item.getProducto().getCodigo()).append(",");
            sb.append(item.getProducto().getNombre()).append(",");
            sb.append(item.getProducto().getPrecio()).append(",");
            sb.append(item.getCantidad());
            if (i < items.size() - 1) {
                sb.append("|");
            }
        }

        return sb.toString();
    }

    private Carrito leerDesdeTexto(String linea) {
        try {
            String[] partes = linea.split(";");
            if (partes.length < 4) return null;

            int codigo = Integer.parseInt(partes[0]);
            long millis = Long.parseLong(partes[1]);
            String username = partes[2];
            String itemsTexto = partes[3];

            Carrito carrito = new Carrito();
            carrito.setCodigo(codigo);
            carrito.setFechaCreacion(new GregorianCalendar());
            carrito.getFechaCreacion().setTimeInMillis(millis);

            if (!username.isEmpty()) {
                Usuario usuario = new Usuario();
                usuario.setUsername(username);
                carrito.setUsuario(usuario);
            }

            String[] items = itemsTexto.split("\\|");
            for (String itemStr : items) {
                String[] datos = itemStr.split(",");
                int codProd = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double precio = Double.parseDouble(datos[2]);
                int cantidad = Integer.parseInt(datos[3]);

                Producto producto = new Producto(codProd, nombre, precio);
                carrito.agregarProducto(producto, cantidad);
            }

            return carrito;
        } catch (Exception e) {
            System.err.println("Error al leer línea: " + linea + " -> " + e.getMessage());
            return null;
        }
    }
}
