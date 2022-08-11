
public class FigurTester
{
	private static Figur figur = new Figur();

	public static void main(String[] args)
	{
		System.out.println(figur);

		//figur.snuFigur(Figur.SNU_VENSTRE);
		//figur.snuFigur(Figur.SNU_VENSTRE);
		//figur.snuFigur(Figur.SNU_VENSTRE);
		//figur.snuFigur(Figur.SNU_VENSTRE);
		figur.snuFigur(Figur.SNU_HØYRE);
		figur.snuFigur(Figur.SNU_VENSTRE);
		figur.snuFigur(Figur.SNU_VENSTRE);
		//figur.snuFigur(Figur.SNU_HØYRE);
		//figur.snuFigur(Figur.SNU_HØYRE);
		//figur.snuFigur(Figur.SNU_HØYRE);
	}
}