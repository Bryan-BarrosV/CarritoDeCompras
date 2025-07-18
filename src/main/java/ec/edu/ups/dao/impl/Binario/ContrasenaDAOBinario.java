package ec.edu.ups.dao.impl.Binario;

import ec.edu.ups.dao.ContrasenaDAO;
import ec.edu.ups.modelo.Contrasena;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContrasenaDAOBinario implements ContrasenaDAO {

    private static final String RUTA_ARCHIVO = "C:\\Users\\Bryan\\contrasenas.bin";
    private final File archivo;

    public ContrasenaDAOBinario() {
        this.archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            try {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void guardar(Contrasena contrasena) {
        List<Contrasena> contrasenas = listarTodas();
        boolean actualizado = false;

        for (int i = 0; i < contrasenas.size(); i++) {
            if (contrasenas.get(i).getUsername().equals(contrasena.getUsername())) {
                contrasenas.set(i, contrasena);
                actualizado = true;
                break;
            }
        }

        if (!actualizado) {
            contrasenas.add(contrasena);
        }

        guardarTodas(contrasenas);
    }

    @Override
    public Contrasena buscarPorUsername(String username) {
        List<Contrasena> contrasenas = listarTodas();
        for (Contrasena c : contrasenas) {
            if (c.getUsername().equals(username)) {
                return c;
            }
        }
        return null;
    }

    private List<Contrasena> listarTodas() {
        if (!archivo.exists() || archivo.length() == 0) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Contrasena>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer archivo binario de contraseñas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void guardarTodas(List<Contrasena> contrasenas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(contrasenas);
        } catch (IOException e) {
            System.err.println("Error al guardar archivo binario de contraseñas: " + e.getMessage());
        }
    }
}
