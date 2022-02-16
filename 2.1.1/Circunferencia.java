class Circulo {
  int x, y;
  int r;
  Circulo (int x, int y, int r) {
    this.x = x;
    this.y = y;
    this.r = r;
  }
  void print () {
    System.out.format("Circunferencia com centro em (%d, %d) e raio %d.\n", this.x, this.y, this.r);
  }
}
