<html>
<body>


<APPLET codebase="http://www.mo.himolde.no/~eivindg/br2/" class="ResultatApplet.class" archive="http://www.mo.himolde.no/~eivindg/br2/brukerundersokelse.jar" width=220 height=280>
<PARAM NAME=CODE VALUE="ResultatApplet.class">
<PARAM NAME=CODEBASE VALUE="http://www.mo.himolde.no/~eivindg/br2">
<PARAM NAME=ARCHIVE VALUE="http://www.mo.himolde.no/~eivindg/br2/brukerundersokelse.jar">
<PARAM NAME="type" VALUE="application/x-java-applet;version=1.3">
<PARAM NAME="scriptable" VALUE="false">
<PARAM name="BAKGRUNNSFARGE" value="#3870a8">
<PARAM name="SØYLEFARGE" value="#d1e1f1">
<PARAM name="TITTELFARGE" value="#60cf2f">
<PARAM name="MARG" value="5">
<PARAM name="TITTELFONT" value="Verdana">
<PARAM name="TITTELSTØRRELSE" value="10">
<PARAM name="TITTELSTYLE" value="BOLD">
<PARAM name="ALTERNATIVAVSTAND" value="10">
<PARAM name="TEKSTSØYLEAVSTAND" value="3">
<PARAM name="SØYLEHØYDE" value="7">
<PARAM name="3DEFFEKT" value="true">
<PARAM name="VISANTALLSTEMMER" value="true">


<?
	$antall = 0;
	
	$alternativer = array();
	$stemmer = array();
	
	lesFraFil($alternativer, $stemmer, $antall);
	
	addStemme($antall, $stemmer, $radiobutton);
	
	lagreTilFil($alternativer, $stemmer, $antall, $radiobutton);
	
	printParameter($alternativer, $stemmer, $antall);
	
	// skriver ut PARAM-tagger til appleten
	function printParameter(&$alt, $stm, $ant)
	{
		for($i=0; $i < $ant; $i++)
		{
			$nr = $i + 1;
			print "<PARAM name=\"TITTEL$nr\" value=\"$alt[$i]\">\n";
			print "<PARAM name=\"STEMMER$nr\" value=\"$stm[$i]\">\n";
		}
	}
	
	// legger til en stemme til riktig alternativ
	function addStemme(&$ant, &$stm, &$knapp)
	{
		for($i=0; $i < $ant; $i++)
		{
			if(!strcasecmp($knapp, "$i"))
			{
				$tall = $stm[$i];
				settype($tall, "integer");
				$tall++;
				$stm[$i] = $tall;
			}
		}
	}
	
	// lagrer alt til filen igjen
	function lagreTilFil(&$alt, &$stm, &$ant, &$knapp)
	{
		$fd = fopen("alternativer.txt", "wb");
		
		for($i=0; $i < $ant; $i++)
		{
			if(!strcasecmp($knapp, "$i"))
			{
				fputs($fd, $alt[$i]."=".$stm[$i]."\n");
			}
			else
			{
				fputs($fd, $alt[$i]."=".$stm[$i]);
			}
		}
		fclose($fd);	
	}


	// leser tidligere stemmer fra fil
	function lesFraFil(&$alternativer, &$stemmer, &$antall)
	{
		$linje = '.';
		$fd = fopen("alternativer.txt", "rb");
		while($linje != null)
		{
			$linje = fgets($fd, 4096);			
			$alt = getAlternativ($linje);
			$stm = getStemmer($linje);
			
			array_push($alternativer, $alt);
			array_push($stemmer, $stm);
			$antall++;
		}	
		fclose($fd);
		$antall--;
	}
			
	// henter ut tekst foran '='
	function getAlternativ($str)
	{
		$pos = strpos($str, "=");
		if($pos == false)
		{
			return false;		
		}
		if(!is_integer($pos))
		{
			return false;
		}
		
		$alternativ = substr($str, 0, $pos);
		return $alternativ;
	}
	
	// henter ut tekst etter '='
	function getStemmer($str)
	{
		$pos = strpos($str, "=");
		if($pos == false)
		{
			return false;		
		}
		if(!is_integer($pos))
		{
			return false;
		}
		
		$stemmer = substr($str, $pos+1);
		return $stemmer;		
	}

?>

</APPLET>
</body>
</html>