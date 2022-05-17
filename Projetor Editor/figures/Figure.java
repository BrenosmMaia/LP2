package figures;

import java.awt.*;
import ivisible.ivisible;

public abstract class Figure implements ivisible{
    int x, y;
    int w, h;
    public Color cont;

    Figure(int x, int y, int w, int h, Color cont) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.cont = cont;
    }

    public abstract void paint (Graphics g, boolean focused);

    public abstract boolean isClicked(int x, int y);

    public abstract void changeColor();

    public void drag(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public void reSize(int s) {
        if(s > 0) {
            this.w += s;
            this.h +=s;
        }
        else {
            if(this.w > 25 || this.h > 25) {
                this.w += s;
                this.h +=s;
            }
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
