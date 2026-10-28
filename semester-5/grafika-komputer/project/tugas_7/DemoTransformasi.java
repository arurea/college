import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Transformasi2D {

    // Matriks translasi 3x3
    static double[][] translasi(double tx, double ty) {
        return new double[][] {
            {1, 0, tx},
            {0, 1, ty},
            {0, 0, 1}
        };
    }

    // Matriks rotasi 3x3 terhadap titik asal (sudut dalam derajat)
    static double[][] rotasi(double derajat) {
        double rad = Math.toRadians(derajat);
        double c = Math.cos(rad);
        double s = Math.sin(rad);
        return new double[][] {
            {c, -s, 0},
            {s,  c, 0},
            {0,  0, 1}
        };
    }

    // Rotasi terhadap pivot (px, py): T(p) * R * T(-p)
    static double[][] rotasiPivot(double derajat, double px, double py) {
        double[][] m = kali(rotasi(derajat), translasi(-px, -py));
        return kali(translasi(px, py), m);
    }

    // Perkalian dua matriks 3x3: hasil = a * b
    static double[][] kali(double[][] a, double[][] b) {
        double[][] h = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    h[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return h;
    }

    // Menerapkan matriks 3x3 ke titik (x, y) dengan koordinat homogen (x, y, 1)
    static double[] terapkan(double[][] m, double x, double y) {
        double xb = m[0][0] * x + m[0][1] * y + m[0][2];
        double yb = m[1][0] * x + m[1][1] * y + m[1][2];
        return new double[] {xb, yb};
    }

    static void cetak(String label, double[] p) {
        System.out.printf("%-40s (%.2f, %.2f)%n", label, p[0], p[1]);
    }
}

public class DemoTransformasi extends JPanel implements KeyListener {

    // Koordinat model: segitiga dengan pusat di (0,0)
    private final double[][] bentuk = {
        {-50, -30},
        { 50, -30},
        {  0,  60}
    };

    private double posX = 300;   // translasi horizontal
    private double posY = 250;   // translasi vertikal
    private double sudut = 0;    // sudut rotasi (derajat)

    public DemoTransformasi() {
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        // Matriks gabungan: rotasi dulu (di titik asal), lalu translasi
        double[][] m = Transformasi2D.kali(
                Transformasi2D.translasi(posX, posY),
                Transformasi2D.rotasi(sudut));

        int n = bentuk.length;
        int[] xs = new int[n];
        int[] ys = new int[n];
        for (int i = 0; i < n; i++) {
            double[] p = Transformasi2D.terapkan(m, bentuk[i][0], bentuk[i][1]);
            xs[i] = (int) Math.round(p[0]);
            ys[i] = (int) Math.round(p[1]);
        }

        g2.setColor(new Color(66, 133, 244));
        g2.fillPolygon(xs, ys, n);
        g2.setColor(Color.BLACK);
        g2.drawPolygon(xs, ys, n);

        g2.drawString("Panah: translasi | A/D: rotasi | R: reset", 10, 20);
        g2.drawString(String.format("Posisi = (%.0f, %.0f)   Sudut = %.0f derajat",
                                    posX, posY, sudut), 10, 40);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:  posX -= 10; break;
            case KeyEvent.VK_RIGHT: posX += 10; break;
            case KeyEvent.VK_UP:    posY -= 10; break;   // sumbu Y layar ke bawah
            case KeyEvent.VK_DOWN:  posY += 10; break;
            case KeyEvent.VK_A:     sudut -= 5; break;
            case KeyEvent.VK_D:     sudut += 5; break;
            case KeyEvent.VK_R:     posX = 300; posY = 250; sudut = 0; break;
            default: return;
        }
        repaint();
    }

    @Override public void keyReleased(KeyEvent e) { }
    @Override public void keyTyped(KeyEvent e) { }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame("Demo Translasi dan Rotasi 2D");
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.add(new DemoTransformasi());
            f.setSize(600, 500);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}