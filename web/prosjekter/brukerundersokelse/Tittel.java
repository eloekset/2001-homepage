import java.awt.*;

public class Tittel
{
    private int x;
    private int y;
    private String tekst;
    private Font font;
    private Color farge;
    
    public Tittel(int x, int y, String str, Font f, Color c)
    {
        this.x = x;
        this.y = y;
        this.tekst = str;
        this.font = f;
        this.farge = c;
    }
    
    public void paint(Graphics g)
    {
        g.setColor(farge);
        g.setFont(font);
        g.drawString(tekst, x, y);
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return (font.getSize() * tekst.length()); }
    public int getHeight() { return font.getSize(); }
}