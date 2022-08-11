import java.awt.*;

public class Søyle
{
    private Color farge;
    private int x, y, høyde, lengde;
    private boolean treD;
    
    public Søyle(int x, int y, int w, int h, Color c, boolean treD)
    {
        this.x = x;
        this.y = y;
        this.lengde = w;
        this.høyde = h;
        this.farge = c;
        this.treD = treD;
    }
    
    public void paint(Graphics g)
    {
        if(treD)
        {
            // tegner fyllfarge
            g.setColor(farge);
            g.fillRect(x, y, lengde-1, høyde-1);

            // tegner mørk ramme
            g.setColor(farge.darker());
            g.drawRect(x, y, lengde-1, høyde-1);

            // tegner lys ramme
            g.setColor(farge.brighter());
            g.drawRect(x+1, y+1, lengde-1, høyde-1);       
        }
        else
        {
            // tegner enkel søyle
            g.setColor(farge);
            g.fillRect(x, y, lengde, høyde);
        }
    }   
    
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return lengde; }
    public int getHeight() { return høyde; }
}