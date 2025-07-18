package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class LoginView extends JFrame {

    private JPanel panelPrincipal;
    private JTextField txtUsername;
    private JPasswordField txtContrasenia;
    private JButton btnIniciarSesion;
    private JButton btnRegistrarse;
    private JButton btnOlvidoContrasena;
    private JPanel panelCredenciales;
    private JPanel panelBotones;
    private JPanel panelSecundario;
    private JLabel nombreLabel;
    private JLabel contraseñaLabel;

    private JMenuBar menuBar;
    private JMenu menuIdioma;
    private JMenu menuConfiguracion;
    private JMenuItem itemMemoria;
    private JMenuItem itemTexto;
    private JMenuItem itemBinario;

    private JMenuItem itemEspañol, itemIngles, itemFrances;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public LoginView(MensajeInternacionalizacionHandler mensajeI) {
        this.mensajeInternacionalizacionHandler = mensajeI;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,900);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuIdioma = new JMenu();
        menuBar.add(menuIdioma);

        itemEspañol = new JMenuItem("Español");
        itemIngles = new JMenuItem("English");
        itemFrances = new JMenuItem("Français");

        menuIdioma.add(itemEspañol);
        menuIdioma.add(itemIngles);
        menuIdioma.add(itemFrances);

        menuConfiguracion = new JMenu("Configuración");
        menuBar.add(menuConfiguracion);

        itemMemoria = new JMenuItem("Memoria");
        itemTexto = new JMenuItem("Archivo de Texto");
        itemBinario = new JMenuItem("Archivo Binario");

        menuConfiguracion.add(itemMemoria);
        menuConfiguracion.add(itemTexto);
        menuConfiguracion.add(itemBinario);


        URL usuarioURL = LoginView.class.getClassLoader().getResource("imagenes/usuario.png");
        if (usuarioURL != null) {
            ImageIcon iconoUsuario = new ImageIcon(usuarioURL);
            Image imagenEscalada = iconoUsuario.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            nombreLabel.setIcon(new ImageIcon(imagenEscalada));
            nombreLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
            nombreLabel.setVerticalTextPosition(SwingConstants.CENTER);
        } else {
            System.err.println("No se encontró la imagen usuario.png");
        }

        URL contrasenaURL = LoginView.class.getClassLoader().getResource("imagenes/contraseña.png");
        if (contrasenaURL != null) {
            ImageIcon iconoContrasena = new ImageIcon(contrasenaURL);
            Image imagenEscalada = iconoContrasena.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            contraseñaLabel.setIcon(new ImageIcon(imagenEscalada));
            contraseñaLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
            contraseñaLabel.setVerticalTextPosition(SwingConstants.CENTER);
        } else {
            System.err.println("No se encontró la imagen contraseña.png");
        }

        URL loginURL = LoginView.class.getClassLoader().getResource("imagenes/acceso.png");
        if (loginURL != null) {
            ImageIcon iconoLogin = new ImageIcon(loginURL);
            Image imagenEscalada = iconoLogin.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            btnIniciarSesion.setIcon(new ImageIcon(imagenEscalada));
            btnIniciarSesion.setHorizontalTextPosition(SwingConstants.RIGHT);
            btnIniciarSesion.setVerticalTextPosition(SwingConstants.CENTER);
        } else {
            System.err.println("No se encontró la imagen login.png");
        }

        URL accesoURL = LoginView.class.getClassLoader().getResource("imagenes/login.png");
        if (accesoURL != null) {
            ImageIcon iconoAcceso = new ImageIcon(accesoURL);
            Image imagenEscalada = iconoAcceso.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            btnRegistrarse.setIcon(new ImageIcon(imagenEscalada));
            btnRegistrarse.setHorizontalTextPosition(SwingConstants.RIGHT);
            btnRegistrarse.setVerticalTextPosition(SwingConstants.CENTER);
        } else {
            System.err.println("No se encontró la imagen acceso.png");
        }
        URL OlvidoContrasenaURL = LoginView.class.getClassLoader().getResource("imagenes/olvidosucontraseña.png");
        if (OlvidoContrasenaURL != null) {
            ImageIcon iconoAcceso = new ImageIcon(OlvidoContrasenaURL);
            Image imagenEscalada = iconoAcceso.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            btnOlvidoContrasena.setIcon(new ImageIcon(imagenEscalada));
            btnOlvidoContrasena.setHorizontalTextPosition(SwingConstants.RIGHT);
            btnOlvidoContrasena.setVerticalTextPosition(SwingConstants.CENTER);
        } else {
            System.err.println("No se encontró la imagen acceso.png");
        }

        actualizarTextosLogin();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtContrasenia() {
        return txtContrasenia;
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public JButton getBtnOlvidoContrasena() {
        return btnOlvidoContrasena;
    }

    public JMenuItem getItemIdiomaES() {
        return itemEspañol;
    }

    public JMenuItem getItemIdiomaEN() {
        return itemIngles;
    }

    public JMenuItem getItemIdiomaFR() {
        return itemFrances;
    }


    public void actualizarTextosLogin() {
        menuIdioma.setText(mensajeInternacionalizacionHandler.get("menu.idiomas"));
        nombreLabel.setText(mensajeInternacionalizacionHandler.get("login.usuario"));
        contraseñaLabel.setText(mensajeInternacionalizacionHandler.get("login.contrasena"));
        btnIniciarSesion.setText(mensajeInternacionalizacionHandler.get("login.boton.iniciar"));
        btnRegistrarse.setText(mensajeInternacionalizacionHandler.get("login.boton.registrarse"));
        btnOlvidoContrasena.setText(mensajeInternacionalizacionHandler.get("login.boton.olvido"));
        setTitle(mensajeInternacionalizacionHandler.get("login.titulo"));
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtUsername.setText("");
        txtContrasenia.setText("");
    }

    public JMenuItem getItemMemoria() {
        return itemMemoria;
    }

    public JMenuItem getItemTexto() {
        return itemTexto;
    }

    public JMenuItem getItemBinario() {
        return itemBinario;
    }

}
