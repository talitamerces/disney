(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 1-10. Adding attributes to results :)
<ul type="square">{
  for $product in doc("catalog.xml")/catalog/product
  where $product/@dept='ACC'
  order by $product/name
  return <li class="{$product/@dept}">{data($product/name)}</li>
}</ul>









