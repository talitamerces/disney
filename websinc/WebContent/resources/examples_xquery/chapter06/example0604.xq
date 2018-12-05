(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 6-4. Multiple variable bindings in one for clause :)
for $i in (1, 2), $j in ("a", "b")
return <oneEval>i is {$i} and j is {$j}</oneEval>


