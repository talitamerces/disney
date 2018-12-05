(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 15-6. Use predicates instead of where clauses :)

(: More efficient query :)

for $prod in doc("catalog.xml")//product[@dept = "ACC"]
order by $prod/name
return $prod/name
