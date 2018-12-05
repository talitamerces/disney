(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 12-2. A query body :)

<title>Order Report</title>,
(for $item in doc("order.xml")//item
 order by $item/@num
 return $item)
