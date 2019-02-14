#include "Naive.h"
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
static bool verify(struct Naive *this, struct MatrixGenerator *mg, int** A, int** B, int **C){
  int** newC;
  newC = mg->multiply(mg,A,B);
  for(int i = 0; i < this->n; i++){
    for(int j = 0; j < this->n; j++){
      if(newC[i][j] != C[i][j]) return false;
    }
  }
  return true;
}
static struct Naive new(long long num){
  return (struct Naive){.n = num,.verify = &verify};
}
const struct NaiveClass Naive={.new=&new};

int main(void){
    FILE *file = fopen("./naive_data.csv","w");
    srand(time(0));
    clock_t start, end;
    for(int i = 4; i <= N; i++){
      struct Naive n = Naive.new(i);
      struct MatrixGenerator mg = MatrixGenerator.new(i);
      start = clock();
      int** A = mg.generate(&mg);
      printf("OK\n");
      int** B = mg.generate(&mg);
      printf("OK\n");
      int** C = mg.multiply(&mg, A, B);
      printf("OK\n");
      if(n.verify(&n,&mg,A,B,C)){
        printf("A and B confirmed to multiply to C.\n");
      }else{
        printf("A and B was deconfirmed!\n");
      }
      end = clock();
      double timeElapsed = (double)(end-start)/CLOCKS_PER_SEC;
      fprintf(file,"%lld,%lf\n",i,timeElapsed);
    }
}
