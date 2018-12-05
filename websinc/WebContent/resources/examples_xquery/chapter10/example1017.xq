(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 10-17. Query with preserve, no-inherit :)

declare copy-namespaces preserve, no-inherit;
<report xmlns="http://datypic.com/report"
           xmlns:cat="http://datypic.com/cat"
           xmlns:prodnew="http://datypic.com/prod"> {
  doc("cat_ns2.xml")//prodnew:product
} </report>


