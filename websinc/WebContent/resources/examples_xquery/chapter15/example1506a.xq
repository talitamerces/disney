(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 15-6. Use predicates instead of where clauses :)

(: Less efficient query :)

for $prod in doc("catalog.xml")//product
where $prod/@dept = "ACC"
order by $prod/name
return $prod/name
