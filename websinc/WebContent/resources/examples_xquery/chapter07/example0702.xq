(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 7-2. Using multiple ordering specifications :)
for $item in doc("order.xml")//item
order by $item/@dept, $item/@num
return $item





