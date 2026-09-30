
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class PixelDemo{
    public static void main(String[] args) throws Exception{
        int width = 200, height = 200;
        BufferedImage img = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        // Warnai seluruh kanvas dengan putih
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                drawPoint(img, x, y, 255, 255, 255);
            }
        }

        // Gambar titik hitam di tengah kanvas
        drawPoint(img, 100, 100, 0, 0, 0);

        // Baca warna piksel, saya modifikasi utk RGB (200, 50+10.n, 120) n = 8
        int p = img.getRGB(100, 100);
        int red   = 200;
        int green = 130;
        int blue  = 120;

        System.out.println("RGB = (" + red + ", "
                + green + ", " + blue + ")");

        // Konversi RGB ke HSV (HSB di Java)
        float[] hsv = Color.RGBtoHSB(red, green, blue, null);
        System.out.printf("HSV = (%.0f°, %.0f%%, %.0f%%)%n",
                hsv[0] * 360, hsv[1] * 100, hsv[2] * 100);

        // Gambar setigiga dari tiga garis
        drawLine(img, 30, 30, 80, 30, 255, 0, 0); // warna merah
        drawLine(img, 80, 30, 55, 80, 255, 255, 0); // warna kuning
        drawLine(img, 55, 80, 30, 30, 0, 255, 0); // warna hijau

        // Gambar tiga lingkaran sepusat
        drawCircle(img, 150, 100, 15, 255, 255, 0); // warna kuning
        drawCircle(img, 150, 100, 10, 0, 255, 255); // warna cyan
        drawCircle(img, 150, 100, 5, 255, 0, 255); // warna magenta

        // Simpan hasil gambar
        ImageIO.write(img, "png", new File("hasil.png"));
        System.out.println("Gambar disimpan sebagai hasil.png"); // nama file hasil.png
    }
    // Fungsi untuk menggambar sebuah titik
    static void drawPoint(BufferedImage img, int x, int y,
        int r, int g, int b){
        int rgb = (r << 16) | (g << 8) | b;
        img.setRGB(x, y, rgb);
    }

    // Fungsi untuk menggambar garis (bresenham algorithm)
    static void drawLine(BufferedImage img,
            int x0, int y0, int x1, int y1,
            int r, int g, int b){

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;

        int err = dx - dy;

        while(true){
            drawPoint(img, x0, y0, r, g, b);

            if(x0 == x1 && y0 == y1){
                break;
            }

            int e2 = 2 * err;

            if(e2 > -dy){
                err -= dy;
                x0 += sx;
            
            }if(e2 < dx){
                err += dx;
                y0 += sy;
            }
        }
    }

    // Fungsi untuk menggambar lingkaran (bresenham algorithm)
    static void drawCircle(BufferedImage img,
            int xc, int yc, int r,
            int red, int green, int blue){

        int x = 0;
        int y = r;
        int d = 3 - 2 * r;

        while(x <= y){
            drawPoint(img, xc + x, yc + y, red, green, blue);
            drawPoint(img, xc - x, yc + y, red, green, blue);
            drawPoint(img, xc + x, yc - y, red, green, blue);
            drawPoint(img, xc - x, yc - y, red, green, blue);

            drawPoint(img, xc + y, yc + x, red, green, blue);
            drawPoint(img, xc - y, yc + x, red, green, blue);
            drawPoint(img, xc + y, yc - x, red, green, blue);
            drawPoint(img, xc - y, yc - x, red, green, blue);

            if(d < 0){
                d = d + 4 * x + 6;
            }else{
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }
}