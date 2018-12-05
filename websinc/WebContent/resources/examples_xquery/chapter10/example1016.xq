(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 10-16. Query with no-preserve, inherit :)

declare copy-namespaces no-preserve, inherit;
<report xmlns="http://datypic.com/report"
        xmlns:cat="http://datypic.com/cat"
        xmlns:prodnew="http://datypic.com/prod"> {
  doc("cat_ns2.xml")//prodnew:product
} </report>

