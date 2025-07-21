package ec.edu.ups.dao.impl.Binario;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAOBinario implements CarritoDAO {

    private List<Carrito> lista;
    private File archivo;

    public CarritoDAOBinario(String ruta) {
        archivo = new File(ruta);
        if (!archivo.exists()) {
            try {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                lista = new ArrayList<>();
                guardarEnArchivo();
            } catch (IOException e) {
                System.err.println("Error al crear archivo de carritos: " + e.getMessage());
            }
        } else {
            lista = cargarDesdeArchivo();
        }
    }

    @Override
    public void crear(Carrito carrito) {
        Carrito existente = buscarPorCodigo(carrito.getCodigo());
        if (existente != null) {
            lista.remove(existente);
        }
        lista.add(carrito);
        guardarEnArchivo();
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : lista) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Carrito carrito) {
        Carrito existente = buscarPorCodigo(carrito.getCodigo());
        if (existente != null) {
            lista.remove(existente);
            lista.add(carrito);
            guardarEnArchivo();
        }
    }

    @Override
    public void eliminar(int codigo) {
        Carrito existente = buscarPorCodigo(codigo);
        if (existente != null) {
            lista.remove(existente);
            guardarEnArchivo();
        }
    }

    @Override
    public List<Carrito> listarTodos() {
        return lista;
    }

    @Override
    public List<Carrito> buscarPorUsuario(Usuario usuario) {
        List<Carrito> resultado = new ArrayList<>();
        for (Carrito c : lista) {
            if (c.getUsuario() != null && c.getUsuario().getUsername().equals(usuario.getUsername())) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Error al guardar carritos: " + e.getMessage());
        }
    }

    private List<Carrito> cargarDesdeArchivo() {
        List<Carrito> lista = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object obj = ois.readObject();
            if (obj != null && obj instanceof List) {
                List aux = (List) obj;
                for (Object elemento : aux) {
                    if (elemento instanceof Carrito) {
                        lista.add((Carrito) elemento);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar carritos: " + e.getMessage());
        }
        return lista;
    }
}
