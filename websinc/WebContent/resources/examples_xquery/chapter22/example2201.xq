(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 22-1. Simple FLWOR :)

for $product in doc("catalog.xml")//product
order by $product/name
return $product/number
