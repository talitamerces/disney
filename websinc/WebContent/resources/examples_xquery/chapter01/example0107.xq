(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 1-7. Wrapping results in a new element :)
<ul>{
  for $product in doc("catalog.xml")/catalog/product
  where $product/@dept='ACC'
  order by $product/name
  return $product/name
}</ul>






