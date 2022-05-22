package figures;

import java.awt.*;

public class Triangle extends Figure {
    private Polygon Triangle;
    private Polygon foco;

    public Triangle(int x, int y, int w, int h, Color fill, Color cont) {
        super(x, y, w, h, fill, cont);
    }

    public void paint(Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        int[] xValues = { this.x, this.x+(this.w/2), this.x+this.w};
        int[] yValues = { this.y+this.h, this.y, this.y + this.h,};
        this.Triangle = new Polygon( xValues, yValues, 3);
        g2d.setColor(this.fill);
        g2d.fill(this.Triangle);
        g2d.setColor(this.cont);
        g2d.draw(this.Triangle);
        if (focused) {
            Graphics2D focusline = (Graphics2D) g;
            int[] xFocus = { this.x-5, this.x+(this.w/2), this.x+this.w+5};
            int[] yFocus = { this.y+this.h+3, this.y-6, this.y + this.h+3};
            this.foco = new Polygon( xFocus, yFocus, 3);
            focusline.setColor(Color.red);
            focusline.draw(this.foco);
        }
    }

    public boolean isClicked(int x, int y) {
        if(this.Triangle != null)
            return this.Triangle.contains(x, y);
        return false;
    }

    public void changeColor() {
        if (this.fill == Color.green)
            this.fill = Color.black;
        else if (this.fill == Color.black)
            this.fill = Color.blue;
        else if (this.fill == Color.blue)
            this.fill = Color.cyan;
        else
            this.fill = Color.green;
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
