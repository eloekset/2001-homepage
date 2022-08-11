import java.awt.*;
import java.io.*;
import java.net.*;

public class ResultatApplet extends java.applet.Applet 
{
    private String[] strTittel;
    private int[] stemmer;
    private int flestStemmer, antallStemmer;
    private Color bakgrunnsFarge, s�yleFarge, tittelFarge;
    private Font tittelFont;
    
    private Tittel[] tittel;
    private S�yle[] s�yle;
    
    private int bredde, h�yde, marg, tekstS�yleAvstand, alternativAvstand;
    private int s�yleH�yde;
    private boolean treD, visAntallStemmer;
    
    public void init () 
    {
        lesParametere();
        lesTittelOgStemmer();
   
        bredde = getSize().width;
        h�yde = getSize().height;
        System.out.println("bredde: " + bredde + ", h�yde: " + h�yde);
        System.out.println("strTittel: " + strTittel.length + "; stemmer: " + stemmer.length);
        
        flestStemmer = 0;
        for(int i=0; i < stemmer.length; i++)
            if(flestStemmer < stemmer[i])
                flestStemmer = stemmer[i];
        antallStemmer = 0;
        for(int i=0; i < stemmer.length; i++)
            antallStemmer += stemmer[i];
        
        tittel = new Tittel[strTittel.length];
        s�yle = new S�yle[stemmer.length];
        
        lagTitler();
        lagS�yler();
    }
    
    public void paint(Graphics g)
    {        
        // tegn titler
        for(int i=0; i < tittel.length; i++)
            if(tittel[i] != null)
                tittel[i].paint(g);
        
        // tegn alle s�yler
        for(int i=0; i < s�yle.length; i++)
            if(s�yle[i] != null)
                s�yle[i].paint(g);
        
        // vis antall stemmer
        if(visAntallStemmer)
        {
            String str = "Antall stemmer: " + antallStemmer;
            int x = marg;
            int y = marg + tittelFont.getSize() * (tittel.length+1);
            y += tekstS�yleAvstand * tittel.length;
            y += s�yleH�yde * tittel.length;
            y += alternativAvstand * (tittel.length+1);
            
            g.setColor(tittelFarge);
            g.setFont(tittelFont);
            g.drawString(str, x, y);
        }
    }
    
    
    //--------------------------------------------------------------------------
    //  Leser innstillinger til appleten
    //--------------------------------------------------------------------------
    private void lesParametere()
    {
        // sett bakgrunnsfarge
        String bakgrunnsFarge = getParameter("BAKGRUNNSFARGE");
        if(bakgrunnsFarge == null)
        {
            System.err.println("Parameter BAKGRUNNSFARGE ikke angitt");
            bakgrunnsFarge = "#000000";
        }
        this.bakgrunnsFarge = lagFarge(bakgrunnsFarge);
        setBackground(this.bakgrunnsFarge);
        
        // sett s�ylefarge
        String s�yleFarge = getParameter("S�YLEFARGE");
        if(s�yleFarge == null)
        { 
            System.err.println("Parameter S�YLEFARGE ikke angitt");
            s�yleFarge = "#d1e1f1";
        }
        this.s�yleFarge = lagFarge(s�yleFarge);
        
        // sett tittelfarge
        String tittelFarge = getParameter("TITTELFARGE");
        if(tittelFarge == null)
        {
            System.err.println("Parameter TITTELFARGE ikke angitt");
            tittelFarge = "d1e1f1";
        }
        this.tittelFarge = lagFarge(tittelFarge);
        
        // sett marg
        String marg = getParameter("MARG");
        if(marg == null)
        {
            System.err.println("Parameter MARG ikke angitt");
            this.marg = 0;
        }
        else
        {
            try { this.marg = Integer.parseInt(marg); }
            catch(NumberFormatException e)
            { 
                System.err.println("MARG-parameteren m� v�re et heltall"); 
                this.marg = 0;
            }                
        }  
        
        // sett tittelfont
        String tittelFont = getParameter("TITTELFONT");
        String tittelSt�rrelse = getParameter("TITTELST�RRELSE");
        String tittelStyle = getParameter("TITTELSTYLE");
        if(tittelFont == null)
        {
            System.err.println("Parameter TITTELFONT ikke angitt");
            tittelFont = "SansSerif";
        }
        if(tittelSt�rrelse == null)
        {
            System.err.println("Parameter TITTELST�RRELSE ikke angitt");
            tittelSt�rrelse = "10";
        }        
        if(tittelStyle == null)
        {
            System.err.println("Parameter TITTELSTYLE ikke angitt");
            tittelStyle = "PLAIN";
        }
        this.tittelFont = lagFont(tittelFont, tittelSt�rrelse, tittelStyle);
        
        // sett alternativavstand
        String alternativAvstand = getParameter("ALTERNATIVAVSTAND");
        if(alternativAvstand == null)
        {
            System.err.println("Parameter ALTERNATIVAVSTAND ikke angitt");
            this.alternativAvstand = 10;
        }
        else
        {
            try { this.alternativAvstand = Integer.parseInt(alternativAvstand); }
            catch(NumberFormatException e)
            {
                System.err.println("Parameteren ALTERNATIVAVSTAND m� v�re et heltall");
                this.alternativAvstand = 10;
            }
        }
        
        // sett tekst/s�yle-avstand
        String tekstS�yleAvstand = getParameter("TEKSTS�YLEAVSTAND");
        if(tekstS�yleAvstand == null)
        {
            System.err.println("Parameter TEKSTS�YLEAVSTAND ikke angitt");
            this.tekstS�yleAvstand = 2;
        }
        else
        {
            try { this.tekstS�yleAvstand = Integer.parseInt(tekstS�yleAvstand); }
            catch(NumberFormatException e)
            {
                System.err.println("Parameteren TEKSTS�YLEAVSTAND m� v�re et heltall");
                this.tekstS�yleAvstand = 2;
            }            
        }
        
        // sett s�yleh�yde
        String s�yleH�yde = getParameter("S�YLEH�YDE");
        if(s�yleH�yde == null)
        {
            System.err.println("Parameter S�YLEH�YDE ikke angitt");
            this.s�yleH�yde = 7;            
        }
        else
        {
            try { this.s�yleH�yde = Integer.parseInt(s�yleH�yde); }
            catch(NumberFormatException e)
            {
                System.err.println("Parameteren S�YLEH�YDE m� v�re et heltall");
                this.s�yleH�yde = 7;
            }                        
        }
        
        // sett 3D-effekt
        String treD = getParameter("3DEFFEKT");
        if(treD == null)
        {
            System.err.println("Parameter 3DEFFEKT ikke angitt");
            this.treD = false;
        }
        else
        {
            if(treD.equalsIgnoreCase("false"))
                this.treD = false;
            else
                this.treD = true;
        }
        
        // sett visAntallStemmer
        String visAntallStemmer = getParameter("VISANTALLSTEMMER");
        if(visAntallStemmer == null)
        {
            System.err.println("Parameter VISANTALLSTEMMER ikke angitt");
            this.visAntallStemmer = false;
        }
        else
        {
            if(visAntallStemmer.equalsIgnoreCase("false"))
                this.visAntallStemmer = false;
            else
                this.visAntallStemmer = true;
        }
    }
    
    //--------------------------------------------------------------------------
    // Leser tittel og stemmer for hvert alternativ. Skal angis som paramtre.
    //--------------------------------------------------------------------------
    private void lesTittelOgStemmer()
    {
        int antall = 0;
        boolean flere = true;
        
        while(flere)
        {            
            String tittel = getParameter("TITTEL" + (antall+1));
            String stemmer = getParameter("STEMMER" + (antall+1));
            
            if(tittel == null && stemmer == null)
            {
                flere = false;
            }
            else
            {
                antall++;
                
                int tall = 0;
                try { tall = Integer.parseInt(stemmer); }
                catch(NumberFormatException e)
                {
                    System.err.println("Stemmer nummer " + antall + " var ikke angitt som heltall");
                    tall = 0;
                }
                
                addResultat(tittel, tall);                
            }
        }
    }
    
    //--------------------------------------------------------------------------
    //  Legger til nytt element i array
    //--------------------------------------------------------------------------
    private void addResultat(String str, int nr)
    {
        String[] tempStr = strTittel;
        int[] tempNr = stemmer;
        
        if(strTittel == null)
        {
            strTittel = new String[1];
            stemmer = new int[1];
            strTittel[strTittel.length-1] = str;
            stemmer[stemmer.length-1] = nr;            
        }
        else
        {
            strTittel = new String[tempStr.length+1];
            stemmer = new int[tempNr.length+1];
              
            for(int i=0; i < tempStr.length; i++)
            {
                strTittel[i] = tempStr[i];
                stemmer[i] = tempNr[i];
            }

            strTittel[strTittel.length-1] = str;
            stemmer[stemmer.length-1] = nr;
        }
    }
    
    //--------------------------------------------------------------------------
    //  Konverterer en string (#ff00ff / ff00ff) til et fargeobjekt
    //--------------------------------------------------------------------------
    private Color lagFarge(String str)
    {
        if(str == null)
            return new Color(0, 0, 0);
        
        int start = 0;
        if((str.substring(0, 1)).equals("#"))
            start = 1;
        
        String r = str.substring(start, start + 2);
        String g = str.substring(start + 2, start + 4);
        String b = str.substring(start + 4, start + 6);
        HexTilInt r�d = new HexTilInt(r);
        HexTilInt gr�nn = new HexTilInt(g);
        HexTilInt bl� = new HexTilInt(b);
        
        return new Color(r�d.getInt(), gr�nn.getInt(), bl�.getInt());
    }
    
    //--------------------------------------------------------------------------
    //  Konverterer parametere til et Font-objekt
    //--------------------------------------------------------------------------
    private Font lagFont(String font, String st�rrelse, String stil)
    {
        Font f = new Font(font, Font.PLAIN, 10);
        
        if(!font.equalsIgnoreCase(f.getFamily()))
        {
            System.err.println("Kunne ikke finne font: " + font + ". SansSerif brukes istedet.");
            font = "SansSerif";
        }
        
        int size = 0;
        try { size = Integer.parseInt(st�rrelse); }
        catch(NumberFormatException e)
        {
            System.err.println("Parameteren TITTELST�RRELSE m� v�re et heltall");
            size = 10;
        }
        
        int style = Font.PLAIN;
        if(stil != null)
        {
            if(stil.equalsIgnoreCase("PLAIN"))
                style = Font.PLAIN;
            else if(stil.equalsIgnoreCase("BOLD"))
                style = Font.BOLD;
            else if(stil.equalsIgnoreCase("ITALIC"))
                style = Font.ITALIC;
            else
                System.err.println("Parameteren TITTELSTYLE kan v�re PLAIN, BOLD eller ITALIC");
        }
        f = new Font(font, style, size);
        
        return f;
    }
    
    //--------------------------------------------------------------------------
    // Lager titler til � beskrive s�ylene
    //--------------------------------------------------------------------------
    private void lagTitler()
    {
        for(int i=0; i < tittel.length; i++)
        {
            int x = marg;
            int y = marg + tittelFont.getSize() * (i+1) + tekstS�yleAvstand * i + s�yleH�yde * i;

            if(i >=1)
                y += alternativAvstand * i;
            
            if(visAntallStemmer)
                strTittel[i] += ": " + stemmer[i];
            
            tittel[i] = new Tittel(x, y, strTittel[i], tittelFont, tittelFarge);
        }
    }
    
    //--------------------------------------------------------------------------
    // Lager s�ylene
    //--------------------------------------------------------------------------
    private void lagS�yler()
    {
        for(int i=0; i < s�yle.length; i++)
        {
            int x = marg;
            int y = marg + tittelFont.getSize() * (i+1) + s�yleH�yde * i + tekstS�yleAvstand * (i+1);
            int lengde;
            try
            {
                lengde = (int)(((bredde - (marg*2)) * stemmer[i]) / antallStemmer);
            }
            catch(ArithmeticException e)
            {
                // antallStemmer = 0, pr�ver � dele med 0
                lengde = 0;
            }

            int h�yde = s�yleH�yde;
            
            if(i >= 1)
                y += alternativAvstand * i;
            
            s�yle[i] = new S�yle(x, y, lengde, h�yde, s�yleFarge, treD);
        }
    }
}
