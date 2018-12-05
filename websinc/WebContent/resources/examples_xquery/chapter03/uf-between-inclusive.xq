(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Useful-function: between-inclusive :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:between-inclusive
 ($value as xs:anyAtomicType, $minValue as xs:anyAtomicType,
  $maxValue as xs:anyAtomicType) as xs:boolean  {

   $value >= $minValue and $value <= $maxValue

 };
(: Example call :)
let $prod := doc("catalog.xml")//product[1]
return functx:between-inclusive ($prod/number, 1, 500)






