#include <iostream>
#include <cuda_runtime.h>

__global__ void haloDariGPU() {
    printf("Halo dari GPU thread ke-%d!\n", threadIdx.x);
}

int main() {
    std::cout << "Memanggil fungsi GPU..." << std::endl;
    haloDariGPU<<<1, 5>>>();
    cudaDeviceSynchronize();
    return 0;
}