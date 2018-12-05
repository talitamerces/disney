(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-14. Testing for the last item using the is operator :)
<p>{ let $prods := doc("catalog.xml")//product
     for $prod in $prods
     return if ($prod is $prods[last()])
            then concat($prod/name,".")
            else concat($prod/name,", ")
}</p>

