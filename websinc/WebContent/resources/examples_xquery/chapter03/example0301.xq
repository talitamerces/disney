(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 3-1. Conditional expression :)
for $prod in (doc("catalog.xml")/catalog/product)
return if ($prod/@dept = 'ACC')
       then <accessoryNum>{data($prod/number)}</accessoryNum>
       else <otherNum>{data($prod/number)}</otherNum>


