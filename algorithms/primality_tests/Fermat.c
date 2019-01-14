#include "Fermat.h"
#include<stdio.h>
#include<time.h>
#include <stdlib.h>
#include <stdbool.h>
#define N 1e63
#define K 10000
int multi_counter = 0;
static long long multiply(struct Fermat *this, long long x, long long y){
  multi_counter++;
  return (x%this->n*y%this->n)%this->n;
}
static long long fastExp(struct Fermat *this, long long a, long long b){
    if(b==1) return a;
    if(b==0) return 1;
    if(b%2 == 0){
        return fastExp(this,multiply(this,a,a),b/2);
    }else{
        return multiply(this, a, fastExp(this,a,b-1));
    }
}
static struct Fermat new(long long num){
  return (struct Fermat){.n = num, .multiply = &multiply, .fastExp = &fastExp};
}
const struct FermatClass Fermat={.new=&new};

int main(void){
    srand(time(0));
    clock_t start, end;
    for(long long i = 3; i <= N; i++){
      bool wasPrime = true;
      multi_counter = 0;
      struct Fermat f = Fermat.new(i);
      start = clock();
      for(int k = 0; k < K; k++){
        long long testK = (rand()%(i-2))+2;
        if(f.fastExp(&f,testK,i-1) != 1){
          wasPrime = false;
          break;
        }
      }
      end = clock();
      if(wasPrime==true){
        printf("%lld is prime!\n",i);
        printf("The time required to calculate this was %f.\n",((double)(end-start))/CLOCKS_PER_SEC);
        printf("This operation took %d multiplications.\n",multi_counter);
        i *= i;
      }
    }
    printf("done");
}
