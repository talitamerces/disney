(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 1-12. Aggregating values :)
for $d in distinct-values(doc("order.xml")//item/@dept)
let $items := doc("order.xml")//item[@dept = $d]
order by $d
return <department name="{$d}" totQuantity="{sum($items/@quantity)}"/>











