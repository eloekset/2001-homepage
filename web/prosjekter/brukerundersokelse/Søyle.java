import java.awt.*;

public class S�yle
{
    private Color farge;
    private int x, y, h�yde, lengde;
    private boolean treD;
    
    public S�yle(int x, int y, int w, int h, Color c, boolean treD)
    {
        this.x = x;
        this.y = y;
        this.lengde = w;
        this.h�yde = h;
        this.farge = c;
        this.treD = treD;
    }
    
    public void paint(Graphics g)
    {
        if(treD)
        {
            // tegner fyllfarge
            g.setColor(farge);
            g.fillRect(x, y, lengde-1, h�yde-1);

            // tegner m�rk ramme
            g.setColor(farge.darker());
            g.drawRect(x, y, lengde-1, h�yde-1);

            // tegner lys ramme
            g.setColor(farge.brighter());
            g.drawRect(x+1, y+1, lengde-1, h�yde-1);       
        }
        else
        {
            // tegner enkel s�yle
            g.setColor(farge);
            g.fillRect(x, y, lengde, h�yde);
        }
    }   
    
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return lengde; }
    public int getHeight() { return h�yde; }
}