(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 21-13. Query with CDATA section :)

if (doc("catalog.xml")//product)
then <h1><![CDATA[Catalog & Price List from <catalog>]]></h1>
else <h1>No catalog items to display</h1>

