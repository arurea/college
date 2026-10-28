import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pivot extends JPanel {

    private double pivotX = 300;
    private double pivotY = 250;

    // Posisi pusat persegi
    private double pusatX = 150;
    private double pusatY = 150;

    private double sudut = 0;
    private final double ukuran = 80;

    public Pivot() {
        setBackground(Color.WHITE);
        setFocusable(true);

        // Klik mouse untuk menentukan pivot
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pivotX = e.getX();
                pivotY = e.getY();
                repaint();
                requestFocusInWindow();
            }
        });

        // Tombol A dan D untuk rotasi
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A) {
                    sudut -= 5;
                } else if (e.getKeyCode() == KeyEvent.VK_D) {
                    sudut += 5;
                } else {
                    return;
                }

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Memindahkan pusat persegi relatif terhadap pivot
        double dx = pusatX - pivotX;
        double dy = pusatY - pivotY;

        // Menghitung posisi pusat baru setelah rotasi
        double rad = Math.toRadians(sudut);

        double xBaru = pivotX
                + dx * Math.cos(rad)
                - dy * Math.sin(rad);

        double yBaru = pivotY
                + dx * Math.sin(rad)
                + dy * Math.cos(rad);

        // Menggambar persegi pada posisi baru
        Graphics2D salinan = (Graphics2D) g2.create();

        salinan.translate(xBaru, yBaru);
        salinan.rotate(rad);

        salinan.setColor(new Color(66, 133, 244));
        salinan.fillRect(
                (int) (-ukuran / 2),
                (int) (-ukuran / 2),
                (int) ukuran,
                (int) ukuran
        );

        salinan.setColor(Color.BLACK);
        salinan.drawRect(
                (int) (-ukuran / 2),
                (int) (-ukuran / 2),
                (int) ukuran,
                (int) ukuran
        );

        salinan.dispose();

        // Menggambar titik pivot
        g2.setColor(Color.RED);
        g2.fillOval(
                (int) pivotX - 5,
                (int) pivotY - 5,
                10, 10
        );

        // Informasi
        g2.setColor(Color.BLACK);
        g2.drawString("Klik mouse untuk menentukan pivot", 10, 20);
        g2.drawString("A/D: rotasi berlawanan/searah jarum jam", 10, 40);
        g2.drawString(
                "Sudut rotasi: " + sudut + " derajat",
                10, 60
        );
        g2.drawString(
                "Pivot: (" + (int) pivotX + ", "
                + (int) pivotY + ")",
                10, 80
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rotasi terhadap Pivot");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Pivot());
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}