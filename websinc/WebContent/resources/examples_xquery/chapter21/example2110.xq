(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 21-10. Testing for text nodes :)

declare function local:change-i-to-em
  ($node as element()) as node() {
  element {node-name($node)} {
    $node/@*,
    for $child in $node/node()
    return if ($child instance of text())
           then $child
           else if ($child instance of element(i))
                then <em>{$child/@*,$child/node()}</em>
                else if ($child instance of element())
                     then local:change-i-to-em($child)
                     else ()
  }
};
