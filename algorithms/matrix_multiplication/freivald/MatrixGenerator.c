#include "MatrixGenerator.h"
#include<stdio.h>
#include<time.h>
#include<stdlib.h>
#include<stdbool.h>
#define K 10000
static int **generate(struct MatrixGenerator *this){
  srand(time(0));
  int **matrix;
  matrix = malloc(sizeof(int*) * this->n);
  for(int i = 0; i < this->n; i++){
    matrix[i] = malloc(sizeof(int*) * this->n);
  }
  for(int i = 0; i < this->n; i++){
    for(int j = 0; j < this->n; j++){
      matrix[i][j] = rand()%10000;
    }
  }
  return matrix;
}
/**
 * Returns the dot product of two n-dimensional vectors.
 * Precondition: The sizes of the arrays pointed by a1 and a2 are the same.
 **/
static int dotProduct(int* a1, int* a2, int n){
  int sum = 0;
  for(int i = 0; i < n; i++){
    sum += a1[i] * a2[i];
  }
  return sum;
}
/**
 * Multiplies the n by n matrix A by an n-vector B, returning the resulting n-vector.
 * Precondition, A has dimensions this->n by this->n and B has length this->n.
 **/
static int* squareXVector(int** A, int* B, int n){
  int* C;
  C = malloc(sizeof(int*) * n);
  for(int i = 0; i < n; i++){
    C[i] = dotProduct(A[i],B,n);
  }
  return C;
}
/**
 * Obtain C, the product of the matrices A and B, and return it.
 * Precondition: Both A and B are this->n by this->n matrices.
 **/
static int **multiply(struct MatrixGenerator *this, int** A, int** B){
  int ** answer = malloc(sizeof(int*) * this->n);
  for(int i = 0; i < this->n; i++){
    answer[i] = malloc(sizeof(int*) * this->n);
  }
  for(int i = 0; i < this->n; i++){
    int* newRow = squareXVector(A, B[i], this->n);
    for(int j = 0; j < this->n; j++){
      answer[j][i] = newRow[j];
    }
  }
  return answer;
}
static struct MatrixGenerator new(long long num){
  return (struct MatrixGenerator){.n = num, .generate = &generate, .multiply = &multiply, .squareXVector = &squareXVector, .dotProduct = &dotProduct};
}
const struct MatrixGeneratorClass MatrixGenerator={.new=&new};
