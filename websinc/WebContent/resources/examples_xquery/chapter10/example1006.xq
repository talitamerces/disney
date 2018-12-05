(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 10-6. Prolog namespace declarations :)

declare namespace rep = "http://datypic.com/report";
declare namespace prod = "http://datypic.com/prod";
<rep:report> {
  doc("cat_ns.xml")//prod:product
} </rep:report>
