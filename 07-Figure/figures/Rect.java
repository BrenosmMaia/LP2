package figures;

import java.awt.*;

public class Rect extends Figure {
    private int x, y;
    private int w, h;
    private Color fill, cont;

    public Rect (int x, int y, int w, int h, Color fill, Color cont) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.fill = fill;
        this.cont = cont;
    }

    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
                this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.fill);
        g2d.fillRect(this.x,this.y,this.w,this.h);
        g2d.setColor(this.cont);
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
}
