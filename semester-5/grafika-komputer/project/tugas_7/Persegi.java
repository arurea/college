import javax.swing.*;
import java.awt.*;

public class Persegi extends JPanel {

    private final double ukuran = 80;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        double[] sudut = {0, 45, 90, 135};

        int pusatY = 200;
        int[] pusatX = {100, 230, 360, 490};

        for (int i = 0; i < sudut.length; i++) {

            Graphics2D salinan = (Graphics2D) g2.create();

            // Memindahkan titik asal ke pusat persegi
            salinan.translate(pusatX[i], pusatY);

            // Memutar persegi terhadap pusatnya
            salinan.rotate(Math.toRadians(sudut[i]));

            // Menggambar persegi
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

            // Label sudut rotasi
            g2.setColor(Color.BLACK);
            g2.drawString(
                    sudut[i] + " derajat",
                    pusatX[i] - 30,
                    pusatY + 65
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Rotasi Persegi");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Persegi());
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}