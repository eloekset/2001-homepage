
public class HexTilInt
{
    String hex;
    
    public HexTilInt(String hex)
    {
        hex = hex.trim();
        this.hex = hex;
    }
    
    public int getInt()
    {
        if(hex == null)
            return 0;
        
        int siffer1 = 0, siffer2 = 0;
        
        try { siffer1 = konverterTilInt(new Character(hex.charAt(0))); }
        catch(IndexOutOfBoundsException e) { siffer1 = 0; }
        
        try { siffer2 = konverterTilInt(new Character(hex.charAt(1))); }
        catch(IndexOutOfBoundsException e) { siffer2 = 0; }
        
        int tall = siffer1 * 16;
        tall += siffer2;
        
        return tall;
    }
    
    private int konverterTilInt(Character c)
    {
        if(Character.isDigit(c.charValue()))
            return (Integer.parseInt(c.toString()));
        else if(Character.isLetter(c.charValue()))
        {
            if(c.charValue() == 'a' || c.charValue() == 'A')
                return 10;
            if(c.charValue() == 'b' || c.charValue() == 'B')
                return 11;
            if(c.charValue() == 'c' || c.charValue() == 'C')
                return 12;
            if(c.charValue() == 'd' || c.charValue() == 'D')
                return 13;
            if(c.charValue() == 'e' || c.charValue() == 'E')
                return 14;
            if(c.charValue() == 'f' || c.charValue() == 'F')
                return 15;
        }
        
        return 0;
    }
}