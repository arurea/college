#include <stdio.h>
#include <cuda_runtime.h>

__global__ void mykernel(void){
    printf("Halo dunia dari kernel!\n");
}
int main (void){
    mykernel<<<1,1>>>();

    cudaDeviceSynchronize();

    printf("Halo dunia!\n");
    return 0;
    
}