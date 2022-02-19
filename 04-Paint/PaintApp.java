import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PaintApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Rect r1, r2, r3, r4;

    PaintFrame () {
        this.addWindowListener (
                new WindowAdapter() {
                    public void windowClosing (WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
        this.setTitle("Painting Figures");
        this.setSize(350, 350);
        this.r1 = new Rect(50,50, 100,30, Color.red, Color.black);
        this.r2 = new Rect(50,80, 100,30, Color.blue, Color.black);
        this.r3 = new Rect(50,110, 100,30, Color.yellow, Color.black);
        this.r4 = new Rect(50,140, 100,30, Color.green, Color.black);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
        this.r4.paint(g);
    }
}

class Rect {
    int x, y;
    int w, h;
    Color fill, cont;

    Rect (int x, int y, int w, int h, Color fill, Color cont) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.fill = fill;
        this.cont = cont;
    }

    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
                this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.fill);
        g2d.fillRect(this.x,this.y,this.w,this.h);
        g2d.setColor(this.cont);
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
}
