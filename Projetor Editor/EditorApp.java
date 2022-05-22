import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.io.*;


import figures.*;

class EditorApp {
    public static void main (String[] args) {
        EditorFrame frame = new EditorFrame();
        frame.setVisible(true);
    }
}

class EditorFrame extends JFrame {
    private ArrayList<Figure> figs = new ArrayList<Figure>();
    private ArrayList<Button> buts = new ArrayList<Button>();
    private Figure focus = null;
    private Button butFocus = null;
    private int i = 0;
    private Point prior;
    private boolean newFocus;
    private int w = 50;
    private int h = 40;
    private int l = 20;
    private int p = 30;

    EditorFrame () {
        try {
            FileInputStream f = new FileInputStream("proj.bin");
            ObjectInputStream o = new ObjectInputStream(f);
            this.figs = (ArrayList<Figure>) o.readObject();
            o.close();

        }
        catch(Exception x) {
            System.out.println("ERRO!");
        }

        this.addWindowListener (
                new WindowAdapter() {
                    public void windowClosing (WindowEvent e) {
                        try {
                            FileOutputStream f = new FileOutputStream("proj.bin");
                            ObjectOutputStream o = new ObjectOutputStream(f);
                            o.writeObject(figs);
                            o.flush();
                            o.close();
                        }
                        catch(Exception x) {
                        }
                        System.exit(0);
                    }
                }
        );

        buts.add(new Button(0, new Rect(0,0,0,0,Color.black,Color.white)));
        buts.add(new Button(1, new Ellipse(0,0,0,0,Color.black,Color.white)));
        buts.add(new Button(2, new Triangle(0,0,0,0,Color.black,Color.white)));
        buts.add(new Button(3, new Seta(0,0,0,0, 5, 10, Color.black, Color.white)));
        buts.add(new Button(4, new Rect(0,0,0,0,Color.red,Color.white)));

        this.addWindowListener (
                new WindowAdapter() {
                    public void windowClosing (WindowEvent e) {
                        System.exit(0);
                    }
                }
        );

        this.addKeyListener (
                new KeyAdapter() {;
                    public void keyPressed (KeyEvent evt) {
                        if(getMousePosition() != null) {
                            int x = getMousePosition().x;
                            int y = getMousePosition().y;

                            if (evt.getKeyChar() == 'e') {
                                Ellipse e = new Ellipse(x, y, w, h, Color.black, Color.black);
                                figs.add(e);
                                focus = e;
                            }
                            else if(evt.getKeyChar() == 'r') {
                                Rect R = new Rect(x, y, w, h, Color.blue, Color.black);
                                figs.add(R);
                                focus = R;
                            }
                            else if(evt.getKeyChar() == 's') {
                                Seta s = new Seta(x, y, w, h, l, p, Color.cyan, Color.black);
                                figs.add(s);
                                focus = s;
                            }
                            else if(evt.getKeyChar() == 't') {
                                Triangle t = new Triangle(x, y, w, h, Color.green, Color.black);
                                figs.add(t);
                                focus = t;
                            }
                            else if(evt.getKeyCode() == KeyEvent.VK_DELETE) {
                                for(Iterator<Figure> iterator = figs.iterator(); iterator.hasNext();) {
                                    Figure figure = iterator.next();
                                    if(figure == focus) {
                                        iterator.remove();
                                        focus = null;
                                    }
                                }
                            }
                            else if(evt.getKeyChar() == 'f' && figs.size()!=0) {
                                if (i >= figs.size()) {
                                    i = 0;
                                }
                                focus = figs.get(i);
                                i++;
                                repaint();
                            }
                            else if(evt.getKeyCode() == KeyEvent.VK_UP) {
                                if(focus != null && focus.getY() > 0)
                                    focus.drag(0, -6);

                            }
                            else if(evt.getKeyCode() == KeyEvent.VK_DOWN) {
                                if(focus != null && focus.getY() < 400)
                                    focus.drag(0, 6);

                            }
                            else if(evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                                if(focus != null && focus.getX() < 400)
                                    focus.drag(6, 0);
                            }
                            else if(evt.getKeyCode() == KeyEvent.VK_LEFT) {
                                if(focus != null && focus.getX() > 0)
                                    focus.drag(-6, 0);
                            }
                            else if(evt.getKeyChar() == '+') {
                                if(focus != null)
                                    focus.reSize(6);
                            }
                            else if(evt.getKeyChar() == '-') {
                                if(focus != null)
                                    focus.reSize(-6);
                            }
                            else if(evt.getKeyChar() == 'c') {
                                if(focus != null)
                                    focus.changeColor();
                            }
                            else if(evt.getKeyChar() == 'v') {
                                if(focus != null)
                                    focus.changeCont();
                            }
                            repaint();
                        }
                    }
                }
        );

        this.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        butFocus = null;
                        if(getMousePosition() == null)
                            return;
                        for(Button but: buts){
                            if(but.isClicked(getMousePosition().x, getMousePosition().y)) {
                                butFocus = but;
                            }
                        }
                        repaint();
                    }
                    public void mousePressed(MouseEvent evt) {
                        focus = null;
                        prior = evt.getPoint();
                        int x = getMousePosition().x;
                        int y = getMousePosition().y;
                        if(getMousePosition() == null)
                            return;
                        if(SwingUtilities.isLeftMouseButton(evt)){
                            if(getMousePosition()!= null && butFocus!=null) {
                                if(butFocus.idx == 0) {
                                    Rect r = new Rect(x, y, w, h, Color.blue, Color.black);
                                    figs.add(r);
                                    focus = r;
                                }
                                else if(butFocus.idx == 1) {
                                    Ellipse e = new Ellipse(x, y, w, h, Color.black, Color.black);
                                    figs.add(e);
                                    focus = e;
                                }
                                else if(butFocus.idx == 2) {
                                    Triangle t = new Triangle(x, y, w, h, Color.green, Color.black);
                                    figs.add(t);
                                    focus = t;
                                }
                                else if(butFocus.idx == 3) {
                                    Seta s = new Seta(x, y, w, h, l , p, Color.cyan, Color.black);
                                    figs.add(s);
                                    focus = s;
                                }
                                else if(butFocus.idx == 4) {
                                    for(Iterator<Figure> iterator = figs.iterator(); iterator.hasNext();) {
                                        Figure figure = iterator.next();
                                        iterator.remove();
                                    }
                                    focus = null;
                                    repaint();
                                }
                            }
                            butFocus = null;
                        }
                        else if (SwingUtilities.isRightMouseButton(evt))
                            butFocus = null;
                            repaint();
                        for(Figure fig: figs) {
                            if(fig.isClicked(x, y)) {
                                newFocus = true;
                                focus = fig;
                                i = figs.indexOf(fig);
                            }
                        }
                        if(newFocus) {
                            figs.remove(i);
                            figs.add(focus);
                            newFocus = false;
                            repaint();
                        }
                    }
                }
        );

        this.addMouseMotionListener(
                new MouseMotionAdapter() {
                    public void mouseDragged(MouseEvent evt) {
                        if(focus != null && prior != null) {
                            Point current = evt.getPoint();
                            focus.drag((int) (current.getX() - prior.x),
                                    (int) (current.getY() - prior.y));
                            prior = current;
                            repaint();
                        }
                    }
                }
        );

        this.setTitle("Editor Grafico");
        this.setSize(400, 400);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for(Figure fig: this.figs){
            if(fig == focus) {
                fig.paint(g, true);
            }
            else {
                fig.paint(g, false);
            }
        }
        for(Button but: this.buts){
            if(but == butFocus) {
                but.paint(g, true);
            }
            else {
                but.paint(g, false);
            }
        }
    }
}
