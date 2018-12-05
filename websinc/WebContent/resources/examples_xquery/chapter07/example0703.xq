(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 7-3. Using an empty order declaration :)
declare default order empty greatest;
for $item in doc("order.xml")//item
order by $item/@color
return $item





