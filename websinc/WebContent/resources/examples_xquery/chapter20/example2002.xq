(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 20-2. Using names as result data :)

<html>{
  for $prod in doc("catalog.xml")//product
   return (<p>Product # {string($prod/number)}</p>,
           <ul>{
             for $child in $prod/(* except number)
             return <li>{local-name($child)}: {string($child)}</li>
           }</ul>)
}</html>

