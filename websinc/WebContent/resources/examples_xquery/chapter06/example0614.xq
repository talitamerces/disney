(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 6-14. Two-way join in a where clause :)
for $item in doc("order.xml")//item,
    $product in doc("catalog.xml")//product
where $item/@num = $product/number
return <item num="{$item/@num}"
             name="{$product/name}"
             quan="{$item/@quantity}"/>


