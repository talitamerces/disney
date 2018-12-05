(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 14-7. A quantified expression with a type declaration :)

every $number as element(*,xs:integer) in
    doc("catalog.xml")//number satisfies ($number > 0)

