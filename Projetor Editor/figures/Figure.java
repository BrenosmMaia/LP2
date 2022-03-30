package figures;

import java.awt.*;

public abstract class Figure {
    int x, y;
    int w, h;
    Color fill;

    public Figure(int x, int y, int w, int h, Color fill) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.fill = fill;
    }

    public abstract void paint (Graphics g);
}
