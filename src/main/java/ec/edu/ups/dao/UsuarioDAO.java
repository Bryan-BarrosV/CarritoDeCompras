package ec.edu.ups.dao;

import ec.edu.ups.modelo.Rol;
import ec.edu.ups.modelo.Usuario;

import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad Usuario.
 */
public interface UsuarioDAO {

    /**
     * Autentica a un usuario por su nombre de usuario y contraseña.
     *
     * @param username    Nombre de usuario.
     * @param contrasenia Contraseña del usuario.
     * @return Usuario autenticado o null si no coincide.
     */
    Usuario autenticar(String username, String contrasenia);

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param usuario Objeto usuario a crear.
     */
    void crear(Usuario usuario);

    /**
     * Busca un usuario en el sistema por su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return Usuario encontrado o null si no existe.
     */
    Usuario buscarPorUsername(String username);

    /**
     * Elimina un usuario del sistema por su nombre de usuario.
     *
     * @param username Nombre de usuario.
     */
    void eliminar(String username);

    /**
     * Actualiza la información de un usuario existente.
     *
     * @param usuario Objeto usuario con datos actualizados.
     */
    void actualizar(Usuario usuario);

    /**
     * Lista todos los usuarios registrados en el sistema.
     *
     * @return Lista de usuarios.
     */
    List<Usuario> listarTodos();

    /**
     * Lista los usuarios que tienen un rol específico.
     *
     * @param rol Rol por el cual se filtrará la lista.
     * @return Lista de usuarios con el rol especificado.
     */
    List<Usuario> listarPorRol(Rol rol);
}
