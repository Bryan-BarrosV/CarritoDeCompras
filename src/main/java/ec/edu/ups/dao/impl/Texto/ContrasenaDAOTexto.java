package ec.edu.ups.dao.impl.Texto;

import ec.edu.ups.dao.ContrasenaDAO;
import ec.edu.ups.modelo.Contrasena;
import ec.edu.ups.modelo.Pregunta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContrasenaDAOTexto implements ContrasenaDAO {

    private final File archivo;

    public ContrasenaDAOTexto(String ruta) throws IOException {
        this.archivo = new File("C:\\Users\\Bryan\\contrasenas.txt");
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardar(Contrasena contrasena) {
        List<Contrasena> contrasenas = cargarTodas();
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
        List<Contrasena> contrasenas = cargarTodas();
        for (Contrasena c : contrasenas) {
            if (c.getUsername().equals(username)) {
                return c;
            }
        }
        return null;
    }

    private List<Contrasena> cargarTodas() {
        List<Contrasena> lista = new ArrayList<>();
        if (!archivo.exists()) {
            return lista;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length != 3) continue;

                String username = partes[0];

                List<Pregunta> preguntas = new ArrayList<>();
                String[] preguntasSeparadas = partes[1].split("\\|");
                for (String preguntaStr : preguntasSeparadas) {
                    String[] campos = preguntaStr.split(":");
                    if (campos.length == 2) {
                        int id = Integer.parseInt(campos[0]);
                        String texto = campos[1];
                        preguntas.add(new Pregunta(id, texto));
                    }
                }

                List<String> respuestas = List.of(partes[2].split("\\|"));

                lista.add(new Contrasena(username, preguntas, respuestas));
            }
        } catch (IOException e) {
            System.err.println("Error al cargar contraseñas: " + e.getMessage());
        }

        return lista;
    }

    private void guardarTodas(List<Contrasena> contrasenas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Contrasena c : contrasenas) {
                StringBuilder preguntasStr = new StringBuilder();
                List<String> preguntas = c.getPreguntas();
                for (int i = 0; i < preguntas.size(); i++) {
                    preguntasStr.append(i).append(":").append(preguntas.get(i));
                    if (i < preguntas.size() - 1) {
                        preguntasStr.append("|");
                    }
                }

                List<String> respuestas = c.getRespuestas();
                String respuestasStr = String.join("|", respuestas);

                String linea = c.getUsername() + ";" + preguntasStr + ";" + respuestasStr;
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar contraseñas: " + e.getMessage());
        }
    }
}
