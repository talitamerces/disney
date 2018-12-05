(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Useful-function: if-absent :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:if-absent (
  $node as node()?, $value as xs:anyAtomicType) as xs:anyAtomicType*
{
  if ($node)
  then data($node)
  else $value
};

(: Example call :)
for $prod in doc("prices.xml")//prod
return $prod/price - functx:if-absent($prod/discount, 0)






