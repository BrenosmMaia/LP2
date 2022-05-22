import java.awt.*;
import ivisible.*;
import figures.*;

public class Button implements ivisible {
    private static int x = 15;
    private static int y = 40;
    private static int size = 30;
    private static int figSize = 20;

    protected int idx;
    protected Figure fig;

    public Button (int idx, Figure fig) {
        this.idx = idx;
        this.fig = fig;
        this.fig.x = x+5;
        this.fig.y = (idx==3 ? y+idx*size+10:y+idx*size+5);
        this.fig.w = (idx==3 ? figSize-10:figSize);
        this.fig.h = (idx==3 ? figSize-10:figSize);
    }

    public boolean isClicked(int mouseX, int mouseY) {
        return mouseX<=x+size &&
                mouseX>=x &&
                mouseY>=y+this.idx*size &&
                mouseY<=y+size+this.idx*size;
    }

    public void paint(Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        if(focused) {
            g2d.setColor(Color.gray);
            g2d.fillRect(x,y+this.idx*size, size, size);
        }
        else {
            g2d.setColor(Color.lightGray);
            g2d.fillRect(x,y+this.idx*size,size,size);
        }
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.black);
        g2d.drawRect(x,y+this.idx*size,size,size);
        this.fig.paint(g, false);
    }
}
