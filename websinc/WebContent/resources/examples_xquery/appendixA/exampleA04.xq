(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example A-4. Example of the last function :)

let $catalog := doc("catalog.xml")/catalog
for $prod in $catalog/product
return concat($prod/number,
            (if ($prod is $catalog/product[last()]) then (".") else (", ")))
