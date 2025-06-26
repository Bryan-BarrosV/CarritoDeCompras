package ec.edu.ups.modelo;

public class Usuario {
    private String username;
    private String contrasenia;
    private Rol rol;
    private String nombre;

    public Usuario(String username, String contrasenia, Rol rol) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }
    public Usuario() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return username + " - " + rol;
    }
}
