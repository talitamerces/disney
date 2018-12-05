(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 15-3. Avoid re-evaluating the same expression :)

(: More efficient query :)

let $bargains := doc("prices.xml")/prices/priceList/prod[price < 30]
return if ($bargains)
       then <bargain-bin>{$bargains}</bargain-bin>
       else ()
