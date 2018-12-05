(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 15-2.Documenting a function with xqdoc :)

(:~
: The <b>functx:substring-after-last</b> function returns the part
: of <b>$string</b> that appears after the last occurrence of 
: <b>$delim</b>. If <b>$string</b> does not contain 
: <b>$delim</b>, the entire string is returned. 
:
: @param $string the string to substring
: @param $delim the delimiter
: @return the substring
:)
declare function functx:substring-after-last
($string as xs:string?, $delim as xs:string) as xs:string?
 { ...  };
