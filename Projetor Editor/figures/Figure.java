package figures;

import java.awt.*;

public abstract class Figure {
    protected int x, y;
    protected int w, h;
    protected Color fill;

    public Figure(int x, int y, int w, int h, Color fill) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.fill = fill;
    }

    public abstract void paint (Graphics g);

    public void drag(int delta_x, int delta_y) {
        this.x += delta_x;
        this.y += delta_y;
    }

    public void reSize(int s) {
        this.w += s;
        this.h +=s;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
