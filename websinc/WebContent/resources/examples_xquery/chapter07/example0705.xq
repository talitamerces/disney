(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 7-5. FLWOR without inadvertent resorting :)
for $prod in doc("catalog.xml")//product
order by $prod/number
return <li>{string($prod/name)}</li>







