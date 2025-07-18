package ec.edu.ups.dao.impl.Texto;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOTexto implements UsuarioDAO {

    private File archivo;

    public UsuarioDAOTexto(String rutaArchivo) throws IOException {
        this.archivo = new File("C:\\Users\\Bryan\\usuarios.txt");
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            String linea = usuario.getUsername() + ";" +
                    usuario.getContrasenia() + ";" +
                    usuario.getRol().name() + ";" +
                    usuario.getNombre() + ";" +
                    usuario.getNombreCompleto() + ";" +
                    usuario.getCorreo() + ";" +
                    usuario.getTelefono();
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
        }
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (int i = 0; i < lista.size(); i++) {
                Usuario u = lista.get(i);
                if (!u.getUsername().equals(username)) {
                    String linea = u.getUsername() + ";" +
                            u.getContrasenia() + ";" +
                            u.getRol().name() + ";" +
                            u.getNombre() + ";" +
                            u.getNombreCompleto() + ";" +
                            u.getCorreo() + ";" +
                            u.getTelefono();
                    writer.write(linea);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Usuario usuario) {
        List<Usuario> lista = listarTodos();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (int i = 0; i < lista.size(); i++) {
                Usuario u = lista.get(i);
                if (u.getUsername().equals(usuario.getUsername())) {
                    u = usuario;
                }
                String linea = u.getUsername() + ";" +
                        u.getContrasenia() + ";" +
                        u.getRol().name() + ";" +
                        u.getNombre() + ";" +
                        u.getNombreCompleto() + ";" +
                        u.getCorreo() + ";" +
                        u.getTelefono();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> lista = new ArrayList<>();
        if (!archivo.exists()) {
            return lista;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 7) {
                    Usuario u = new Usuario();
                    u.setUsername(partes[0]);
                    u.setContrasenia(partes[1]);
                    u.setRol(Rol.valueOf(partes[2]));
                    u.setNombre(partes[3]);
                    u.setNombreCompleto(partes[4]);
                    u.setCorreo(partes[5]);
                    u.setTelefono(partes[6]);
                    lista.add(u);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo de usuarios: " + e.getMessage());
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
}
