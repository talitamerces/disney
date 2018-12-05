(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-15. Converting values without a lookup table :)
let $cat := doc("catalog.xml")/catalog
for $dept in distinct-values($cat/product/@dept)
return  <li>Department: {if ($dept = "ACC")
                        then "Accessories"
                        else if ($dept = "MEN")
                             then "Menswear"
                             else if ($dept = "WMN")
                                  then "Womens"
                                  else ()
               }  ({$dept})</li>
