package ec.edu.ups.vista;

import ec.edu.ups.util.MensajeInternacionalizacionHandler;

import javax.swing.*;

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
    private JComboBox comboIdioma;
    private JLabel lblIdioma;
    private MensajeInternacionalizacionHandler mensajeInternacionalizacionHandler;

    public LoginView() {
        mensajeInternacionalizacionHandler = MensajeInternacionalizacionHandler.getInstance();
        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane(panelPrincipal);

        pack();

        setLocationRelativeTo(null);
        comboIdioma.addItem("Español");
        comboIdioma.addItem("English");
        comboIdioma.addItem("Français");
        comboIdioma.setSelectedItem("Español");

        comboIdioma.addActionListener(e -> {
            String seleccion = (String) comboIdioma.getSelectedItem();
            switch (seleccion) {
                case "Español":
                    mensajeInternacionalizacionHandler.setLenguaje("es", "EC");
                    break;
                case "English":
                    mensajeInternacionalizacionHandler.setLenguaje("en", "US");
                    break;
                case "Français":
                    mensajeInternacionalizacionHandler.setLenguaje("fr", "FR");
                    break;
            }
            actualizarTextosLogin();
        });

        actualizarTextosLogin();


        setVisible(false);
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

    public JComboBox getComboIdioma() { return comboIdioma;}

    public JLabel getLblIdioma() { return lblIdioma;}

    public void actualizarTextosLogin() {
        nombreLabel.setText(mensajeInternacionalizacionHandler.get("login.usuario"));
        contraseñaLabel.setText(mensajeInternacionalizacionHandler.get("login.contrasena"));
        btnIniciarSesion.setText(mensajeInternacionalizacionHandler.get("login.boton.iniciar"));
        btnRegistrarse.setText(mensajeInternacionalizacionHandler.get("login.boton.registrarse"));
        btnOlvidoContrasena.setText(mensajeInternacionalizacionHandler.get("login.boton.olvido"));
        lblIdioma.setText(mensajeInternacionalizacionHandler.get("login.idioma"));
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
