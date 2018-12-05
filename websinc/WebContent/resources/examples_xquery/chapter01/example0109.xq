(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 1-9. Using the data function :)
<ul>{
  for $product in doc("catalog.xml")/catalog/product
  where $product/@dept='ACC'
  order by $product/name
  return <li>{data($product/name)}</li>
}</ul>








