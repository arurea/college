import javax.swing.*;
import java.awt.*;

public class MembentukLingkaran {

    // Metode Pertama - Bresenham
    static void bresenham(Graphics g) {

        int xc = 250, yc = 175;
        int r = 125;

        g.setColor(new Color(255, 0, 0));

        int x = 0;
        int y = r;
        int d = 3 - 2 * r;

        while (x <= y) {

            // 8 titik simetris
            g.fillRect(xc + x, yc + y, 2, 2);
            g.fillRect(xc - x, yc + y, 2, 2);
            g.fillRect(xc + x, yc - y, 2, 2);
            g.fillRect(xc - x, yc - y, 2, 2);

            g.fillRect(xc + y, yc + x, 2, 2);
            g.fillRect(xc - y, yc + x, 2, 2);
            g.fillRect(xc + y, yc - x, 2, 2);
            g.fillRect(xc - y, yc - x, 2, 2);

            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }

            x++;
        }
    }

    // Metode Kedua - Midpoint
    static void midpointCircle(Graphics g) {

        int xc = 250, yc = 175;
        int r = 125;

        g.setColor(new Color(0, 0, 255));

        int x = 0;
        int y = r;
        int p = 1 - r;

        while (x <= y) {

            // 8 titik simetris
            g.fillRect(xc + x, yc + y, 2, 2);
            g.fillRect(xc - x, yc + y, 2, 2);
            g.fillRect(xc + x, yc - y, 2, 2);
            g.fillRect(xc - x, yc - y, 2, 2);

            g.fillRect(xc + y, yc + x, 2, 2);
            g.fillRect(xc - y, yc + x, 2, 2);
            g.fillRect(xc + y, yc - x, 2, 2);
            g.fillRect(xc - y, yc - x, 2, 2);

            if (p < 0) {
                x++;
                p = p + 2 * x + 1;
            } else {
                x++;
                y--;
                p = p + 2 * x - 2 * y + 1;
            }
        }
    }

    public static void main(String[] args) {

        // Menampilkan lingkaran dengan metode 1 - Bresenham
        JFrame window1 = new JFrame("Metode Bresenham");
        window1.setSize(500, 350);
        window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                bresenham(g);
            }
        };

        window1.add(panel1);
        window1.setLocation(200, 100);
        window1.setVisible(true);

        // Menampilkan lingkaran dengan metode 2 - Midpoint
        JFrame window2 = new JFrame("Metode Midpoint");
        window2.setSize(500, 350);
        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                midpointCircle(g);
            }
        };

        window2.add(panel2);
        window2.setLocation(750, 100);
        window2.setVisible(true);
    }
}
