package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;

/**
 * La clase {@code MiJDesktopPane} extiende {@link JDesktopPane} para crear un escritorio
 * personalizado con una ilustración gráfica que incluye un cielo, césped, edificio con ventanas,
 * árboles y personas con carritos de compras. Este componente es usado como fondo visual principal
 * de la interfaz.
 *
 * <p>Es útil en interfaces visuales enriquecidas para dar una mejor presentación al usuario.</p>
 *
 * @author Bryan Barros
 */
public class MiJDesktopPane extends JDesktopPane {

    /**
     * Constructor por defecto. Establece que el componente es opaco
     * para permitir el dibujo personalizado en el fondo.
     */
    public MiJDesktopPane() {
        setOpaque(true);
    }

    /**
     * Sobrescribe el método {@code paintComponent} para realizar un dibujo gráfico completo
     * del fondo, incluyendo cielo, césped, edificio, árboles y personas con carritos.
     *
     * @param g el contexto gráfico proporcionado por el sistema Swing para dibujar.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        // Cielo
        g2d.setColor(new Color(180, 220, 255));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Césped
        g2d.setColor(new Color(100, 200, 100));
        g2d.fillRect(0, getHeight() - 150, getWidth(), 150);

        // Edificio central
        int edificioX = getWidth() / 2 - 100;
        int edificioY = getHeight() - 300;
        g2d.setColor(new Color(60, 60, 110));
        g2d.fillRect(edificioX, edificioY, 200, 150);

        // Ventanas del edificio
        g2d.setColor(new Color(200, 230, 255));
        for (int i = 0; i < 3; i++) {
            g2d.fillRect(edificioX + 20 + (i * 50), edificioY + 30, 30, 30);
            g2d.fillRect(edificioX + 20 + (i * 50), edificioY + 80, 30, 30);
        }

        // Cartel del edificio
        g2d.setColor(Color.WHITE);
        g2d.fillRoundRect(edificioX + 30, edificioY - 40, 140, 30, 10, 10);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString("BV Corp", edificioX + 60, edificioY - 20);

        // Árboles decorativos
        for (int i = 0; i < 3; i++) {
            int x = 50 + i * 80;
            int y = getHeight() - 180;
            g2d.setColor(new Color(110, 70, 20)); // tronco
            g2d.fillRect(x + 10, y + 30, 10, 40);
            g2d.setColor(new Color(30, 150, 30)); // copa
            g2d.fillOval(x, y, 30, 30);
        }

        // Personas con carritos de compras
        for (int i = 0; i < 3; i++) {
            int x = 200 + i * 100;
            int y = getHeight() - 100;

            g2d.setColor(new Color(255, 220, 180)); // cabeza
            g2d.fillOval(x, y - 20, 15, 15);

            g2d.setColor(new Color(80, 80, 200)); // cuerpo
            g2d.drawLine(x + 7, y - 5, x + 7, y + 20);

            g2d.setColor(Color.GRAY); // carrito
            g2d.drawRect(x + 20, y, 30, 20);
            g2d.drawLine(x + 20, y, x + 15, y - 10); // agarradera
            g2d.setColor(Color.BLACK);
            g2d.fillOval(x + 22, y + 20, 5, 5); // ruedas
            g2d.fillOval(x + 42, y + 20, 5, 5);
        }

        g2d.dispose();
    }
}
