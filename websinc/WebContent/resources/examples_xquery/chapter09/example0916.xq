(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-16. Converting values with a lookup table :)
let $deptNames := <deptNames>
                    <dept code="ACC" name="Accessories"/>
                    <dept code="MEN" name="Menswear"/>
                    <dept code="WMN" name="Womens"/>
                  </deptNames>
let $cat := doc("catalog.xml")/catalog
for $dept in distinct-values($cat/product/@dept)
return <li>Department: {data($deptNames/dept[@code = $dept]/@name)
                  } ({$dept})</li>
