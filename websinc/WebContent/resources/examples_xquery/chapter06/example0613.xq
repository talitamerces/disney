(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 6-13. Two-way join in a predicate :)
for $item in doc("order.xml")//item,
    $product in doc("catalog.xml")//product[number = $item/@num]
return <item num="{$item/@num}"
             name="{$product/name}"
             quan="{$item/@quantity}"/>

