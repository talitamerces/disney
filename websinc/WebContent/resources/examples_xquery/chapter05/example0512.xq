(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 5-12. Constructor with boundary whitespace :)

(: line below added to give $prod a value :)
let $prod := doc("catalog.xml")//product[1] return
<ul>
  {  <li>  <b> number:</b> { $prod/number }  </li>   }
</ul>







