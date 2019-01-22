struct Fermat{
  long long n;
  long long (*multiply)(struct Fermat *this, long long x, long long y);
  long long (*fastExp)(struct Fermat *this, long long a, long long b);
};
extern const struct FermatClass{
  struct Fermat (*new)(long long num);
} Fermat;
