(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 21-11. Query with XML entities :)

if (doc("catalog.xml")//product[@dept='&#65;CC'])
then <h1>Accessories &amp; Misc&#x20;List from &lt;catalog&gt;</h1>
else ()
