package figures;

import java.awt.*;
import java.io.Serializable;

import ivisible.ivisible;

public abstract class Figure implements ivisible, Serializable {
    public int x, y;
    public int w, h;
    public Color cont;
    public Color fill;

    Figure(int x, int y, int w, int h, Color fill, Color cont) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.cont = cont;
        this.fill = fill;
    }

    public abstract void paint (Graphics g, boolean focused);

    public abstract boolean isClicked(int x, int y);

    public abstract void changeColor();

    public abstract void changeCont();

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

    public void reDimension(int dw, int dh) {
        if(dw>0 && dh > 0) {
            this.w += dw;
            this.h += dh;
        }
        else {
            if(this.w > 20 || this.h > 20) {
                this.w += dw;
                this.h += dh;
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
