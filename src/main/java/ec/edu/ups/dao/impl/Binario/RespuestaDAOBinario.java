package ec.edu.ups.dao.impl.Binario;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Respuesta;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RespuestaDAOBinario implements RespuestaDAO {

    private File archivo;
    private Map<String, List<Respuesta>> mapaRespuestas;

    public RespuestaDAOBinario(String ruta) {
        archivo = new File(ruta);
        if (!archivo.exists()) {
            try {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                mapaRespuestas = new HashMap<>();
                guardarTodoEnArchivo();
            } catch (IOException e) {
                System.err.println("Error al crear archivo de respuestas: " + e.getMessage());
                mapaRespuestas = new HashMap<>();
            }
        } else {
            mapaRespuestas = cargarDesdeArchivo();
        }
    }

    @Override
    public void guardarRespuestas(String username, List<Respuesta> respuestas) {
        mapaRespuestas.put(username, respuestas);
        guardarTodoEnArchivo();
    }

    @Override
    public List<Respuesta> obtenerRespuestas(String username) {
        return mapaRespuestas.get(username);
    }

    @Override
    public boolean verificarRespuestas(String username, List<Respuesta> respuestasUsuario) {
        List<Respuesta> respuestasGuardadas = obtenerRespuestas(username);

        if (respuestasGuardadas == null || respuestasUsuario == null) {
            return false;
        }

        if (respuestasGuardadas.size() != respuestasUsuario.size()) {
            return false;
        }

        int correctas = 0;
        for (int i = 0; i < respuestasGuardadas.size(); i++) {
            Respuesta r1 = respuestasGuardadas.get(i);
            Respuesta r2 = respuestasUsuario.get(i);

            if (r1.getRespuesta().equalsIgnoreCase(r2.getRespuesta())) {
                correctas++;
            }
        }

        return correctas >= 2;
    }

    private void guardarTodoEnArchivo() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(archivo));
            oos.writeObject(mapaRespuestas);
        } catch (IOException e) {
            System.err.println("Error al guardar respuestas: " + e.getMessage());
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar flujo de salida: " + e.getMessage());
                }
            }
        }
    }

    private Map<String, List<Respuesta>> cargarDesdeArchivo() {
        Map<String, List<Respuesta>> mapa = new HashMap<>();
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(archivo));
            Object objetoLeido = ois.readObject();
            mapa = (Map<String, List<Respuesta>>) objetoLeido;
        } catch (EOFException eof) {
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer respuestas: " + e.getMessage());
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    System.err.println("Error al cerrar flujo de entrada: " + e.getMessage());
                }
            }
        }

        return mapa;
    }
}
