#include <stdio.h>
#include <cuda_runtime.h>

__global__ void helloFromGPU() {
    printf("Hello World dari GPU CUDA RTX 3050! (Thread ID: %d)\n", threadIdx.x);
}

int main() {
    printf("Hello World dari CPU (Host)!\n\n");

    // Launch 5 thread
    helloFromGPU<<<1, 5>>>();

    // Tunggu eksekusi GPU selesai
    cudaDeviceSynchronize();

    // Reset device untuk mengalirkan (flush) isi buffer printf ke terminal
    cudaDeviceReset();

    printf("\nProgram CUDA Pertama Berhasil Dieksekusi!\n");
    return 0;
}