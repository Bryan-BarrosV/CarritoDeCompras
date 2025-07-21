package ec.edu.ups.dao.impl.Texto;

import ec.edu.ups.dao.RespuestaDAO;
import ec.edu.ups.modelo.Pregunta;
import ec.edu.ups.modelo.Respuesta;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RespuestaDAOTexto implements RespuestaDAO {

    private File archivo;
    private Map<String, List<Respuesta>> mapaRespuestas;

    public RespuestaDAOTexto(String ruta) {
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
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (Map.Entry<String, List<Respuesta>> entrada : mapaRespuestas.entrySet()) {
                String username = entrada.getKey();
                List<Respuesta> lista = entrada.getValue();

                StringBuilder linea = new StringBuilder();
                linea.append(username);
                for (Respuesta r : lista) {
                    linea.append("|").append(r.getPregunta().getTexto()).append(";").append(r.getRespuesta());
                }

                bw.write(linea.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar respuestas: " + e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar BufferedWriter: " + e.getMessage());
            }
        }
    }

    private Map<String, List<Respuesta>> cargarDesdeArchivo() {
        Map<String, List<Respuesta>> mapa = new HashMap<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length >= 2) {
                    String username = partes[0];
                    List<Respuesta> listaRespuestas = new ArrayList<>();

                    for (int i = 1; i < partes.length; i++) {
                        String[] preguntaRespuesta = partes[i].split(";");
                        if (preguntaRespuesta.length == 2) {
                            Pregunta pregunta = new Pregunta(0, preguntaRespuesta[0]);
                            String respuestaTexto = preguntaRespuesta[1];
                            Respuesta respuesta = new Respuesta(pregunta, respuestaTexto);
                            listaRespuestas.add(respuesta);
                        }
                    }

                    mapa.put(username, listaRespuestas);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo de respuestas: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar BufferedReader: " + e.getMessage());
            }
        }

        return mapa;
    }
}
