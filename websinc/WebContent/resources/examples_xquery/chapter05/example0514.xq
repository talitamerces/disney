(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 5-14. Turning content into markup :)
for $dept in distinct-values(doc("catalog.xml")/catalog/product/@dept)
return element {$dept}
               {doc("catalog.xml")/catalog/product[@dept = $dept]/name}







