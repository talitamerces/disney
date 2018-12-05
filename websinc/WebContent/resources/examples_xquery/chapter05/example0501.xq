(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 5-1. Including elements from the input document :)
for $prod in doc("catalog.xml")/catalog/product[@dept = 'ACC']
return $prod/name


