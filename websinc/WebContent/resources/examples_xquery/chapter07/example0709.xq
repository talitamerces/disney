(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 7-9. Grouping by department :)
for $d in distinct-values(doc("order.xml")//item/@dept)
let $items := doc("order.xml")//item[@dept = $d]
order by $d
return <department code="{$d}">{
         for $i in $items
         order by $i/@num
         return $i
       }</department>
