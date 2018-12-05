(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-11. Attempting to use a positional variable with a where clause :)
for $prod at $count in doc("catalog.xml")//product
where $prod/@dept = ("ACC", "MEN")
order by $prod/name
return <p>{$count}. {data($prod/name)}</p>

