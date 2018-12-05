(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 1-8. Element constructor in FLWOR return clause :)
<ul>{
  for $product in doc("catalog.xml")/catalog/product
  where $product/@dept='ACC'
  order by $product/name
  return <li>{$product/name}</li>
}</ul>







