package ec.edu.ups.modelo;

/**
 * Clase que representa a un usuario del sistema.
 * Un usuario tiene credenciales de acceso, datos personales y un rol asociado.
 */
public class Usuario {

    /** Nombre de usuario (único para iniciar sesión). */
    private String username;

    /** Contraseña del usuario. */
    private String contrasenia;

    /** Rol que determina los permisos del usuario (ADMINISTRADOR o USUARIO). */
    private Rol rol;

    /** Nombre corto del usuario (alias o nombre visible). */
    private String nombre;

    /** Nombre completo del usuario. */
    private String nombreCompleto;

    /** Correo electrónico del usuario. */
    private String correo;

    /** Número de teléfono del usuario. */
    private String telefono;

    /**
     * Constructor principal con credenciales y rol.
     *
     * @param username nombre de usuario único.
     * @param contrasenia contraseña del usuario.
     * @param rol rol asignado (ADMINISTRADOR o USUARIO).
     */
    public Usuario(String username, String contrasenia, Rol rol) {
        this.username = username;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    /**
     * Constructor vacío para frameworks o inicialización gradual.
     */
    public Usuario() {}

    /** @return nombre de usuario. */
    public String getUsername() { return username; }

    /** @param username nuevo nombre de usuario. */
    public void setUsername(String username) { this.username = username; }

    /** @return contraseña del usuario. */
    public String getContrasenia() { return contrasenia; }

    /** @param contrasenia nueva contraseña. */
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    /** @return rol del usuario (ADMINISTRADOR o USUARIO). */
    public Rol getRol() { return rol; }

    /** @param rol nuevo rol asignado. */
    public void setRol(Rol rol) { this.rol = rol; }

    /** @return nombre corto del usuario. */
    public String getNombre() { return nombre; }

    /** @param nombre nuevo nombre corto. */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** @return nombre completo del usuario. */
    public String getNombreCompleto() { return nombreCompleto; }

    /** @param nombreCompleto nuevo nombre completo. */
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    /** @return correo electrónico del usuario. */
    public String getCorreo() { return correo; }

    /** @param correo nuevo correo electrónico. */
    public void setCorreo(String correo) { this.correo = correo; }

    /** @return número telefónico del usuario. */
    public String getTelefono() { return telefono; }

    /** @param telefono nuevo número telefónico. */
    public void setTelefono(String telefono) { this.telefono = telefono; }

    /**
     * Devuelve una representación textual básica del usuario.
     *
     * @return username y rol.
     */
    @Override
    public String toString() {
        return username + " - " + rol;
    }
}
