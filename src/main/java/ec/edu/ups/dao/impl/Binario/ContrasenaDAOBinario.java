package ec.edu.ups.dao.impl.Binario;

import ec.edu.ups.dao.ContrasenaDAO;
import ec.edu.ups.modelo.Contrasena;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContrasenaDAOBinario implements ContrasenaDAO {

    private List<Contrasena> lista;
    private File archivo;

    public ContrasenaDAOBinario(String rutaArchivo) {
        archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            try {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                lista = new ArrayList<>();
                guardarEnArchivo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            lista = cargarDesdeArchivo();
        }
    }

    @Override
    public void guardar(Contrasena contrasena) {
        for (int i = 0; i < lista.size(); i++) {
            Contrasena c = lista.get(i);
            if (c.getUsername().equals(contrasena.getUsername())) {
                lista.remove(i);
                break;
            }
        }
        lista.add(contrasena);
        guardarEnArchivo();
    }

    @Override
    public Contrasena buscarPorUsername(String username) {
        for (Contrasena c : lista) {
            if (c.getUsername().equals(username)) {
                return c;
            }
        }
        return null;
    }

    private List<Contrasena> cargarDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Contrasena>) ois.readObject();
        } catch (EOFException eof) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void guardarEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
