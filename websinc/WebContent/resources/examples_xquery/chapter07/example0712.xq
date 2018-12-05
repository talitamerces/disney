(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 7-12. Constraining and sorting on aggregated values :)
let $allItems := doc("order.xml")//item
for $d in distinct-values($allItems/@dept)
for $n in distinct-values($allItems/@num)
let $items := $allItems[@dept = $d and @num = $n]
where sum($items/@quantity) > 1
order by count($items)
return if (exists($items))
       then <group dept="{$d}" num="{$n}" numItems="{count($items)}"
                   totQuant="{sum($items/@quantity)}"/>
       else ()
