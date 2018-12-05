(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 6-7. A where clause with multiple expressions :)
for $prod in doc("catalog.xml")//product
let $prodDept := $prod/@dept
where $prod/number > 100
      and starts-with($prod/name, "F")
      and exists($prod/colorChoices)
      and ($prodDept = "ACC" or $prodDept = "WMN")
return $prod




