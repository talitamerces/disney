(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-7. Using the change-elem-names function :)
(: See also: http://www.xqueryfunctions.com :)

declare function local:change-elem-names
  ($nodes as node()*, $old-names as xs:string+,
   $new-names as xs:string+) as node()* {

  if (count($old-names) != count($new-names))
  then error(xs:QName("Different_Number_Of_Names"))
  else for $node in $nodes
       return if ($node instance of element())
              then let $newName :=
                     if (local-name($node) = $old-names)
                     then $new-names[index-of($old-names, local-name($node))]
                     else local-name($node)
                   return element {$newName}
                     {$node/@*,
                      local:change-elem-names($node/node(),
                                              $old-names, $new-names)}
              else $node
};

let $order := doc("order.xml")/order
let $oldNames := ("order", "item")
let $newNames := ("purchaseOrder", "purchasedItem")
return local:change-elem-names($order, $oldNames, $newNames)
