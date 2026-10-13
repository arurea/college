#include <stdio.h>
#include <cuda_runtime.h>
;
#define NUM_BLOCKS 2
#define BLOCK_WIDTH 2 // jumlah thread

__global__ void hello(void){
    printf("Hello dunia, aku adalah thread %d --- dalam blok %d\n", threadIdx.x, blockIdx.x);
}

int main (void){
    hello<<<NUM_BLOCKS, BLOCK_WIDTH>>>();

    cudaDeviceSynchronize();

    printf("Itu aja\n");

    return 0;
}