import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Ellipse> es = new ArrayList<Ellipse>();
    ArrayList<Rect> rs = new ArrayList<Rect>();
    Random rand = new Random();

    ListFrame () {
        this.addWindowListener (
                new WindowAdapter() {
                    public void windowClosing (WindowEvent e) {
                        System.exit(0);
                    }
                }
        );

        this.addKeyListener (
                new KeyAdapter(){
                    public void keyPressed (KeyEvent evt){
                        if (evt.getKeyChar() == 'e') {
                            int x = rand.nextInt(350);
                            int y = rand.nextInt(350);
                            int w = rand.nextInt(50);
                            int h = rand.nextInt(50);
                            Ellipse e = new Ellipse(x,y,w,h,Color.black,Color.white);
                            es.add(e);
                            repaint();
                        }
                    }
                }
        );

        this.setTitle("Elipses aleatórias");
        this.setSize(400, 400);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for(Ellipse e: this.es) {
            e.paint(g);
        }
    }
}
