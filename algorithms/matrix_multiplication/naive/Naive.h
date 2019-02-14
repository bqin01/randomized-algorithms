#include <stdbool.h>

struct Naive{
  long long n;
  bool (*verify)(struct Naive *this, struct MatrixGenerator *mg, int** A, int** B, int **C);
};
extern const struct NaiveClass{
  struct Naive (*new)(long long num);
} Naive;
