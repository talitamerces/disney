(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 1-6. Adding a let clause :)
for $product in doc("catalog.xml")/catalog/product
let $name := $product/name
where $product/@dept = "ACC"
order by $name
return $name





