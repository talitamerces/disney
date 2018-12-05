(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 12-1. A query prolog :)

xquery version "1.0";
declare default element namespace "http://datypic.com/cat";
declare boundary-space preserve;
declare default collation "http://datypic.com/collation/custom";
declare namespace ord = "http://datypic.com/ord";
import schema namespace prod="http://datypic.com/prod"
                        at "http://datypic.com/prod.xsd";
declare function local:getProdNums
  ($catalog as element()) as xs:integer*
  {for $prod in $catalog/product
   return xs:integer($prod/number)};