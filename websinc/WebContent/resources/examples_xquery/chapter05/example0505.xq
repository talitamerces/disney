(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 5-5. Enclosed expressions that evaluate to elements :)
for $prod in doc("catalog.xml")/catalog/product
return <li>number: {$prod/number}</li>




