(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 7-10. Aggregation :)
for $d in distinct-values(doc("order.xml")//item/@dept)
let $items := doc("order.xml")//item[@dept = $d]
order by $d
return <department code="{$d}"
                   numItems="{count($items)}"
                   distinctItemNums="{count(distinct-values($items/@num))}"
                   totQuant="{sum($items/@quantity)}"/>
