
<script language="JavaScript">
<!--


/*
BrowserDetector()
Parses User-Agent string into useful info.

Source: Webmonkey Code Library
(http://www.hotwired.com/webmonkey/javascript/code_library/)

Author: Richard Blaylock
Author Email: blaylock@wired.com

Usage: var bd = new BrowserDetector(navigator.userAgent);
*/


// Utility function to trim spaces from both ends of a string
function Trim(inString) {
  var retVal = "";
  var start = 0;
  while ((start < inString.length) && (inString.charAt(start) == ' ')) {
    ++start;
  }
  var end = inString.length;
  while ((end > 0) && (inString.charAt(end - 1) == ' ')) {
    --end;
  }
  retVal = inString.substring(start, end);
  return retVal;
}

function BrowserDetector(ua) {

// Defaults
  this.browser = "Unknown";
  this.platform = "Unknown";
  this.version = "";
  this.majorver = "";
  this.minorver = "";

  uaLen = ua.length;

// ##### Split into stuff before parens and stuff in parens
  var preparens = "";
  var parenthesized = "";

  i = ua.indexOf("(");
  if (i >= 0) {
    preparens = Trim(ua.substring(0,i));
        parenthesized = ua.substring(i+1, uaLen);
        j = parenthesized.indexOf(")");
        if (j >= 0) {
          parenthesized = parenthesized.substring(0, j);
        }
  }
  else {
    preparens = ua;
  }

// ##### First assume browser and version are in preparens
// ##### override later if we find them in the parenthesized stuff
  var browVer = preparens;

  var tokens = parenthesized.split(";");
  var token = "";
// # Now go through parenthesized tokens
  for (var i=0; i < tokens.length; i++) {
    token = Trim(tokens[i]);
        //## compatible - might want to reset from Netscape
        if (token == "compatible") {
          //## One might want to reset browVer to a null string
          //## here, but instead, we'll assume that if we don't
          //## find out otherwise, then it really is Mozilla
          //## (or whatever showed up before the parens).
        //## browser - try for Opera or IE
    }
        else if (token.indexOf("MSIE") >= 0) {
      browVer = token;
    }
    else if (token.indexOf("Opera") >= 0) {
      browVer = token;
    }
        //'## platform - try for X11, SunOS, Win, Mac, PPC
    else if ((token.indexOf("X11") >= 0) || (token.indexOf("SunOS") >= 0) ||
(token.indexOf("Linux") >= 0)) {
      this.platform = "Unix";
        }
    else if (token.indexOf("Win") >= 0) {
      this.platform = token;
        }
    else if ((token.indexOf("Mac") >= 0) || (token.indexOf("PPC") >= 0)) {
      this.platform = token;
        }
  }

  var msieIndex = browVer.indexOf("MSIE");
  if (msieIndex >= 0) {
    browVer = browVer.substring(msieIndex, browVer.length);
  }

  var leftover = "";
  if (browVer.substring(0, "Mozilla".length) == "Mozilla") {
    this.browser = "Netscape";
        leftover = browVer.substring("Mozilla".length+1, browVer.length);
  }
  else if (browVer.substring(0, "Lynx".length) == "Lynx") {
    this.browser = "Lynx";
        leftover = browVer.substring("Lynx".length+1, browVer.length);
  }
  else if (browVer.substring(0, "MSIE".length) == "MSIE") {
    this.browser = "IE";
    leftover = browVer.substring("MSIE".length+1, browVer.length);
  }
  else if (browVer.substring(0, "Microsoft Internet Explorer".length) ==
"Microsoft Internet Explorer") {
    this.browser = "IE"
        leftover = browVer.substring("Microsoft Internet Explorer".length+1,
browVer.length);
  }
  else if (browVer.substring(0, "Opera".length) == "Opera") {
    this.browser = "Opera"
    leftover = browVer.substring("Opera".length+1, browVer.length);
  }

  leftover = Trim(leftover);

  // # Try to get version info out of leftover stuff
  i = leftover.indexOf(" ");
  if (i >= 0) {
    this.version = leftover.substring(0, i);
  }
  else
  {
    this.version = leftover;
  }
  j = this.version.indexOf(".");
  if (j >= 0) {
    this.majorver = this.version.substring(0,j);
    this.minorver = this.version.substring(j+1, this.version.length);
  }
  else {
    this.majorver = this.version;
  }


} // function BrowserCap



// -->

</script>

