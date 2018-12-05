(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 1-11. Joining multiple input documents :)
for $item in doc("order.xml")//item
let $name := doc("catalog.xml")//product[number = $item/@num]/name
return <item num="{$item/@num}"
             name="{$name}"
             quan="{$item/@quantity}"/>










