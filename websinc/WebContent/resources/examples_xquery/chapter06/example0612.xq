(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 6-12. Distinctness on a combination of values :)
let $prods := doc("catalog.xml")//product
for $d in distinct-values($prods/@dept),
    $n in distinct-values($prods[@dept = $d]/number)
return <result dept="{$d}" number="{$n}"/>
