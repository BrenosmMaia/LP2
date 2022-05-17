package ivisible;

import java.awt.Graphics;

public interface ivisible {
    void paint(Graphics g, boolean focused);
    boolean isClicked(int x, int y);
}
