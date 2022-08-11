import javax.swing.*;

public interface Kantlinje
{
	Border blackline, etched, raisedbevel, loweredbevel, empty;

	blackline = BorderFactory.createLineBorder(Color.black);
	etched = BorderFactory.createEtchedBorder();
	raisedbevel = BorderFactory.createRaisedBevelBorder();
	loweredbevel = BorderFactory.createLoweredBevelBorder();
	empty = BorderFactory.createEmptyBorder();

}