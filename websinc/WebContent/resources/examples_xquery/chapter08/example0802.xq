(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 8-2. Handling the empty sequence :)
declare function local:discountPrice(
  $price as xs:decimal?,
  $discount as xs:decimal?,
  $maxDiscountPct as xs:integer?) as xs:double?
{
   let $newDiscount    := if ($discount) then $discount else 0
   let $maxDiscount    := if ($maxDiscountPct)
                          then ($price * $maxDiscountPct) div 100
                          else 0
   let $actualDiscount := min(($maxDiscount, $discount))
   return ($price - $actualDiscount)
};

(:line below inserted to give $prod a value :)
let $prod := doc("prices.xml")//prod[1] return
local:discountPrice($prod/price, $prod/discount, 15)
