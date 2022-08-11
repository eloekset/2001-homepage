import java.awt.*;

public class Rute
{
	public static final int RUTESTØRRELSE = 15;

	private int x, y;
	private Color farge;

	public Rute(int x, int y, Color farge)
	{
		this.x = x;
		this.y = y;
		this.farge = farge;
	}

	public Rute()
	{
		x = 0;
		y = 0;
		farge = null;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public Color getFarge()
	{
		return farge;
	}

	public void setX(int pos)
	{
		x = pos;
	}

	public void setY(int pos)
	{
		y = pos;
	}

	public void setFarge(Color farge)
	{
		this.farge = farge;
	}
}