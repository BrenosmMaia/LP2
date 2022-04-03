import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import figures.*;

class EditorApp {
    public static void main (String[] args) {
        EditorFrame frame = new EditorFrame();
        frame.setVisible(true);
    }
}

class EditorFrame extends JFrame {
    private ArrayList<Figure> figs = new ArrayList<Figure>();
    private Figure focus = null;
    private Figure auxFocus = null;
    private int i = 0;
    private Point prior;
    private boolean newFocus;

    EditorFrame () {
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
                        if(getMousePosition() != null) {
                            int x = getMousePosition().x;
                            int y = getMousePosition().y;
                            int w = 50;
                            int h = 40;
                            if (evt.getKeyChar() == 'e') {
                                Ellipse e = new Ellipse(x, y, w, h, Color.black, Color.black);
                                figs.add(e);
                                if(focus != null)
                                    focus.setCont(Color.black);
                                focus = e;
                                focus.setCont(Color.red);
                            }
                            else if(evt.getKeyChar() == 'r') {
                                Rect R = new Rect(x, y, w, h, Color.blue, Color.black);
                                figs.add(R);
                                if(focus != null)
                                    focus.setCont(Color.black);
                                focus = R;
                                focus.setCont(Color.red);
                            }
                            else if(evt.getKeyChar() == 'l') {
                                Line l = new Line(x, y, w, h, Color.black);
                                figs.add(l);
                                if(focus != null)
                                    focus.setCont(Color.black);
                                focus = l;
                                focus.setCont(Color.red);
                            }
                            else if(evt.getKeyChar() == 't') {
                                Triangle t = new Triangle(x, y, w, h, Color.green, Color.black);
                                figs.add(t);
                                if(focus != null)
                                    focus.setCont(Color.black);
                                focus = t;
                                focus.setCont(Color.red);
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
                            repaint();
                        }
                    }
                }
        );

        this.addMouseListener(
                new MouseAdapter() {
                    public void mousePressed(MouseEvent evt) {
                        auxFocus = focus;
                        if(auxFocus != null) {
                            auxFocus.setCont(Color.black);
                            repaint();
                        }
                        focus = null;
                        prior = evt.getPoint();
                        if(getMousePosition() == null)
                            return;
                        for(Figure fig: figs) {
                            if(fig.isClicked(getMousePosition().x, getMousePosition().y)) {
                                newFocus = true;
                                if(auxFocus != null)
                                    auxFocus.setCont(Color.black);
                                focus = fig;
                                focus.setCont(Color.red);
                                i = figs.indexOf(fig);
                                repaint();
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

    public void paint (Graphics g) {
        super.paint(g);
        for(Figure fig: this.figs) {
            fig.paint(g);
        }
    }
}
