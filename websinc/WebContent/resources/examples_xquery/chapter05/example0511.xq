(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 5-11. Removing a child from an element :)
for $prod in doc("catalog.xml")/catalog/product[@dept = 'ACC']
return <product>
          {$prod/(@*, * except number)}
       </product>







