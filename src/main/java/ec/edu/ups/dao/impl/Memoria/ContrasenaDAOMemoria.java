package ec.edu.ups.dao.impl.Memoria;

import ec.edu.ups.dao.ContrasenaDAO;
import ec.edu.ups.modelo.Contrasena;

import java.util.ArrayList;
import java.util.List;

public class ContrasenaDAOMemoria implements ContrasenaDAO {

    private List<Contrasena> contrasenasList;

    public ContrasenaDAOMemoria() {
        contrasenasList = new ArrayList<>();
    }

    @Override
    public void guardar(Contrasena contrasena) {
        Contrasena existente = buscarPorUsername(contrasena.getUsername());
        if (existente != null) {
            contrasenasList.remove(existente);
        }
        contrasenasList.add(contrasena);
    }

    @Override
    public Contrasena buscarPorUsername(String username) {
        for (Contrasena c : contrasenasList) {
            if (c.getUsername().equals(username)) {
                return c;
            }
        }
        return null;
    }
}
