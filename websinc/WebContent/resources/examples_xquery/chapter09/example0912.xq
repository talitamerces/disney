(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-12. Embedding the where clause :)
let $sortedProds := for $prod in doc("catalog.xml")//product
                    where $prod/@dept = "ACC" or $prod/@dept = "MEN"
                    order by $prod/name
                    return $prod
for $sortedProd at $count in $sortedProds
return <p>{$count}. {data($sortedProd/name)}</p>
