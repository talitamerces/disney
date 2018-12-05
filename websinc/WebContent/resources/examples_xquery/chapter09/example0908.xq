(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-8. Attempting to use a counter variable :)
let $count := 0
for $prod in doc("catalog.xml")//product[@dept = ("ACC", "WMN")]
let $count := $count + 1
return <p>{$count}. {data($prod/name)}</p>
