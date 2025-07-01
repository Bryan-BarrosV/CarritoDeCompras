package ec.edu.ups.modelo;

import java.util.List;

public class Contrasena {

    private String username;
    private List<String> preguntas;
    private List<String> respuestas;

    public Contrasena(String username, List<String> preguntas, List<String> respuestas) {
        this.username = username;
        this.preguntas = preguntas;
        this.respuestas = respuestas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<String> preguntas) {
        this.preguntas = preguntas;
    }

    public List<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<String> respuestas) {
        this.respuestas = respuestas;
    }

    public boolean verificarRespuestas(List<String> respuestasUsuario) {
        if (respuestasUsuario.size() != respuestas.size()) {
            return false;
        }
        int correctas = 0;
        for (int i = 0; i < respuestas.size(); i++) {
            if (respuestas.get(i).equalsIgnoreCase(respuestasUsuario.get(i))) {
                correctas++;
            }
        }
        return correctas >= 2;
    }
}