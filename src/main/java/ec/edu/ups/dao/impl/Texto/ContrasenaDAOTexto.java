package ec.edu.ups.dao.impl.Texto;

import ec.edu.ups.dao.ContrasenaDAO;
import ec.edu.ups.modelo.Contrasena;
import ec.edu.ups.modelo.Pregunta;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContrasenaDAOTexto implements ContrasenaDAO {

    private List<Contrasena> lista;
    private File archivo;

    public ContrasenaDAOTexto(String ruta) {
        archivo = new File(ruta);
        if (!archivo.exists()) {
            try {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();
                lista = new ArrayList<>();
                guardarTodoEnArchivo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            lista = cargarDesdeArchivo();
        }
    }

    @Override
    public void guardar(Contrasena contrasena) {
        // Eliminar si ya existe
        for (int i = 0; i < lista.size(); i++) {
            Contrasena existente = lista.get(i);
            if (existente.getUsername().equals(contrasena.getUsername())) {
                lista.remove(i);
                break;
            }
        }
        lista.add(contrasena);
        guardarTodoEnArchivo();
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

    private void guardarTodoEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (Contrasena c : lista) {
                String linea = c.getUsername() + "|"
                        + String.join(",", c.getPreguntas()) + "|"
                        + String.join(",", c.getRespuestas());
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Contrasena> cargarDesdeArchivo() {
        List<Contrasena> tempLista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 3) {
                    String username = partes[0];
                    String[] preguntasTexto = partes[1].split(",");
                    List<Pregunta> listaPreguntas = new ArrayList<>();
                    for (String texto : preguntasTexto) {
                        listaPreguntas.add(new Pregunta(0, texto));
                    }
                    List<String> respuestas = Arrays.asList(partes[2].split(","));
                    Contrasena contrasena = new Contrasena(username, listaPreguntas, respuestas);
                    tempLista.add(contrasena);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return tempLista;
    }


}
