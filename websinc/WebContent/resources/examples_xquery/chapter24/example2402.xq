(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 24-2. Distinctness on a combination of values :)

for $d in distinct-values(doc("catalog2.xml")//product/@dept),
    $n in distinct-values(doc("catalog2.xml")//product[@dept = $d]/number)
return <result dept="{$d}" number="{$n}"/>

