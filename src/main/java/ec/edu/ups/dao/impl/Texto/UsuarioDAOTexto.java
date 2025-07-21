package ec.edu.ups.dao.impl.Texto;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOTexto implements UsuarioDAO {

    private List<Usuario> listaUsuarios;
    private File archivo;

    public UsuarioDAOTexto(String rutaArchivo) throws IOException {
        this.archivo = new File(rutaArchivo);
        this.listaUsuarios = new ArrayList<>();

        if (!archivo.exists()) {
            archivo.getParentFile().mkdirs();
            archivo.createNewFile();
        }

        cargarDesdeArchivo();
    }

    private void cargarDesdeArchivo() {
        listaUsuarios.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 3) {
                    Usuario usuario = new Usuario();
                    usuario.setUsername(partes[0]);
                    usuario.setContrasenia(partes[1]);
                    usuario.setRol(Rol.valueOf(partes[2]));
                    listaUsuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer usuarios: " + e.getMessage());
        }
    }

    private void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Usuario u : listaUsuarios) {
                bw.write(u.getUsername() + ";" + u.getContrasenia() + ";" + u.getRol().name());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getUsername().equals(username) && usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void crear(Usuario usuario) {
        if (buscarPorUsername(usuario.getUsername()) == null) {
            listaUsuarios.add(usuario);
            guardarEnArchivo();
        }
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void eliminar(String username) {
        Usuario encontrado = buscarPorUsername(username);
        if (encontrado != null) {
            listaUsuarios.remove(encontrado);
            guardarEnArchivo();
        }
    }

    @Override
    public void actualizar(Usuario usuarioActualizado) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario actual = listaUsuarios.get(i);
            if (actual.getUsername().equals(usuarioActualizado.getUsername())) {
                listaUsuarios.set(i, usuarioActualizado);
                guardarEnArchivo();
                break;
            }
        }
    }

    @Override
    public List<Usuario> listarTodos() {
        return new ArrayList<>(listaUsuarios);
    }

    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : listaUsuarios) {
            if (u.getRol().equals(rol)) {
                filtrados.add(u);
            }
        }
        return filtrados;
    }
}
