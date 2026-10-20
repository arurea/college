#include <stdio.h>
#include <stdlib.h>
#include <cuda_runtime.h>
#include <string.h>

// Menentukan baris dan kolom dari matriks X, Y dan hasil
#define dimensiMatriks 4 // 4x4

// Function utk perkalian matriks (parameter menyimpan pointer)
__global__ void kaliMatriks(int *x, int *y, int *z)
{
    // Indexing arrays untuk baris dan kolom pada matriks (mempermudah proses paralel)
    int baris = threadIdx.y + blockIdx.y * blockDim.y;
    int kolom = threadIdx.x + blockIdx.x * blockDim.x;

    // Mengkalikan matriks dengan metode biasa
    int hasil = 0; // Variabel hasil utk menyimpan hasil perkalian di setiap indexnya
    for (int i = 0; i < dimensiMatriks; i++)
    {
        // Matriks x[baris] * matriks y[kolom]
        hasil += x[baris * dimensiMatriks + i] * y[i * dimensiMatriks + kolom];
    }
    z[baris * dimensiMatriks + kolom] = hasil;
}

int main()
{
    // Inisialisasi variabel-variabel
    int *x, *y, *z;                              // variabel x,y,z pada host
    int *d_x, *d_y, *d_z;                        // variabel x,y,z pada device
    int total = dimensiMatriks * dimensiMatriks; // total elemen yang akan dialokasikan memorinya
    int size = total * sizeof(int);

    // Alokasi memori utk variabel x,y,z (device)
    cudaMalloc((void **)&d_x, size);
    cudaMalloc((void **)&d_y, size);
    cudaMalloc((void **)&d_z, size);

    // Alokasi memori utk variabel x,y,z (host)
    x = (int *)malloc(size);
    y = (int *)malloc(size);
    z = (int *)malloc(size);

    // Mengisi matriks X dan Y
    int X[dimensiMatriks][dimensiMatriks] = {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {8, 7, 6, 5},
        {4, 3, 2, 1}};
    int Y[dimensiMatriks][dimensiMatriks] = {
        {1, 0, 1, 0},
        {0, 1, 0, 1},
        {1, 1, 1, 1},
        {0, 0, 0, 0}};
    int hasil[dimensiMatriks][dimensiMatriks]; // matriks hasil utk menampung hasil perkalian

    // Menyalin matriks X dan Y ke var host
    memcpy(x, X, size);
    memcpy(y, Y, size);

    // Menyalin var host ke var device (CPU->GPU)
    cudaMemcpy(d_x, x, size, cudaMemcpyHostToDevice); // tujuan, asal, size, arah (host ke device)
    cudaMemcpy(d_y, y, size, cudaMemcpyHostToDevice);

    // Pengunaan dim3 untuk menentukan jumlah block dan thread
    dim3 threadPerBlock(dimensiMatriks, dimensiMatriks); // jumlah thread disesuaikan dgn dimensi matriks, jika 4x4, maka akan ada 16 thread per 1 block
    dim3 jumlahBlock(1, 1);                              // hanya menggunakan 1 block

    // Eksekusi di GPU
    kaliMatriks<<<jumlahBlock, threadPerBlock>>>(d_x, d_y, d_z);
    cudaDeviceSynchronize(); // menunggu proses eksekuis di gpu selesai

    // Menyalin kembali var device ke var host (GPU->CPU)
    cudaMemcpy(z, d_z, size, cudaMemcpyDeviceToHost);

    // Print hasil
    printf("Hasil Perkalian Matriks %dx%d:\n", dimensiMatriks, dimensiMatriks);

    for (int baris = 0; baris < dimensiMatriks; baris++)
    {
        for (int kolom = 0; kolom < dimensiMatriks; kolom++)
        {
            printf("%d\t", z[baris * dimensiMatriks + kolom]);
        }
        printf("\n");
    }

    // Cleanup, bersihin memori yg sebelumnya dialokasi
    free(x);
    free(y);
    free(z);
    cudaFree(d_x);
    cudaFree(d_y);
    cudaFree(d_z);
    return 0;
}