package ec.edu.ups.dao.impl.Texto;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.ItemCarrito;
import ec.edu.ups.modelo.Producto;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CarritoDAOTexto implements CarritoDAO {

    private List<Carrito> carritos;
    private File archivo;

    public CarritoDAOTexto(String ruta) {
        archivo = new File(ruta);
        if (!archivo.exists()) {
            try {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                carritos = new ArrayList<>();
                guardarTodo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            carritos = cargarDesdeArchivo();
        }
    }

    @Override
    public void crear(Carrito carrito) {
        carritos.add(carrito);
        guardarTodo();
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : carritos) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Carrito carrito) {
        for (int i = 0; i < carritos.size(); i++) {
            if (carritos.get(i).getCodigo() == carrito.getCodigo()) {
                carritos.set(i, carrito);
                break;
            }
        }
        guardarTodo();
    }

    @Override
    public void eliminar(int codigo) {
        Carrito carrito = buscarPorCodigo(codigo);
        if (carrito != null) {
            carritos.remove(carrito);
            guardarTodo();
        }
    }

    @Override
    public List<Carrito> listarTodos() {
        return carritos;
    }

    @Override
    public List<Carrito> buscarPorUsuario(Usuario usuario) {
        List<Carrito> resultado = new ArrayList<>();
        for (Carrito c : carritos) {
            if (c.getUsuario() != null && c.getUsuario().getUsername().equals(usuario.getUsername())) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    private void guardarTodo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Carrito c : carritos) {
                StringBuilder itemsStr = new StringBuilder();
                for (ItemCarrito item : c.obtenerItems()) {
                    itemsStr.append(item.getProducto().getCodigo()).append(",").append(item.getCantidad()).append(";");
                }
                if (itemsStr.length() > 0) {
                    itemsStr.deleteCharAt(itemsStr.length() - 1);
                }

                writer.write(
                        c.getCodigo() + "|" +
                                c.getFechaCreacion().getTimeInMillis() + "|" +
                                (c.getUsuario() != null ? c.getUsuario().getUsername() : "null") + "|" +
                                itemsStr.toString()
                );
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Carrito> cargarDesdeArchivo() {
        List<Carrito> lista = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length >= 4) {
                    int codigo = Integer.parseInt(partes[0]);
                    long millis = Long.parseLong(partes[1]);
                    String username = partes[2];
                    String[] itemsData = partes[3].split(";");

                    Carrito carrito = new Carrito();
                    carrito.setCodigo(codigo);

                    GregorianCalendar fecha = new GregorianCalendar();
                    fecha.setTimeInMillis(millis);
                    carrito.setFechaCreacion(fecha);

                    if (!username.equals("null")) {
                        Usuario usuario = new Usuario();
                        usuario.setUsername(username);
                        carrito.setUsuario(usuario);
                    }

                    for (int i = 0; i < itemsData.length; i++) {
                        String[] datos = itemsData[i].split(",");
                        if (datos.length == 2) {
                            int codigoProducto = Integer.parseInt(datos[0]);
                            int cantidad = Integer.parseInt(datos[1]);

                            Producto producto = new Producto();
                            producto.setCodigo(codigoProducto);

                            ItemCarrito item = new ItemCarrito();
                            item.setProducto(producto);
                            item.setCantidad(cantidad);

                            carrito.agregarProducto(producto, cantidad);
                        }
                    }

                    lista.add(carrito);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
