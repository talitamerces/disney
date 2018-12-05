(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 21-3. XML comment constructors :)

let $count := count(doc("catalog.xml")//product)
(: unordered list :)
return <ul>
         <!-- {concat(" List of ", $count, " products ")} -->
         {comment{concat(" List of ", $count, " products ")}}
       </ul>
