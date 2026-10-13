#include <stdio.h>
#include <cuda_runtime.h>


__global__ void add(int *a, int *b, int *c){
    int index = threadIdx.x + blockIdx.x * blockDim.x;
    c[index] = a[index] +b[index];
}

void random_ints(int *a, int N){
    for (int i = 0; i < N; i++){
        a[i] = rand() % 1000;
    }
    
}
#define N (8*8) // jumlah blok = 8 pow 2
#define THREADS_PER_BLOCK 8
int main(){
    int *a, *b, *c; // host copies of abc
    int *d_a, *d_b, *d_c; // device copies of abc
    int size = N * sizeof(int);

    // Allocate space for device copies of abc
    cudaMalloc((void **)&d_a, size);
    cudaMalloc((void **)&d_b, size);
    cudaMalloc((void **)&d_c, size);

    //
    a = (int *)malloc(size);
    b = (int *)malloc(size);
    c = (int *)malloc(size);

    // Setup input values
    // a = 2;
    // b = 7;
    random_ints(a, N);
    random_ints(b, N);

    // Copy inputs to device
    cudaMemcpy(d_a, a, size, cudaMemcpyHostToDevice); // tujuan, asal, size, arah (host ke device)
    cudaMemcpy(d_b, b, size, cudaMemcpyHostToDevice);

    // Launch add kernel() on GPU
    add<<<N/THREADS_PER_BLOCK,THREADS_PER_BLOCK>>>(d_a, d_b, d_c); // 1 adalah blok, N adalah thread
    cudaDeviceSynchronize();

    // Copy result back to the host
    cudaMemcpy(c, d_c, size, cudaMemcpyDeviceToHost); // tujuan, asal, size, arah (device ke host)
    for (int i =0 ; i < N; i++){
        printf("Hasil dari index ke- %d adalah: %d\n", i, c[i]);
    }

    // Cleanup
    free(a);
    free(b);
    free(c);
    cudaFree(d_a);
    cudaFree(d_b);
    cudaFree(d_c);
    return 0;
}