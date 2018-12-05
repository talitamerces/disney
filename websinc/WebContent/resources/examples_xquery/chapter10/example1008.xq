(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 10-8. Namespace declarations in query different from input document :)

declare namespace rep = "http://datypic.com/report";
declare namespace cat = "http://datypic.com/cat";
declare namespace prod2 = "http://datypic.com/prod";
<rep:report> {
  doc("cat_ns.xml")/cat:catalog/prod2:product
} </rep:report>
