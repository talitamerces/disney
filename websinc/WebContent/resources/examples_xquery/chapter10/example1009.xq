(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 10-9. Using namespace declaration attributes :)

<rep:report xmlns="http://datypic.com/cat"
            xmlns:prod="http://datypic.com/prod"
            xmlns:rep="http://datypic.com/report"> {
  doc("cat_ns.xml")/catalog/prod:product
} </rep:report>
