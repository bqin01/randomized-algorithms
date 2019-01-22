#include "Freivald.h"
#include "MatrixGenerator.h"
#include<stdio.h>
#include<time.h>
#include <stdlib.h>
#include <stdbool.h>
#define N 10000
#define K 10000
/**
 * Generates an n-dimensional 1D vector with values equalling either 0 or 1.
 * This vector will be the randomized "r" employed within the algorithm that both B and C will be multiplied by.
 **/
static int* generate01Vector(struct Freivald *this, long long size){
  srand(time(0));
  int *matrix;
  matrix = malloc(sizeof(int*) * size);
  for(int i = 0; i < size; i++){
    matrix[i] = rand()%2;
  }
  return matrix;
}
/**
 * Conduct Frievald's algorithm, checking whether or not A x B = C.
 * Precondition: A, B, and C are all n by n square matrices. Furthermore, these
 * must be equal to this->n.
 **/
static bool verify(struct Freivald *this, struct MatrixGenerator *mg, int** A, int** B, int **C){
  int* r = generate01Vector(this,this->n);
  int* Br = mg->squareXVector(B,r,this->n);
  int* Cr = mg->squareXVector(C,r,this->n);
  int* ABr = mg->squareXVector(A,Br,this->n);
  for(int i = 0; i < this->n; i++){
    if(Cr[i] != ABr[i]) return false;
  }
  return true;
}
static struct Freivald new(long long num){
  return (struct Freivald){.n = num, .generate01Vector = &generate01Vector, .verify = &verify};
}
const struct FreivaldClass Freivald={.new=&new};

int main(void){
    srand(time(0));
    clock_t start, end;
    for(int i = 4; i <= N; i++){
      struct Freivald s = Freivald.new(i);
      struct MatrixGenerator mg = MatrixGenerator.new(i);
      start = clock();
      int** A = mg.generate(&mg);
      int** B = mg.generate(&mg);
      int** C = mg.multiply(&mg, A, B);
      bool verified = true;
      for(int j = 0; j <= K; j++){
        if(!s.verify(&s,&mg,A,B,C)){
            verified = false;
        }
      }
      end = clock();
      if(verified){
        printf("A and B confirmed to multiply to C.\n");
      }else{
        printf("A and B was deconfirmed!\n");
      }
      printf("This took a total of %f seconds per instance of verify.\n", ((double)(end-start)/CLOCKS_PER_SEC/K));
    }
}
