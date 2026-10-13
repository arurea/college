#include <stdio.h>

__global__ void helloFromGPU() {
    printf("Hello World dari GPU CUDA! (Thread ID: %d)\n", threadIdx.x);
}

int main() {
    printf("Hello World dari CPU (Host)!\n\n");

    helloFromGPU <<<1, 5 >>> ();

    cudaDeviceSynchronize();

    printf("\nProgram CUDA Pertama Berhasil Dieksekusi!\n");
    return 0;
}