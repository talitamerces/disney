(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 5-7. Enclosed expressions with multiple subexpressions :)
for $prod in doc("catalog.xml")/catalog/product
return <li>{$prod/@dept,"string",5+3,$prod/number}</li>






