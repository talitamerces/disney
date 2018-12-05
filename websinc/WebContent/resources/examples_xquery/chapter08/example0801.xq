(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 8-1. A function declaration :)
declare function local:discountPrice(
  $price as xs:decimal?,
  $discount as xs:decimal?,
  $maxDiscountPct as xs:integer?) as xs:decimal?
{
   let $maxDiscount := ($price * $maxDiscountPct) div 100
   let $actualDiscount := min(($maxDiscount, $discount))
   return ($price - $actualDiscount)
};

(:line below inserted to give $prod a value :)
let $prod := doc("prices.xml")//prod[1] return
local:discountPrice($prod/price, $prod/discount, 15)
