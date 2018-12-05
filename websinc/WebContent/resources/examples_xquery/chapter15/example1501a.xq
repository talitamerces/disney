(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 15-1. Making use of whitespace :)

(: Less clear query :)

for $product in doc("catalog.xml")//product return 
<product><number>{$product/number}</number>
<price>{for $price in doc("prices.xml")//prod
where $product/number = $price/@num
return $price/price}</price>
</product>
