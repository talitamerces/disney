(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 7-6. Using an order comparison :)
let $prods := doc("catalog.xml")//product
for $prod in $prods
where $prod << $prods[@dept = $prod/@dept][last()]
return $prod








