(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 5-3. Constructing elements using XML-like syntax :)
<html>
  <h1>Product Catalog</h1>
  <ul>{
    for $prod in doc("catalog.xml")/catalog/product
    return <li>number: {data($prod/number)}, name: {data($prod/name)}</li>
  }</ul>
</html>



