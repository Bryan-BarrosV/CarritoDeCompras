package ec.edu.ups.dao.impl;

import ec.edu.ups.dao.*;
import ec.edu.ups.dao.impl.Binario.CarritoDAOBinario;
import ec.edu.ups.dao.impl.Binario.ProductoDAOBinario;
import ec.edu.ups.dao.impl.Binario.UsuarioDAOBinario;
import ec.edu.ups.dao.impl.Memoria.*;
import ec.edu.ups.dao.impl.Texto.CarritoDAOTexto;
import ec.edu.ups.dao.impl.Texto.ContrasenaDAOTexto;
import ec.edu.ups.dao.impl.Texto.ProductoDAOTexto;
import ec.edu.ups.dao.impl.Texto.UsuarioDAOTexto;
import ec.edu.ups.util.MensajeInternacionalizacionHandler;
import ec.edu.ups.dao.impl.Binario.ContrasenaDAOBinario;


import java.io.IOException;


public class DAOFactory {

    private String tipoAlmacenamiento;

    private static final String RUTA_BINARIO = "C:\\Users\\Bryan\\data\\";
    private static final String RUTA_TEXTO = "C:\\Users\\Bryan\\data\\";

    public DAOFactory(String tipoAlmacenamiento) {
        this.tipoAlmacenamiento = tipoAlmacenamiento;
    }

    public ProductoDAO getProductoDAO() {
        if ("BINARIO".equalsIgnoreCase(tipoAlmacenamiento)) {
            return new ProductoDAOBinario(RUTA_BINARIO + "productos.dat");
        } else if ("TEXTO".equalsIgnoreCase(tipoAlmacenamiento)) {
            return new ProductoDAOTexto(RUTA_TEXTO + "productos.txt");
        } else if ("MEMORIA".equalsIgnoreCase(tipoAlmacenamiento)) {
            return new ProductoDAOMemoria();
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento no soportado: " + tipoAlmacenamiento);
        }
    }

    public CarritoDAO getCarritoDAO() throws IOException {
        if ("BINARIO".equalsIgnoreCase(tipoAlmacenamiento)) {
            return new CarritoDAOBinario(RUTA_BINARIO + "carritos.dat");
        } else if ("TEXTO".equalsIgnoreCase(tipoAlmacenamiento)) {
            return new CarritoDAOTexto(RUTA_TEXTO + "carritos.txt");
        } else if ("MEMORIA".equalsIgnoreCase(tipoAlmacenamiento)) {
            return new CarritoDAOMemoria();
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento no soportado: " + tipoAlmacenamiento);
        }
    }

    public UsuarioDAO getUsuarioDAO() throws IOException {
        if ("BINARIO".equalsIgnoreCase(tipoAlmacenamiento)) {
            return new UsuarioDAOBinario(RUTA_BINARIO + "usuarios.dat");
        } else if ("TEXTO".equalsIgnoreCase(tipoAlmacenamiento)) {
            return new UsuarioDAOTexto(RUTA_TEXTO + "usuarios.txt");
        } else if ("MEMORIA".equalsIgnoreCase(tipoAlmacenamiento)) {
            return new UsuarioDAOMemoria();
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento no soportado: " + tipoAlmacenamiento);
        }
    }

    public ContrasenaDAO getContrasenaDAO() throws IOException {
        if ("BINARIO".equalsIgnoreCase(tipoAlmacenamiento)) {
            return new ContrasenaDAOBinario();
        } else if ("TEXTO".equalsIgnoreCase(tipoAlmacenamiento)) {
            return new ContrasenaDAOTexto(RUTA_TEXTO + "contrasenas.txt");
        } else if ("MEMORIA".equalsIgnoreCase(tipoAlmacenamiento)) {
            return new ContrasenaDAOMemoria();
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento no soportado: " + tipoAlmacenamiento);
        }
    }



    public PreguntaDAO getPreguntaDAO(MensajeInternacionalizacionHandler handler) {
        if ("MEMORIA".equalsIgnoreCase(tipoAlmacenamiento)) {
            return new PreguntaDAOMemoria(handler);
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento no soportado para PreguntaDAO: " + tipoAlmacenamiento);
        }
    }

    public RespuestaDAO getRespuestaDAO() {
        if ("MEMORIA".equalsIgnoreCase(tipoAlmacenamiento)) {
            return new RespuestaDAOMemoria();
        } else {
            throw new IllegalArgumentException("Tipo de almacenamiento no soportado para RespuestaDAO: " + tipoAlmacenamiento);
        }
    }
}
