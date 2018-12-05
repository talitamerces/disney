(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 7-1. The order by clause :)
for $item in doc("order.xml")//item
order by $item/@num
return $item





