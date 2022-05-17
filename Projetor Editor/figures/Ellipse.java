package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Ellipse extends Figure {
    private Color fill;
    private Ellipse2D Ellipse;

    public Ellipse (int x, int y, int w, int h, Color fill, Color cont) {
        super(x, y, w, h, cont);
        this.fill = fill;
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        this.Ellipse = new Ellipse2D.Double(this.x, this.y, this.w, this.h);
        g2d.setColor(this.fill);
        g2d.fill(this.Ellipse);
        if (focused)
            g2d.setColor(Color.red);
        else
            g2d.setColor(this.cont);
        g2d.draw(this.Ellipse);
    }

    public boolean isClicked(int x, int y) {
        if(this.Ellipse != null)
            return this.Ellipse.contains(x, y);
        return false;
    }

    public void changeColor() {
        if (this.fill == Color.black)
            this.fill = Color.blue;
        else if(this.fill == Color.blue)
            this.fill = Color.green;
        else
            this.fill = Color.black;
    }
}
