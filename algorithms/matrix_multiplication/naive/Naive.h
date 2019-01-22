#include <stdbool.h>

struct Naive{
  long long n;
  bool (*verify)(struct MatrixGenerator *this, int** A, int** B, int **C);
};
extern const struct NaiveClass{
  struct Naive (*new)(long long num);
} Naive;
