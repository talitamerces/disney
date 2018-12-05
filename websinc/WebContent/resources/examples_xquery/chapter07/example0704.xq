(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 7-4. Inadvertent resorting in document order :)
let $sortedProds := for $prod in doc("catalog.xml")//product
                    order by $prod/number
                    return $prod
for $prodName in $sortedProds/name
return <li>{string($prodName)}</li>






