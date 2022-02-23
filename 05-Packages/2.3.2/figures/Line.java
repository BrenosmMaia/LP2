package figures;

import java.awt.*;

public class Line {
    int x1, y1;
    int x2, y2;
    Color fill;

    public Line (int x1, int y1, int x2, int y2, Color fill) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.fill = fill;
    }

    public void print () {
        System.out.format("Linha entre os pontos (%d,%d) e (%d,%d).\n",
                this.x1, this.y1, this.x2, this.y2);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.fill);
        g2d.drawLine(this.x1,this.y1, this.x2, this.y2);
    }
}
