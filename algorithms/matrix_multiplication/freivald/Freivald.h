#include <stdbool.h>

struct Freivald{
  long long n;
  int* (*generate01Vector)(struct Freivald *this, long long size);
  bool (*verify)(struct Freivald *this, struct MatrixGenerator *mg, int** A, int** B, int **C);
};
extern const struct FreivaldClass{
  struct Freivald (*new)(long long num);
} Freivald;
