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
    private Random rand = new Random();
    private Figure focus = null;

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
                            int w = rand.nextInt(20,100);
                            int h = rand.nextInt(20,100);
                            if (evt.getKeyChar() == 'e') {
                                Ellipse e = new Ellipse(x, y, w, h, Color.black, Color.white);
                                figs.add(e);
                                focus = e;
                            }
                            else if(evt.getKeyChar() == 'r') {
                                Rect R = new Rect(x, y, w, h, Color.blue, Color.white);
                                figs.add(R);
                                focus = R;
                            }
                            else if(evt.getKeyChar() == 'l') {
                                Line l = new Line(x, y, w, h, Color.red);
                                figs.add(l);
                                focus = l;
                            }
                            else if(evt.getKeyChar() == 't') {
                                Triangle t = new Triangle(x, y, w, h, Color.black, Color.blue);
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
