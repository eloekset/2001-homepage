import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class TetrisApplet extends JApplet implements Farge
{
	private Tetris tetris = new Tetris(10);
	private JPanel statuspanel = new JPanel();
	private JPanel kontrollpanel = new JPanel();
	private Border raised = BorderFactory.createRaisedBevelBorder();
	private Border lowered = BorderFactory.createLoweredBevelBorder();
	private Border kombinert = BorderFactory.createCompoundBorder(raised, lowered);

	public void init()
	{
		getContentPane().setLayout(new BorderLayout());

		statuspanel.setBackground(FARGE_BLÅ);
		statuspanel.setBorder(kombinert);
		kontrollpanel.setBackground(FARGE_BLÅ);
		kontrollpanel.setBorder(kombinert);

		getContentPane().add(tetris, BorderLayout.CENTER);
		getContentPane().add(kontrollpanel, BorderLayout.NORTH);
		getContentPane().add(statuspanel, BorderLayout.SOUTH);
	}
}