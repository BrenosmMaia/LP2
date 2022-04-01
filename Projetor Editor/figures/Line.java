package figures;

import java.awt.*;
import java.awt.geom.Line2D;

public class Line extends Figure {
    private Line2D Line;

    public Line (int x, int y, int w, int h, Color cont) {
        super(x, y, w, h, cont);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Line = new Line2D.Double(this.x,this.y, this.w, this.h);
        g2d.setColor(this.cont);
        g2d.draw(Line);
    }

    public boolean isClicked(int x, int y) {
        if(this.Line != null)
            return this.Line.contains(x, y);
        return  false;
    }
}
