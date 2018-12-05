(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 7-11. Aggregation on multiple values :)
let $allItems := doc("order.xml")//item
for $d in distinct-values($allItems/@dept)
for $n in distinct-values($allItems[@dept = $d]/@num)
let $items := $allItems[@dept = $d and @num = $n]
order by $d, $n
return <group dept="{$d}" num="{$n}"
              numItems="{count($items)}"
              totQuant="{sum($items/@quantity)}"/>
