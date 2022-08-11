<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
	<title>Uten tittel</title>
	<link rel="STYLESHEET" href="http://home.online.no/~gusloek/formateringer.css" type="text/css">
</head>

<body bgcolor="#3870a8" leftmargin="0" topmargin="0">

<table width="250" border="0" cellspacing="0" cellpadding="3">
  <tr>
    	
    <td bgcolor="#FFFFFF">
      <table width="100%" border="0" cellspacing="0" cellpadding="3">
        <tr>
          <td bgcolor="#3870a8">
            <div align="center" class="underoverskrift">Brukerunders&oslash;kelse</div>
          </td>
        </tr>
        <tr>
          <td class="ingress" valign="top" align="center">Ser du denne brukerunders&oslash;kelsen?<br>
            <br>
            <APPLET codebase="http://embla.mo.himolde.no/~eivindg/annonse" class="ResultatApplet.class" archive="http://embla.mo.himolde.no/~eivindg/annonse/brukerundersokelse.jar" width=238 height=106>
              <PARAM NAME=CODE VALUE="ResultatApplet.class">
					<PARAM NAME=CODEBASE VALUE="http://embla.mo.himolde.no/~eivindg/annonse">
					<PARAM NAME=ARCHIVE VALUE="http://embla.mo.himolde.no/~eivindg/annonse/brukerundersokelse.jar">
					<PARAM NAME="type" VALUE="application/x-java-applet;version=1.3">
					<PARAM NAME="scriptable" VALUE="false">
					<PARAM name="BAKGRUNNSFARGE" value="#ffffff">
					<PARAM name="SØYLEFARGE" value="#3870a8">
					<PARAM name="TITTELFARGE" value="#000000">
					<PARAM name="MARG" value="0">
					<PARAM name="TITTELFONT" value="Verdana">
					<PARAM name="TITTELSTØRRELSE" value="10">
					<PARAM name="TITTELSTYLE" value="PLAIN">
					<PARAM name="ALTERNATIVAVSTAND" value="8">
					<PARAM name="TEKSTSØYLEAVSTAND" value="3">
					<PARAM name="SØYLEHØYDE" value="9">
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
					
					</APPLET><br>
				
            <p class="tekst"><a href="http://home.online.no/%7Egusloek/annonse.html">Tilbake</a></p>		  
          </td>
        </tr>
      </table>
    </td>
  	</tr>
</table>

&nbsp;<br>

<table width="250" border="0" cellspacing="0" cellpadding="0"><tr><td align="center">
<table border="0" cellspacing="0" cellpadding="3" bgcolor="#ffffff"><tr>
          <td align="center" valign="middle"><a href="http://www.nrk.no/kanal/nrk_mpetre/" target="new"><img src="http://home.online.no/~gusloek/mpetre.gif" width="225" height="28" border="0" alt="Den beste musikken på planeten!!!"></a></td>
</tr></table>
</td></tr>
<tr><td align="center"><table border="0" cellspacing="0" cellpadding="3" bgcolor="#ffffff"><tr>
		  <td><div class="tekst">&nbsp;&nbsp;<a href="http://media.hiof.no/streams/nrk-mpetre-24.m3u" target="new" class="menyvalg" alt="Streaming for modem">28.8</a>&nbsp;&nbsp;</div></td>
		  <td>
            <div class="tekst">&nbsp;&nbsp;<a href="http://media.hiof.no/streams/nrk-mpetre-56.m3u" target="new" class="menyvalg" alt="Streaming for ISDN">56</a>&nbsp;&nbsp;</div>
          </td>
		  <td>
            <div class="tekst">&nbsp;&nbsp;<a href="http://media.hiof.no/streams/nrk-mpetre-128.m3u" target="new" class="menyvalg" alt="Streaming for ADSL">128</a>&nbsp;&nbsp;</div>
          </td>
<tr></table>
</td></tr></table>

&nbsp;<br>

<table width="250" border="0" cellspacing="0" cellpadding="0"><tr><td align="center">
<table border="0" cellspacing="0" cellpadding="3" bgcolor="#ffffff"><tr>
          <td align="center" valign="middle"><a href="http://bunnz.oldiesoracle.net/" target="_blank"><img src="http://home.online.no/~gusloek/bunny_abondonware.gif" width="103" height="30" border="0" alt="Mange gamle spill, kan lastes ned gratis."></a></td>
</tr></table>
</td></tr></table>

&nbsp;<br>

<table width="250" border="0" cellspacing="0" cellpadding="0"><tr><td align="center">
<table border="0" cellspacing="0" cellpadding="3" bgcolor="#ffffff"><tr>
          <td align="center" valign="middle"><a href="http://www.nessetbanken.no" target="new"><img src="http://home.online.no/~gusloek/nessetbanken_vinter.jpg" width="225" height="78" border="0" alt="Nesset Sparebank's hjemmeside"></a></td>
</tr></table>
</td></tr></table>

</body>
</html>
