int repeat = 10000;
int count = 0;
int a = 0;
for (int i = 0; i < repeat; ++i) {
  a += 1;
  if (random(1) < 0.5) {
    count += 1;
  }
}
println(a);
println(count + " / " + repeat);
