#include<stdbool.h>

struct Naive{
  long long n;
  bool (*checkPrime)(struct Naive *this);
};
extern const struct NaiveClass{
  struct Naive (*new)(long long num);
} Naive;
