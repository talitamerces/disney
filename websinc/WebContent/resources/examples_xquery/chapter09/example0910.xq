(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-10. Using a positional variable in a for clause :)
for $prod at $count in doc("catalog.xml")//product[@dept = ("ACC", "WMN")]
return <p>{$count}. {data($prod/name)}</p>

