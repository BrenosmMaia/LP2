package figures;

import java.awt.*;

public class Rect extends Figure {
    private Color cont;

    public Rect (int x, int y, int w, int h, Color fill, Color cont) {
        super(x, y, w, h, fill);
        this.cont = cont;
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.fill);
        g2d.fillRect(this.x,this.y,this.w,this.h);
        g2d.setColor(this.cont);
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
}
