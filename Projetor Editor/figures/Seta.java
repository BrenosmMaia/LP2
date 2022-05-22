package figures;

import java.awt.*;

public class Seta extends Figure {
    private Polygon Seta;
    private Polygon foco;


    private int l, p;

    public Seta (int x, int y, int w, int h, int l, int p, Color fill, Color cont) {
        super(x, y , w, h, fill, cont);
        this.l = l;
        this.p = p;
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        int[] xValues = { this.x, this.x+this.w, this.x+this.w, this.x+this.w+this.p,
                this.x + this.w, this.x + this.w, this.x};
        int[] yValues = { this.y, this.y, this.y - this.l, this.y + this.h/2,
                this.l+this.h+this.y, this.y + this.h, this.y + this.h};

        this.Seta = new Polygon(xValues, yValues, 7 );
        g2d.setColor(this.fill);
        g2d.fill(this.Seta);
        g2d.setColor(this.cont);
        g2d.draw(this.Seta);
        if (focused) {
            Graphics2D focusline = (Graphics2D) g;
            int[] xFocus = { this.x-3, this.x+this.w-3, this.x+this.w-3,this.x+this.w, this.x+this.w+this.p+6,
                    this.x + this.w,this.x + this.w-3, this.x + this.w-3, this.x-3};
            int[] yFocus = { this.y-3, this.y-3, this.y - this.l-6,this.y - this.l-6, this.y + this.h/2,
                    this.l+this.h+this.y+6,this.l+this.h+this.y+6, this.y + this.h+3, this.y + this.h+3};
            this.foco = new Polygon( xFocus, yFocus, 9 );
            focusline.setColor(Color.red);
            focusline.draw(this.foco);
        }

    }

    public boolean isClicked(int x, int y) {
        if(this.Seta != null)
            return this.Seta.contains(x, y);
        return  false;
    }

    public void changeColor() {
        if (this.fill== Color.cyan)
            this.fill = Color.blue;
        else if(this.fill == Color.blue)
            this.fill = Color.green;
        else if(this.fill == Color.green)
            this.fill = Color.black;
        else
            this.fill = Color.cyan;
    }

    public void changeCont() {
        if (this.cont == Color.black)
            this.cont = Color.blue;
        else if (this.cont == Color.blue)
            this.cont = Color.green;
        else if (this.cont == Color.green)
            this.cont = Color.cyan;
        else
            this.cont = Color.black;
    }

    public void reSize(int s) {
        if (s > 0) {
            this.w += s+5;
            this.h += s+5;
            this.p += s;
            this.l += s;
        }
        else {
            if (this.p >= 15 && this.l >=4 && this.w >=10) {
                this.p += s;
                this.l += s;
                this.w += s-5;
                this.h += s-5;
            }
        }
    }
}
