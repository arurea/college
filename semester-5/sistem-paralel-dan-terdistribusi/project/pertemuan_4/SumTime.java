public class SumTime {
    public static void main(String[] args) {
        // Inisialisasi array
        int[] arrayX = new int[1000];

        // Mengisi array dengan bilangan random
        for (int i = 0; i < arrayX.length; i++) {
            arrayX[i] = (int) (Math.random() * 100);
        }
        // Start, mulai menghitung waktu awal
        long start = System.nanoTime();
        // long start = System.currentTimeMillis();

        // Menjumlahkan isi array
        int sum = 0;
        for (int i = 0; i < arrayX.length; i++) {
            sum += arrayX[i];
        }
        // Stop, menghitung waktu akhir
        long stop = System.nanoTime();
        // long stop = System.currentTimeMillis();

        // Menghitung waktu
        long time = stop - start;
        System.out.println("Hasil sum= " + sum);
        System.out.println("Waktu= " + time);
    }
}