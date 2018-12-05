(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 5-6. Enclosed expressions that evaluate to attributes :)
for $prod in doc("catalog.xml")/catalog/product
return <li>{$prod/@dept}number: {$prod/number}</li>





