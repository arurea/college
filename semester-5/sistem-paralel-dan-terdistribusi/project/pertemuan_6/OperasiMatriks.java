
public class OperasiMatriks {
    // Global variabel
    static int ukuranTile = 2; // matriks akan dibagi menjadi sub matriks 2x2
    static int jumlahThread = 4;
    static int ukuranMatriks = 4; // (4x4)

    // // Matriks X dgn ukuran 4x4
    // static int[][] matriksX = {
    // {11, 22, 33, 44},
    // {55, 66, 77, 88},
    // {11, 22, 33, 44},
    // {55, 66, 77, 88}
    // };
    // // Matriks Y dgn ukuran 4x4
    // static int[][] matriksY = {
    // {8, 7, 6, 5},
    // {4, 3, 2, 1},
    // {1, 2, 3, 4},
    // {5, 6, 7, 8}
    // };

    // Matriks X dan Y dengan dimensi disesuaikan dgn var ukuran matriks
    static int[][] matriksX = new int[ukuranMatriks][ukuranMatriks];
    static int[][] matriksY = new int[ukuranMatriks][ukuranMatriks];
    // Dua matriks utk menampung hasil (4x4)
    static int[][] matriksTambah = new int[ukuranMatriks][ukuranMatriks];
    static int[][] matriksKali = new int[ukuranMatriks][ukuranMatriks];

    // Class thread utk menjumlahkan dua buah matriks
    static class Tambah extends Thread {
        public int id;

        public Tambah(int inputID) {
            this.id = inputID;
        }

        @Override
        public void run() {
            for (int i = 0; i < ukuranMatriks; i++) {
                // 1 thread menghandle 1 baris penjumlahan matriks
                matriksTambah[this.id][i] = matriksX[this.id][i] + matriksY[this.id][i]; // id dimulai dari 0-3 (jika 4
                                                                                         // thread)
                System.out.println("Thread ke-" + this.id + " telah selesai menjumlahkan matriks");
            }
        }
    }

    // Class thread utk menjumlahkan dua buah matriks
    static class Kali extends Thread {
        public int id;

        public Kali(int inputID) {
            this.id = inputID;
        }

        @Override
        public void run() {
            // Misal ukuran 4x4, maka jumlah tile per baris adalah 2 tile per baris
            int tilePerBaris = ukuranMatriks / ukuranTile;

            // Menentukan koordinat awal tile utk menghitung perkalian matriks berdasarkan
            // id
            int barisTile = id / tilePerBaris;
            int kolomTile = id % tilePerBaris;

            int barisAwal = barisTile * ukuranTile;
            int kolomAwal = kolomTile * ukuranTile;

            // Tile-> baris-> kolom
            for (int tile = 0; tile < ukuranMatriks; tile += ukuranTile) {
                for (int i = barisAwal; i < barisAwal + ukuranTile; i++) {
                    for (int j = kolomAwal; j < kolomAwal + ukuranTile; j++) {
                        int sum = 0;
                        for (int k = tile; k < tile + ukuranTile; k++) {
                            sum += matriksX[i][k] * matriksY[k][j];
                        }

                        // Meng-assign hasil perkalian dari var sum ke matriks kali di koordinat (i,j)
                        matriksKali[i][j] += sum;
                    }
                }
            }
            System.out.println("Thread ke-" + this.id + "telah selesai mengkalikan matriks");
        }
    }
    // Class thread utk melakukan binary search
    // static class BinaryS extends Thread{
    // -
    // }

    public static void main(String[] args) throws InterruptedException {
        // Mengisi matriks X dan Y dengan angka random 1-20
        for (int i = 0; i < ukuranMatriks; i++) {
            for (int j = 0; j < ukuranMatriks; j++) {
                matriksX[i][j] = (int) (Math.random() * 20);
                matriksY[i][j] = (int) (Math.random() * 20);
            }

        }

        // Buat array thread agar mempermudah proses pembuatan thread
        Thread[] arrayThread = new Thread[jumlahThread];
        // Penambahan 2 matriks
        for (int i = 0; i < jumlahThread; i++) {
            arrayThread[i] = new Tambah(i);
            arrayThread[i].start();
        }

        try {
            for (int i = 0; i < jumlahThread; i++) {
                arrayThread[i].join();
            }
        } catch (InterruptedException e) {

        }
        // Hasil tambah matriks X dan Y
        System.out.println("Hasil penjumlahan matriks= ");
        for (int i = 0; i < ukuranMatriks; i++) {
            for (int j = 0; j < ukuranMatriks; j++) {
                System.out.print(matriksTambah[i][j] + " ");
            }
            System.out.println();
        }

        // Perkalian 2 matriks pakai metode tile (sub matriks)
        for (int i = 0; i < jumlahThread; i++) {
            arrayThread[i] = new Kali(i);
            arrayThread[i].start();
        }

        try {
            for (int i = 0; i < jumlahThread; i++) {
                arrayThread[i].join();
            }
        } catch (InterruptedException e) {

        }
        // Hasil kali matriks X dan Y
        System.out.println("Hasil perkalian matriks= ");
        for (int i = 0; i < ukuranMatriks; i++) {
            for (int j = 0; j < ukuranMatriks; j++) {
                System.out.print(matriksKali[i][j] + " ");
            }
            System.out.println();
        }
        // Binary search (bonus)
        // -
    }
}
