package ec.edu.ups.dao.impl.Texto;

import ec.edu.ups.dao.PreguntaDAO;
import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PreguntaDAOTexto implements PreguntaDAO {

    private List<Pregunta> preguntas;
    private File archivo;
    private MensajeInternacionalizacionHandler mensaje;

    public PreguntaDAOTexto(String ruta, MensajeInternacionalizacionHandler mensaje) {
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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (int i = 0; i < preguntas.size(); i++) {
                Pregunta pregunta = preguntas.get(i);
                String linea = pregunta.getId() + "|" + pregunta.getTexto();
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println(mensaje.get("error.guardar.archivo") + ": " + e.getMessage());
        }
    }

    private List<Pregunta> cargarDesdeArchivo() {
        List<Pregunta> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 2) {
                    int id = Integer.parseInt(partes[0]);
                    String texto = partes[1];
                    lista.add(new Pregunta(id, texto));
                }
            }
        } catch (IOException e) {
            System.err.println(mensaje.get("error.leer.archivo") + ": " + e.getMessage());
        }

        return lista;
    }
}
