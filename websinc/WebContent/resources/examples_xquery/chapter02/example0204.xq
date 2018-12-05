(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 2-4. Querying with namespaces :)
declare namespace prod = "http://datypic.com/prod";
for $product in doc("prod_ns.xml")/prod:product
return $product/prod:name






