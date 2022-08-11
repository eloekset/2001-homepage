import java.awt.*;

public class Figur extends Rute implements Farge
{
	private final int[][] FIGUR_TABELL =
	{
		{
			1, 0, 0, 0,
		  	1, 0, 0, 0,
		  	1, 0, 0, 0,
		  	1, 1, 0, 0
		},

		{
			0, 0, 0, 0,
			1, 1, 1, 1,
			0, 0, 0, 0,
			0, 0, 0, 0,
		},

		{
			0, 0, 0, 0,
			0, 1, 1, 0,
			0, 0, 1, 1,
			0, 0, 0, 0
		},

		{
			0, 0, 0, 0,
			0, 1, 0, 0,
			0, 1, 1, 0,
			0, 0, 1, 0
		},

		{
			0, 0, 0, 0,
			0, 1, 1, 0,
			0, 1, 1, 0,
			0, 0, 0, 0
		}

	};

	public final boolean SKRIV_UT_FIGURNUMMER = false;
	public final boolean SKRIV_UT_FIGUR = false;
	public final boolean SKRIV_UT_ST�RRELSE = false;

	public static final int FIGUR_BREDDE = 4;  // i ant ruter
	public static final int FIGUR_H�YDE = 4;  // i ant ruter
	public static final int SNU_VENSTRE = 1;  // snur figuren 90 grader mot venstre
	public static final int SNU_H�YRE = 2;  // snur figuren 90 grader mot h�yre

	private int[][] figur;
	private int figurnr, bredde, h�yde;
	private int x, y;
	private Color farge;
	private int venstre = FIGUR_BREDDE, h�yre = 0;  // lagrer hvor selve figuren begynner
	private int oppe = FIGUR_H�YDE, nede = 0;  // lagrer hvor selve figuren begynner
	private Rute[] rute = new Rute[FIGUR_BREDDE * FIGUR_H�YDE];


	public Figur()
	{
		lagTilfeldigFigur();

		// finn bredden og h�yden p� denne figuren
		for(int i=0; i < figur.length; i++)
			for(int j=0; j < figur[i].length; j++)
			{
				if(i < venstre && figur[i][j] == 1)
					venstre = i;
				if(i > h�yre && figur[i][j] == 1)
					h�yre = i;
				if(j < oppe && figur[i][j] == 1)
					oppe = j;
				if(j > nede && figur[i][j] == 1)
					nede = j;
			}

		bredde = h�yre - venstre + 1;
		h�yde = nede - oppe + 1;

		if(SKRIV_UT_ST�RRELSE)
			System.err.println("St�rrelse: bredde: " + bredde + ", h�yde: " + h�yde + "; " + venstre + "," + h�yre + "," + oppe + "," + nede);
	}

	public void lagTilfeldigFigur()
	{
		int figurnr = (int)(Math.random() * FIGUR_TABELL.length);  // plukker ut figuren
		figur = new int[FIGUR_H�YDE][FIGUR_BREDDE];  // lager en ny tom figur

		switch(figurnr)  // gi figuren farge etter type
		{
			case 0:
				farge = FARGE_FIGUR_LILLA;
				break;
			case 1:
				farge = FARGE_FIGUR_R�D;
				break;
			case 2:
				farge = FARGE_FIGUR_GR�NN;
				break;
			case 3:
				farge = FARGE_FIGUR_GUL;
				break;
			case 4:
				farge = FARGE_FIGUR_BL�;
				break;
			default:
				farge = FARGE_GUL;
		}

		if(SKRIV_UT_FIGURNUMMER)
			System.err.println("Figur nr: " + figurnr);

		// setter inn verdier til den nye figuren
		for(int j=0; j < FIGUR_TABELL[figurnr].length; j++)
		{
			// bytt om x og y hvis figuren st�r feil vei!!!
			int y = (j % figur.length);
			int x = (j / FIGUR_BREDDE);

			// konverterer fra 1D til 2D array
			figur[y][x] = FIGUR_TABELL[figurnr][j];
		}

		for(int i=0; i < figur.length; i++)
			for(int j=0; j < figur[i].length; j++)
				if(figur[i][j] == 1)
				{
					int ruteX = x + j;
					int ruteY = y + i;

					rute[i * figur[i].length + j] = new Rute(ruteX, ruteY, farge);
				}

		if(SKRIV_UT_FIGUR)
			for(int i=0; i < figur.length; i++)
				for(int j=0; j < figur[i].length; j++)
				{
					if(j % FIGUR_BREDDE == 0)
						System.err.print("\n");

					System.err.print(figur[j][i]);
				}
	}

	public void snuFigur(int retning)
	{
		int[][] temp = new int[FIGUR_H�YDE][FIGUR_BREDDE];

		if(retning == SNU_VENSTRE)  // mot venstre
		{
			for(int i=0; i < figur.length; i++)
				for(int j=0; j < figur[i].length; j++)
					temp[3-j][i] = figur[i][j];
		}
		else  // mot h�yre
		{
			for(int i=0; i < figur.length; i++)
				for(int j=0; j < figur[i].length; j++)
					temp[j][3-i] = figur[i][j];
		}

		figur = temp;

		if(SKRIV_UT_FIGUR)
			for(int i=0; i < figur.length; i++)
				for(int j=0; j < figur[i].length; j++)
				{
					if(j % FIGUR_BREDDE == 0)
						System.err.print("\n");

					System.err.print(figur[j][i]);
				}
	}

	public void flytt(int x, int y)
	{
		this.x += x;
		this.y += y;

		for(int i=0; i < figur.length; i++)
			for(int j=0; j < figur[i].length; j++)
				if(figur[i][j] == 1)
				{
					int ruteX = x + j;
					int ruteY = y + i;

					rute[i * figur[i].length + j] = new Rute(ruteX, ruteY, farge);
				}
	}

	public void tegn(Graphics g)
	{
		int s = Rute.RUTEST�RRELSE;

		g.setColor(farge);

		for(int i=0; i < rute.length; i++)
			if(rute[i] != null)
			{
				System.err.println(rute[i].getX() + ", " + rute[i].getY());
				g.fillRect(rute[i].getX()*s, rute[i].getY()*s, s, s);
			}

		System.err.println();
	}
}