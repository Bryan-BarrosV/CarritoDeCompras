package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.*;
import ec.edu.ups.dao.impl.Binario.*;
import ec.edu.ups.dao.impl.Memoria.*;
import ec.edu.ups.dao.impl.Texto.*;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;


public class DAOFactory {

    private String modo;

    public DAOFactory(String modo) {
        this.modo = modo;
    }

    public void setModo(String nuevoModo) {
        this.modo = nuevoModo;
    }
    public String getModo() {
        return modo;
    }

    public UsuarioDAO getUsuarioDAO() {
        if (modo.equalsIgnoreCase("MEMORIA")) {
            return new UsuarioDAOMemoria();
        } else if (modo.equalsIgnoreCase("TEXTO")) {
            try {
                return new UsuarioDAOTexto("datos/usuarios.txt");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else if (modo.equalsIgnoreCase("BINARIO")) {
            try {
                return new UsuarioDAOBinario("datos/usuarios.dat");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public ProductoDAO getProductoDAO() {
        if (modo.equalsIgnoreCase("MEMORIA")) {
            return new ProductoDAOMemoria();
        } else if (modo.equalsIgnoreCase("TEXTO")) {
            try {
                return new ProductoDAOTexto("datos/productos.txt");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else if (modo.equalsIgnoreCase("BINARIO")) {
            try {
                return new ProductoDAOBinario("datos/productos.dat");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public CarritoDAO getCarritoDAO() {
        if (modo.equalsIgnoreCase("MEMORIA")) {
            return new CarritoDAOMemoria();
        } else if (modo.equalsIgnoreCase("TEXTO")) {
            try {
                return new CarritoDAOTexto("datos/carritos.txt");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else if (modo.equalsIgnoreCase("BINARIO")) {
            try {
                return new CarritoDAOBinario("datos/carritos.dat");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public ContrasenaDAO getContrasenaDAO() {
        if (modo.equalsIgnoreCase("MEMORIA")) {
            return new ContrasenaDAOMemoria();
        } else if (modo.equalsIgnoreCase("TEXTO")) {
            try {
                return new ContrasenaDAOTexto("datos/contrasenas.txt");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else if (modo.equalsIgnoreCase("BINARIO")) {
            try {
                return new ContrasenaDAOBinario("datos/contrasenas.dat");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public PreguntaDAO getPreguntaDAO(MensajeInternacionalizacionHandler handler) {
        if (modo.equalsIgnoreCase("MEMORIA")) {
            return new PreguntaDAOMemoria(handler);
        } else if (modo.equalsIgnoreCase("TEXTO")) {
            try {
                return new PreguntaDAOTexto("datos/preguntas.txt", handler);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else if (modo.equalsIgnoreCase("BINARIO")) {
            try {
                return new PreguntaDAOBinario("datos/preguntas.dat", handler);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public RespuestaDAO getRespuestaDAO() {
        if (modo.equalsIgnoreCase("MEMORIA")) {
            return new RespuestaDAOMemoria();
        } else if (modo.equalsIgnoreCase("TEXTO")) {
            try {
                return new RespuestaDAOTexto("datos/respuestas.txt");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else if (modo.equalsIgnoreCase("BINARIO")) {
            try {
                return new RespuestaDAOBinario("datos/respuestas.dat");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
