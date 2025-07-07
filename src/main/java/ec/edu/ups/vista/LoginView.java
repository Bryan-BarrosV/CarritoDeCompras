package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JMenuItem itemEspañol, itemIngles, itemFrances;

    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public LoginView(MensajeInternacionalizacionHandler mensajeI) {
        this.mensajeInternacionalizacionHandler = mensajeI;

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        itemEspañol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensajeInternacionalizacionHandler.setLenguaje("es", "EC");
                actualizarTextosLogin();
            }
        });

        itemIngles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensajeInternacionalizacionHandler.setLenguaje("en", "US");
                actualizarTextosLogin();
            }
        });

        itemFrances.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensajeInternacionalizacionHandler.setLenguaje("fr", "FR");
                actualizarTextosLogin();
            }
        });

        btnIniciarSesion.addActionListener(e -> {
            dispose();
        });

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

}
