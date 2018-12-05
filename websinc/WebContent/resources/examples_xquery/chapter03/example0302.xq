(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 3-2. Conditional expression returning multiple expressions :)
for $prod in (doc("catalog.xml")/catalog/product)
return if ($prod/@dept = 'ACC')
       then (<accessoryNum>{data($prod/number)}</accessoryNum>,
            <accessoryName>{data($prod/name)}</accessoryName>)
       else <otherNum>{data($prod/number)}</otherNum>



