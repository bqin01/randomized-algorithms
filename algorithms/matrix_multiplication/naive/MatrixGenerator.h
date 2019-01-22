struct MatrixGenerator{
  long long n;
  int **(*generate)(struct MatrixGenerator *this);
  int **(*multiply)(struct MatrixGenerator *this, int** A, int** B);
};
extern const struct MatrixGeneratorClass{
  struct MatrixGenerator (*new)(long long num);
} MatrixGenerator;
static int dotProduct(int* a1, int* a2, int n);
static int* squareXVector(int** A, int* B, int n);
