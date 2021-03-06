package figures;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rect extends Figure {
    private Rectangle2D Rect;
    private Rectangle2D foco;

    public Rect (int x, int y, int w, int h, Color fill, Color cont) {
        super(x, y, w, h, fill, cont);
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        this.Rect = new Rectangle2D.Double(this.x, this.y, this.w, this.h);
        g2d.setColor(this.fill);
        g2d.fill(this.Rect);
        g2d.setColor(this.cont);
        g2d.draw(this.Rect);
        if (focused) {
            Graphics2D focusline = (Graphics2D) g;
            this.foco = new Rectangle2D.Double(this.x-3,this.y-3,this.w+6,this.h+6);
            focusline.setColor(Color.red);
            focusline.draw(this.foco);
        }
    }

    public boolean isClicked(int x, int y) {
        if(this.Rect != null)
            return this.Rect.contains(x, y);
        return false;
    }

    public void changeColor() {
        if (this.fill == Color.blue)
            this.fill = Color.black;
        else if(this.fill == Color.black)
            this.fill = Color.green;
        else if (this.fill == Color.green)
            this.fill = Color.cyan;
        else
            this.fill = Color.blue;
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
}
