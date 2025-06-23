package ec.edu.ups.modelo;

public class Usuario {
    private int id;
    private String username;
    private String nombre;
    private String contrasenia;
    private Rol rol;

    public Usuario() {}

    public Usuario(int id, String username, String nombre, String contrasenia, Rol rol) {
        this.id = id;
        this.username = username;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    // Constructor sin id (el que falta)
    public Usuario(String username, String nombre, String contrasenia, Rol rol) {
        this.username = username;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public Usuario(String admin, String admin123, Rol rol) {
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", rol=" + rol +
                '}';
    }
}
