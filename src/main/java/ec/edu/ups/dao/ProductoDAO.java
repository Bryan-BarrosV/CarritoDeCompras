package ec.edu.ups.dao;

import ec.edu.ups.modelo.Producto;

import java.util.List;

/**
 * Interfaz que define las operaciones CRUD básicas para la entidad {@link Producto}.
 */
public interface ProductoDAO {

    /**
     * Crea un nuevo producto en el sistema.
     *
     * @param producto El producto a crear.
     */
    void crear(Producto producto);

    /**
     * Busca un producto por su código único.
     *
     * @param codigo Código del producto.
     * @return El producto encontrado o null si no existe.
     */
    Producto buscarPorCodigo(int codigo);

    /**
     * Busca productos que coincidan con el nombre proporcionado.
     *
     * @param nombre Nombre a buscar.
     * @return Lista de productos que coinciden con el nombre.
     */
    List<Producto> buscarPorNombre(String nombre);

    /**
     * Actualiza la información de un producto existente.
     *
     * @param producto Producto con los datos actualizados.
     */
    void actualizar(Producto producto);

    /**
     * Elimina un producto del sistema según su código.
     *
     * @param codigo Código del producto a eliminar.
     */
    void eliminar(int codigo);

    /**
     * Lista todos los productos disponibles en el sistema.
     *
     * @return Lista de todos los productos.
     */
    List<Producto> listarTodos();
}
