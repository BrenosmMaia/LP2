package figures;

import java.awt.*;

public abstract class Figure {
    protected int x, y;
    protected int w, h;
    protected Color cont;

    public Figure(int x, int y, int w, int h, Color cont) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.cont = cont;
    }

    public abstract void paint (Graphics g);

    public abstract boolean isClicked(int x, int y);

    public abstract void changeColor();

    public void drag(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
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

    public void setCont(Color cont) {
        this.cont = cont;
    }
}
