
public class Rad
{
	private int antallRuter;

	private Rute[] rute;

	public Rad(int ant)
	{
		this.antallRuter = ant;

		rute = new Rute[antallRuter];
	}

	public void setRute(Rute r)
	{
		int x = r.getX();

		rute[x] = r;
	}

	public boolean isRute(int x)
	{
		if(rute[x] != null)
			return true;

		return false;
	}
}