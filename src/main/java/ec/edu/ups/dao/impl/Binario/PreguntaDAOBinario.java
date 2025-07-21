package ec.edu.ups.dao.impl.Binario;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDAOBinario implements PreguntaDAO {

    private List<Pregunta> preguntas;
    private File archivo;
    private MensajeInternacionalizacionHandler mensaje;

    public PreguntaDAOBinario(String ruta, MensajeInternacionalizacionHandler mensaje) {
        this.archivo = new File(ruta);
        this.mensaje = mensaje;

        if (!archivo.exists()) {
            try {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                preguntas = new ArrayList<>();
                guardarTodoEnArchivo();
            } catch (IOException e) {
                System.err.println(mensaje.get("error.crear.archivo") + ": " + e.getMessage());
                preguntas = new ArrayList<>();
            }
        } else {
            preguntas = cargarDesdeArchivo();
        }
    }

    @Override
    public List<Pregunta> obtenerTodas() {
        return preguntas;
    }

    private void guardarTodoEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(preguntas);
        } catch (IOException e) {
            System.err.println(mensaje.get("error.guardar.archivo") + ": " + e.getMessage());
        }
    }

    private List<Pregunta> cargarDesdeArchivo() {
        List<Pregunta> lista = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object objeto = ois.readObject();
            lista = (List<Pregunta>) objeto;
        } catch (IOException e) {
            System.err.println(mensaje.get("error.leer.archivo") + ": " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(mensaje.get("error.clase.no.encontrada") + ": " + e.getMessage());
        }

        return lista;
    }
}
