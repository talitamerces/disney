(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 6-6. Intermingled for and let clauses :)
let $doc := doc("catalog.xml")
for $prod in $doc//product
let $prodDept := $prod/@dept
let $prodName := $prod/name
where $prodDept = "ACC" or $prodDept = "WMN"
return $prodName



