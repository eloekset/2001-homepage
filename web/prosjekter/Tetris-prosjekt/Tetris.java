import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Tetris extends JPanel
{
	private Timer timer;
	private Figur figur = new Figur();
	private Rad[] rad;

	private int opphold, antallRader;
	private int w, h;

	public Tetris(int bps)
	{
		setBackground(Color.black);
		Border raised = BorderFactory.createRaisedBevelBorder();
		Border lowered = BorderFactory.createLoweredBevelBorder();
		Border kombinert = BorderFactory.createCompoundBorder(raised, lowered);
		setBorder(raised);
		addKeyListener(new SpilltastListener());

		opphold = 1000 / bps;  // bps = bilder per sekund
		w = getSize().width;
		h = getSize().height;
		antallRader = w / Rute.RUTESTØRRELSE;

		rad = new Rad[antallRader];
		timer = new Timer(opphold, new TimerListener());

		requestFocus();
	}

	public void paintComponent(Graphics g)
	{
		figur.tegn(g);
	}

	class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{

		}

		//flytt();
		//fjernFulleRader();

		//repaint();
	}

	class SpilltastListener implements KeyListener
	{
		public void keyPressed(KeyEvent evt)
		{
			System.err.println("Knast knastet...");

			switch(evt.getKeyCode())
			{
				case KeyEvent.VK_UP:
					figur.snuFigur(Figur.SNU_HØYRE);
					break;
				case KeyEvent.VK_LEFT:
					figur.flytt(-1, 0);
					break;
				case KeyEvent.VK_RIGHT:
					figur.flytt(1, 0);
					break;
				case KeyEvent.VK_DOWN:
					figur.flytt(0, 1);
					break;
			}

			repaint();
		}

		public void keyReleased(KeyEvent event) {System.err.println("Knast knastet...");}
		public void keyTyped(KeyEvent event) {System.err.println("Knast knastet...");}
	}
}