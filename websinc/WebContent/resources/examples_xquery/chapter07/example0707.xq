(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 7-7. Using the unordered function :)
unordered(
for $item in doc("order.xml")//item,
    $product in doc("catalog.xml")//product
where $item/@num = $product/number
return <item number="{$item/@num}"
             name="{$product/name}"
             quantity="{$item/@quantity}"/>
)









