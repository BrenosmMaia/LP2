package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Ellipse {
    int x, y;
    int w, h;
    Color fill, cont;

    public Ellipse (int x, int y, int w, int h, Color fill, Color cont) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.fill = fill;
        this.cont = cont;
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
                this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.fill);
        g2d.fill (new Ellipse2D.Double(this.x, this.y, this.w, this.h));
        g2d.setColor(this.cont);
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }
}
