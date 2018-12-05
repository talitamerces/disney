(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-13. Testing for the last item :)
<p>{ let $prods := doc("catalog.xml")//product
     let $numProds := count($prods)
     for $prod at $count in $prods
     return if ($count = $numProds)
            then concat($prod/name,".")
            else concat($prod/name,",")
}</p>
