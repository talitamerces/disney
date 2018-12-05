(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 6-16. Outer join :)
for $product in doc("catalog.xml")//product
return <product number="{$product/number}">{
                attribute price
                   {for $price in doc("prices.xml")//prices/priceList/prod
                    where $product/number = $price/@num
                    return $price/price}
  }</product>




