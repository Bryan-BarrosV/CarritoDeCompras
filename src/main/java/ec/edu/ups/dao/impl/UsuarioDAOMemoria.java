package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOMemoria implements UsuarioDAO {
    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioDAOMemoria() {
        usuarios.add(new Usuario( "admin", "12345", Rol.ADMINISTRADOR));

    }
    @Override
    public Usuario autenticar(String username, String contrasenia) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.getContrasenia().equals(contrasenia)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void crear(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void eliminar(String username) {
        usuarios.removeIf(u -> u.getUsername().equals(username));
    }

    @Override
    public void actualizar(Usuario usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsername().equals(usuario.getUsername())) {
                usuarios.set(i, usuario);
            }
        }
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    @Override
    public List<Usuario> listarPorRol(Rol rol) {
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (u.getRol() == rol) {
                filtrados.add(u);
            }
        }
        return filtrados;
    }
}