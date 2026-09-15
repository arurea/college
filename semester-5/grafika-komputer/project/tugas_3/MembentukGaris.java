import javax.swing.*;
import java.awt.*;

public class MembentukGaris {

    // Metode Pertama - Direct Method
    static void direct(Graphics g){
        int x1 = 100, y1 = 150;
        int x2 = 400, y2 = 150;

        g.setColor(new Color(255, 0, 0));

        double m = (double)(y2 - y1) / (x2 - x1);
        double c = y1 - m * x1;

        for (int x = x1; x <= x2; x++){
            int y = (int)Math.round(m * x + c);
            g.fillRect(x, y, 2, 2);
        }
    }

    // Metode Kedua - DDA (Digital Differential Analyzer) Method
    static void dda(Graphics g){
        int x1 = 250, y1 = 50;
        int x2 = 250, y2 = 250;

        g.setColor(new Color(0, 255, 0));

        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps;

        if (Math.abs(dx) > Math.abs(dy)){
            steps = Math.abs(dx);
        }else{
            steps = Math.abs(dy);
        }
        double x = x1;
        double y = y1;

        double xInc = (double)dx / steps;
        double yInc = (double)dy / steps;

        for (int i = 0; i <= steps; i++){
            g.fillRect(
                (int)Math.round(x),
                (int)Math.round(y),
                2,
                2
            );
            x += xInc;
            y += yInc;
        }
    }

    // Metode Ketiga - Bresenham Method
    static void bresenham(Graphics g){
        int x1 = 400, y1 = 50;
        int x2 = 50, y2 = 250;

        g.setColor(new Color(0, 0, 255));

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int sx = (x1 < x2) ? 1 : -1;
        int sy = (y1 < y2) ? 1 : -1;

        int error = dx - dy;

        int x = x1;
        int y = y1;

        while(true){
            g.fillRect(x, y, 2, 2);

            if (x == x2 && y == y2){
                break;
            }
            int e = 2 * error;

            if (e > -dy){
                error -= dy;
                x += sx;
            }if (e < dx){
                error += dx;
                y += sy;
            }
        }
    }
    public static void main(String[] args){
        // Menampilkan garis dgn metode 1 - Direct
        JFrame window1 = new JFrame("Metode Direct");
        window1.setSize(500, 350);
        window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                direct(g);
            }
        };
        window1.add(panel1);
        window1.setLocation(50, 100);
        window1.setVisible(true);

        // Menampilkan garis dgn metode 2 - DDA
        JFrame window2 = new JFrame("Metode Digital Defferential Analyzer / DDA");
        window2.setSize(500, 350);
        window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel2 = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                dda(g);
            }
        };
        window2.add(panel2);
        window2.setLocation(550, 100);
        window2.setVisible(true);

        // Menampilkan garis dgn metode 3 - Bresenham
        JFrame window3 = new JFrame("Metode Bresenham");
        window3.setSize(500, 350);
        window3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel3 = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                bresenham(g);
            }
        };  
        window3.add(panel3);
        window3.setLocation(300, 450);
        window3.setVisible(true);
    }
}
