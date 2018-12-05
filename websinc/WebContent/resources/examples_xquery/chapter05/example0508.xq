(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 5-8. Specifying attributes directly using XML-like syntax :)
<html>
<h1 class="itemHdr">Product Catalog</h1>
<ul>{
  for $prod in doc("catalog.xml")/catalog/product
  return <li dep="{$prod/@dept}">number: {data($prod/number)
             }, name: {data($prod/name)}</li>
}</ul>
</html>






