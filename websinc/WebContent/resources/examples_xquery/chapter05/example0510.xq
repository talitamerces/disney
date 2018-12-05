(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 5-10. Adding an attribute to an element :)
for $prod in doc("catalog.xml")/catalog/product[@dept = 'ACC']
return <product id="P{$prod/number}">
          {$prod/(@*, *)}
       </product>






