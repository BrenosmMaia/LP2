package figures;

import java.awt.*;

public class Triangle extends Figure {
    private Color fill;
    private Polygon Triangle;

    public Triangle(int x, int y, int w, int h, Color fill, Color cont) {
        super(x, y, w, h, cont);
        this.fill = fill;
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int[] xValues = { this.x, this.x+(this.w/2), this.x+this.w};
        int[] yValues = { this.y+this.h, this.y, this.y + this.h,};
        this.Triangle = new Polygon( xValues, yValues, 3);
        g2d.setColor(this.fill);
        g2d.fill(this.Triangle);
        g2d.setColor(this.cont);
        g2d.draw(this.Triangle);
    }

    public boolean isClicked(int x, int y) {
        if(this.Triangle != null)
            return this.Triangle.contains(x, y);
        return false;
    }
}
