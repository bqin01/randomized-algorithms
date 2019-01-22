#include "Freivald.h"
#include "MatrixGenerator.h"
#include<stdio.h>
#include<time.h>
#include <stdlib.h>
#include <stdbool.h>
#define N 10000
/**
 * Conduct the naive O(n^3) algorithm, checking whether or not A x B = C.
 * Precondition: A, B, and C are all n by n square matrices. Furthermore, these
 * must be equal to this->n.
 **/
static bool verify(struct Naive *this, int** A, int** B, int **C){
  int* r = generate01Vector(this,this->n);
  int* Br = squareXVector(B,r,this->n);
  int* Cr = squareXVector(C,r,this->n);
  int* ABr = squareXVector(A,Br,this->n);
  for(int i = 0; i < this->n; i++){
    if(Cr[i] != ABr[i]) return false;
  }
  return true;
}
static struct Naive new(long long num){
  return (struct Naive){.n = num,.verify = &verify};
}
const struct NaiveClass Naive={.new=&new};

int main(void){
    srand(time(0));
    clock_t start, end;
    for(int i = 4; i <= N; i++){
      struct Naive n = Freivald.new(i);
      struct MatrixGenerator mg = MatrixGenerator.new(i);
      start = clock();
      int** A = mg.generate(&mg);
      int** B = mg.generate(&mg);
      int** C = mg.multiply(&mg, A, B);
      if(s.verify(&n,A,B,C)){
        printf("A and B confirmed to multiply to C.\n");
      }else{
        printf("A and B was deconfirmed!\n");
      }
      end = clock();
      printf("This took a total of %f seconds per instance of verify.\n", ((double)(end-start)/CLOCKS_PER_SEC));
    }
}
