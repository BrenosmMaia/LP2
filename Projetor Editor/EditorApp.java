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
    private Figure auxFocus = null;
    private Button butFocus = null;
    private int i = 0;
    private Point prior;
    private boolean newFocus;
    private int w = 50;
    private int h = 40;
    private int l = 20;
    private int p = 30;
    private int x ;
    private int y ;
    private Rect reSize = new Rect (0, 0, 10, 10, Color.white, Color.black);
    private Rect reDimenR = new Rect (0, 0, 10, 10, Color.white, Color.black);
    private Rect reDimenL = new Rect (0, 0, 10, 10, Color.white, Color.black);
    private Rect reDimenT = new Rect (0, 0, 10, 10, Color.white, Color.black);
    private Rect reDimenD = new Rect (0, 0, 10, 10, Color.white, Color.black);
    private boolean reSizeFocus = false;
    private boolean reDimenRFocus= false;
    private boolean reDimenLFocus= false;
    private boolean reDimenTFocus= false;
    private boolean reDimenDFocus= false;


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
                            x = getMousePosition().x;
                            y = getMousePosition().y;

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
                        auxFocus = focus;
                        focus = null;
                        prior = evt.getPoint();
                        x = getMousePosition().x;
                        y = getMousePosition().y;
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
                            reSizeFocus = false;
                            reDimenRFocus = false;
                            reDimenLFocus = false;
                            reDimenDFocus = false;
                            reDimenTFocus = false;
                            if(fig.isClicked(x, y)) {
                                newFocus = true;
                                focus = fig;
                                i = figs.indexOf(fig);
                            }
                            else if(reSize.isClicked(x, y)) {
                                reSizeFocus = true;
                                focus = auxFocus;
                            }
                            else if(reDimenR.isClicked(x, y)) {
                                reDimenRFocus = true;
                                focus = auxFocus;
                            }
                            else if(reDimenL.isClicked(x, y)) {
                                reDimenLFocus = true;
                                focus = auxFocus;
                            }
                            else if(reDimenD.isClicked(x, y)) {
                                reDimenDFocus = true;
                                focus = auxFocus;
                            }
                            else if(reDimenT.isClicked(x, y)) {
                                reDimenTFocus = true;
                                focus = auxFocus;
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
                        Point current = evt.getPoint();
                        if(reSizeFocus) {
                            focus.reDimension(current.x - x,
                                    current.y - y);
                            x = current.x;
                            y = current.y;
                            repaint();
                        }

                        else if (reDimenRFocus) {
                            if(current.x - x > 0) {
                                focus.w += current.x - x;
                                x = current.x;
                            }
                            else {
                                if((focus.w >= 15) && (evt.getX() > focus.x)) {
                                    focus.w += current.x - x;
                                    x = current.x;
                                }
                            }
                            repaint();
                        }

                        else if (reDimenLFocus) {
                            if(current.x - x < 0) {
                                focus.x += current.x - x;
                                focus.w -= current.x - x;
                            }
                            else {
                                if((focus.w >= 15)) {
                                    focus.x += current.x - x;
                                    focus.w -= current.x - x;
                                }
                            }
                            x = current.x;
                            repaint();
                        }

                        else if(reDimenDFocus) {
                            if(current.y - y < 0) {
                                if(focus.h >= 15)
                                    focus.h +=  current.y - y;
                            }
                            else
                                focus.h +=  current.y - y;
                            y = current.y;
                            repaint();
                        }

                        else if(reDimenTFocus) {
                            if(current.y - y > 0) {
                                if(focus.h >= 15) {
                                    focus.y += current.y - y;
                                    focus.h -= current.y - y;
                                }
                            }
                            else {
                                focus.y += current.y - y;
                                focus.h -=  current.y - y;
                            }
                            y = current.y;
                            repaint();
                        }

                        else if(focus != null && prior != null) {
                            focus.drag(current.x - prior.x,
                                    current.y - prior.y);
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
        if(focus != null) {
            reSize.x = (focus.x - 1) + (focus.w + 18) - 6;
            reSize.y = (focus.y - 3) + (focus.h + 15) - 6;

            reDimenR.x = focus.x + focus.w + 25;
            reDimenR.y = focus.y + (focus.h/2) - 5;

            reDimenL.x = focus.x - 10;
            reDimenL.y = focus.y + (focus.h/2) - 5;

            reDimenT.x = focus.x + (focus.w/2) - 5 ;
            reDimenT.y = focus.y - 12;

            reDimenD.x = focus.x + (focus.w/2) - 5 ;
            reDimenD.y = focus.y + focus.h + 5;

            reSize.paint(g, false);
            reDimenR.paint(g, false);
            reDimenL.paint(g, false);
            reDimenT.paint(g, false);
            reDimenD.paint(g, false);
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
