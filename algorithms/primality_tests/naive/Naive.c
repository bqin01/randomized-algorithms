#include "Naive.h"
#include<stdio.h>
#include<time.h>
#include<math.h>
#include <stdlib.h>
#include <stdbool.h>
#define N 1e63
#define K 10000
int multi_counter = 0;
static bool checkPrime(struct Naive *this){
  long long num = this->n;
  if(num == 1) return false;
  if(num == 2) return true;
  long long sqrtN = (long long)sqrt((double)num);
  for(long long i = 2; i < sqrtN; i++){
    if(num/i*i == num){
      return false;
    }
  }
  return true;
}
static struct Naive new(long long num){
  return (struct Naive){.n = num, .checkPrime = &checkPrime};
}
const struct NaiveClass Naive={.new=&new};

int main(void){
  FILE *file = fopen("./naive_data.csv","w");
  srand(time(0));
  clock_t start, end;
  for(long long i = 10; i <= N; i++){
    struct Naive n = Naive.new(i);
    start = clock();
    if(checkPrime(&n)){
      for(int j = 0; j < 100; j++) checkPrime(&n);
      end = clock();
      double timeElapsed = (double)(end-start)/CLOCKS_PER_SEC/100;
      fprintf(file,"%lld,%lf\n",i,timeElapsed);
      i += i;
    }
  }
}
