package ec.edu.ups.dao;

import ec.edu.ups.modelo.Contrasena;

public interface ContrasenaDAO {
    void guardar(Contrasena contrasena);
    Contrasena buscarPorUsername(String username);
}
