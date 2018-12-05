(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 6-15. Three-way join in a where clause :)
for $item in doc("order.xml")//item,
    $product in doc("catalog.xml")//product,
    $price in doc("prices.xml")//prices/priceList/prod
where $item/@num = $product/number and $product/number = $price/@num
return <item num="{$item/@num}"
             name="{$product/name}"
             price="{$price/price}"/>



