(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Useful-function: avg-empty-is-zero :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:avg-empty-is-zero
 ($allNodes as node()*, $values as xs:anyAtomicType*) as xs:double {
   if (empty($allNodes))
   then 0
   else sum($values[. != ""]) div count($allNodes)
};

(: Example call :)
let $prods := doc("prices.xml")//prod
return (functx:avg-empty-is-zero($prods, $prods/discount))







