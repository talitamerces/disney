(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 10-13. Prolog namespace declarations :)

declare default element namespace "http://datypic.com/report";
declare namespace cat = "http://datypic.com/cat";
declare namespace prod = "http://datypic.com/prod";
<report> {
  for $product in doc("prod_ns.xml")/prod:product
  return <lineItem>
           {$product/prod:number}
           {$product/prod:name}
         </lineItem>
} </report>
