(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 6-11. Binding multiple variables in a quantified expression :)
some $i in (1 to 3), $j in (10, 11)
  satisfies $j - $i = 7