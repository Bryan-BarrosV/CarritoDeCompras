package ec.edu.ups.dao.impl.Binario;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOBinario implements UsuarioDAO {

    private File archivo;

    public UsuarioDAOBinario(String rutaArchivo) throws IOException {
        this.archivo = new File("C:\\Users\\Bryan\\usuarios.dat");
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                guardarLista(new ArrayList<>());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario autenticar(String username, String contrasenia) {
        List<Usuario> lista = listarTodos();
        for (Usuario u : lista) {
            if (u.getUsername().equals(username) && u.getContrasenia().equals(contrasenia)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void crear(Usuario usuario) {
        List<Usuario> lista = listarTodos();
        lista.add(usuario);
        guardarLista(lista);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        List<Usuario> lista = listarTodos();
        for (Usuario u : lista) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void eliminar(String username) {
        List<Usuario> lista = listarTodos();
        List<Usuario> nuevaLista = new ArrayList<>();

        for (Usuario u : lista) {
            if (!u.getUsername().equals(username)) {
                nuevaLista.add(u);
            }
        }

        guardarLista(nuevaLista);
    }

    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> lista = listarTodos();
        for (int i = 0; i < lista.size(); i++) {
            Usuario u = lista.get(i);
            if (u.getUsername().equals(usuario.getUsername())) {
                lista.set(i, usuario);
                break;
            }
        }
        guardarLista(lista);
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        if (!archivo.exists()) {
            return lista;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            lista = (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer archivo binario de usuarios: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> resultado = new ArrayList<>();
        List<Usuario> lista = listarTodos();
        for (int i = 0; i < lista.size(); i++) {
            Usuario u = lista.get(i);
            if (u.getRol() == rol) {
                resultado.add(u);
            }
        }
        return resultado;
    }

    private void guardarLista(List<Usuario> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios en binario: " + e.getMessage());
        }
    }
}
