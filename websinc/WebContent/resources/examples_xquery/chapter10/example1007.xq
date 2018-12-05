(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 10-7. Prolog default namespace declaration :)

declare default element namespace "http://datypic.com/cat";
declare namespace rep = "http://datypic.com/report";
declare namespace prod = "http://datypic.com/prod";
<rep:report> {
  doc("cat_ns.xml")/catalog/prod:product
} </rep:report>
