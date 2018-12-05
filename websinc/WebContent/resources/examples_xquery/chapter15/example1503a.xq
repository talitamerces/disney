(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 15-3. Avoid re-evaluating the same expression :)

(: Less efficient query :)

if (doc("prices.xml")/prices/priceList/prod[price < 30])
then <bargain-bin>{
       doc("prices.xml")/*/priceList/prod[price < 30]
     }</bargain-bin>
else ()
