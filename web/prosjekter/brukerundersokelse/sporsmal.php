<html>
<head>
<title>Sp&oslash;rreunders&oslash;kelse</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#3870a8" text="#FFFFFF">
<p><b>Hva synes du om denne brukerunders&oslash;kelsen?</b></p>
<form name="form1" method="post" action="resultat.php" target="_blank">
  <table width="300" border="0" cellspacing="3" cellpadding="3">
    <tr>
      <td>
	<?
		$antall = 0;
		$alternativer = array();
		
		lesFraFil($alternativer, $antall);
		lagAlternativer($alternativer, $antall);
		
		function lesFraFil(&$alt, &$ant)
		{
			$linje = '.';
			$fd = fopen("alternativer.txt", "rb");
			while($linje != null)
			{
				$linje = fgets($fd, 4096);			
				$alt2 = getAlternativ($linje);

				array_push($alt, $alt2);
				$ant++;
			}	
			fclose($fd);
			$ant--;			
		}
		
		
		function lagAlternativer(&$alt, &$ant)
		{
			for($i=0; $i < $ant; $i++)
			{
				print "<input type=\"radio\" name=\"radiobutton\" value=\"$i\">\n";
				print "<b>$alt[$i]</b><br>\n";
			}
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
	?>
	
      </td>
    </tr>
    <tr>
      <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="3">
          <tr>
            <td>
              <input type="submit" name="cmdSend" value="Send svar">
            </td>
            <td>
              <input type="reset" name="cmdNullstill" value="Fjern valg">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
<p><b>Klikk her hvis du vil <a href="resultat.php" target="_blank">se resultatet.</a></b></p>


</body>
</html>