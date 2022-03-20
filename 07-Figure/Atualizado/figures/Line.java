package figures;

import java.awt.*;

public class Line extends Figure {

    public Line (int x, int y, int w, int h, Color fill) {
        super(x, y, w, h, fill);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.fill);
        g2d.drawLine(this.x,this.y, this.w, this.h);
    }
}
