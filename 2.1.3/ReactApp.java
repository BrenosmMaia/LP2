public class RectApp {
    public static void main (String[] args) {
        Rect r1 = new Rect(1,1, 10,5);
        r1.print();
        int A = r1.area();
        System.out.format("A area do retangulo: %d\n", A);
        r1.drag(10, 5);
        r1.print();
    }
}
class Rect {
    int x, y;
    int w, h;
    Rect (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
                this.w, this.h, this.x, this.y);
    }
    int area() {
        return this.w * this.h;
    }
    void drag(int dx, int dy) {
        this.x+=dx;
        this.y+=dy;
    }
}
