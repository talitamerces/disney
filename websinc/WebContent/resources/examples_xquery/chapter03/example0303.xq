(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 3-3. Nested conditional expressions :)
for $prod in (doc("catalog.xml")/catalog/product)
return if ($prod/@dept = 'ACC')
       then <accessory>{data($prod/number)}</accessory>
       else if ($prod/@dept = 'WMN')
            then <womens>{data($prod/number)}</womens>
            else if ($prod/@dept = 'MEN')
                 then <mens>{data($prod/number)}</mens>
                 else <other>{data($prod/number)}</other>


