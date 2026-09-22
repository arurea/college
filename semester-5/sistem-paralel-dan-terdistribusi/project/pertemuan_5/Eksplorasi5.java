public class Eksplorasi5 extends Thread {

    static int[] arrayX = new int[1000000];

    // Menentukan batas kiri dan kanan utk membagi array pada masing-masing thread
    int batasKiri, batasKanan, sum;

    public Eksplorasi5(int batasKiri, int batasKanan) {
        this.batasKiri = batasKiri;
        this.batasKanan = batasKanan;

        // Tugas yang akan dijalankan pada masing-masing thread
    }

    public void run() {
        for (int i = batasKiri; i < batasKanan; i++) {
            synchronized (this) {
                sum += arrayX[i];
            }
        }
        // Menampilkan output, thread i = hasil penjumlahan
        System.out.println(Thread.currentThread().getName() + " = " + sum);

    }

    public static void main(String[] args) {
        int jumlahT = 8;

        // Mengisi array dengan bil random 1-10
        for (int i = 0; i < arrayX.length; i++) {
            arrayX[i] = (int) (Math.random() * 10);
        }
        // Instansiasi thread, jumlahnya mengacu pada variabel jumlahT
        Eksplorasi5[] thread = new Eksplorasi5[jumlahT];

        // Membagi array ke dalam beberapa bagian, cth: 250rb data per bagian
        int bagian = arrayX.length / jumlahT;

        // Membuat dan menjalankan thread
        for (int i = 0; i < jumlahT; i++) {
            int batasKiri = i * bagian;
            int batasKanan = (i + 1) * bagian;

            // Inisialisasi dan start thread
            thread[i] = new Eksplorasi5(batasKiri, batasKanan);
            thread[i].start();
        }
        // Menunggu semua thread selesai
        try {
            for (int i = 0; i < jumlahT; i++) {
                thread[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Menjumlahkan hasil semua thread
        long total = 0;

        for (int i = 0; i < jumlahT; i++) {
            total += thread[i].sum;
        }
        // Print total
        System.out.println("Jumlah sum dari semua thread = " + total);
    }
}
