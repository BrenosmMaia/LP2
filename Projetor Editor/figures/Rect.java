package figures;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Rect extends Figure {
    private Color fill;
    private Rectangle2D Rect;

    public Rect (int x, int y, int w, int h, Color fill, Color cont) {
        super(x, y, w, h, cont);
        this.fill = fill;
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        this.Rect = new Rectangle2D.Double(this.x, this.y, this.w, this.h);
        g2d.setColor(this.fill);
        g2d.fill(this.Rect);
        if (focused)
            g2d.setColor(Color.red);
        else
            g2d.setColor(this.cont);
        g2d.draw(this.Rect);
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
        else
            this.fill = Color.blue;
    }
}
