package ec.edu.ups.dao.impl.Binario;

import ec.edu.ups.dao.CarritoDAO;
import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAOBinario implements CarritoDAO {

    private static final String RUTA_ARCHIVO = "C:\\Users\\Bryan\\carritos.bin";
    private final File archivo;

    public CarritoDAOBinario(String s) {
        this.archivo = new File(RUTA_ARCHIVO);
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                guardarCarritos(new ArrayList<>());
            }
        } catch (IOException e) {
            System.err.println("Error al crear archivo binario: " + e.getMessage());
        }
    }

    @Override
    public void crear(Carrito carrito) {
        List<Carrito> carritos = listarTodos();
        carritos.add(carrito);
        guardarCarritos(carritos);
    }

    @Override
    public Carrito buscarPorCodigo(int codigo) {
        for (Carrito c : listarTodos()) {
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
        guardarCarritos(carritos);
    }

    @Override
    public void eliminar(int codigo) {
        List<Carrito> carritos = listarTodos();
        carritos.removeIf(c -> c.getCodigo() == codigo);
        guardarCarritos(carritos);
    }

    @Override
    public List<Carrito> listarTodos() {
        if (!archivo.exists() || archivo.length() == 0) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Carrito>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer archivo binario: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Carrito> buscarPorUsuario(Usuario usuario) {
        List<Carrito> resultado = new ArrayList<>();
        for (Carrito carrito : listarTodos()) {
            if (carrito.getUsuario() != null &&
                    carrito.getUsuario().getUsername().equals(usuario.getUsername())) {
                resultado.add(carrito);
            }
        }
        return resultado;
    }

    private void guardarCarritos(List<Carrito> carritos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(carritos);
        } catch (IOException e) {
            System.err.println("Error al guardar archivo binario: " + e.getMessage());
        }
    }
}
