public class EksplorasiSerial {
    public static void main(String[] args) {
        // ArrayX berjumlah 1jt
        int[] arrayX = new int[1000000];
        long sum = 0;
        // Mengisi arrayX dengan bil random
        for (int i = 0; i < arrayX.length; i++) {
            arrayX[i] = (int) (Math.random() * 1000);
        }
        long start = System.nanoTime();
        // Penjumlahan isi arrayX secara serial
        for (int i = 0; i < arrayX.length; i++) {
            sum+=arrayX[i];
        }
        long finish = System.nanoTime();
        long waktu = (finish-start)/1000000; // Waktu dalam milidetik
        // Output
        System.out.println("Hasil penjumlahan arrayX adalah: "+sum);
        System.out.println("Waktu yang dibutuhkan utk menjumlahkan array secara serial adalah: "+waktu+" ms");
    }  
}