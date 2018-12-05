(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 6-3. Multiple for clauses :)
for $i in (1, 2)
for $j in ("a", "b")
return <oneEval>i is {$i} and j is {$j}</oneEval>

