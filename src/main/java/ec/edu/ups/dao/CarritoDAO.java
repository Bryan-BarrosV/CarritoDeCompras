package ec.edu.ups.dao;

import ec.edu.ups.modelo.Carrito;
import ec.edu.ups.modelo.Usuario;

import java.util.List;

/**
 * Interfaz que define las operaciones CRUD para la entidad {@link Carrito}.
 * Proporciona métodos para crear, buscar, actualizar, eliminar y listar carritos,
 * así como para buscar carritos asociados a un usuario específico.
 */
public interface CarritoDAO {

    /**
     * Crea un nuevo carrito en el sistema.
     *
     * @param carrito El carrito a ser creado.
     */
    void crear(Carrito carrito);

    /**
     * Busca un carrito por su código identificador.
     *
     * @param codigo Código del carrito.
     * @return El carrito encontrado o null si no existe.
     */
    Carrito buscarPorCodigo(int codigo);

    /**
     * Actualiza los datos de un carrito existente.
     *
     * @param carrito Carrito con la información actualizada.
     */
    void actualizar(Carrito carrito);

    /**
     * Elimina un carrito del sistema usando su código.
     *
     * @param codigo Código del carrito a eliminar.
     */
    void eliminar(int codigo);

    /**
     * Lista todos los carritos registrados en el sistema.
     *
     * @return Lista de carritos.
     */
    List<Carrito> listarTodos();

    /**
     * Busca los carritos asociados a un usuario específico.
     *
     * @param usuario Usuario del que se quieren obtener los carritos.
     * @return Lista de carritos pertenecientes al usuario.
     */
    List<Carrito> buscarPorUsuario(Usuario usuario);

}
