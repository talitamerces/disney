(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 10-12. Using XML namespace declarations :)

<report xmlns="http://datypic.com/report"
             xmlns:cat="http://datypic.com/cat"
             xmlns:prod="http://datypic.com/prod"> {
  for $product in doc("prod_ns.xml")/prod:product
  return <lineItem>
           {$product/prod:number}
           {$product/prod:name}
         </lineItem>
} </report>

