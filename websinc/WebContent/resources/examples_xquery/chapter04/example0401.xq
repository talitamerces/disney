(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 4-1. Prefixed name tests :)
declare namespace prod = "http://datypic.com/prod";
<prod:prodList>{
  doc("prod_ns.xml")/prod:product/prod:number
}</prod:prodList>


