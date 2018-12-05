(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 24-3. Two-way join in XQuery :)

for $item in doc("order.xml")//item,
    $product in doc("catalog2.xml")//product
where $item/@num = $product/number
return <item num="{$item/@num}"
             name="{$product/name}"
             quan="{$item/@quantity}"/>


