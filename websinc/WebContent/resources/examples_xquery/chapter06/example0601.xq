(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 6-1. FLWOR :)
for $prod in doc("catalog.xml")//product
let $prodDept := $prod/@dept
where $prodDept = "ACC" or $prodDept = "WMN"
return $prod/name
