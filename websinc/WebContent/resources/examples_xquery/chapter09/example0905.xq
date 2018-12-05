(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-5. Useful function: remove-elements-not-contents :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:remove-elements-not-contents
 ($element as element(), $names as xs:string*) as element() {
   element {node-name($element)}
           {$element/@*,
            for $child in $element/node()
            return if ($child instance of element())
                   then if ($child[name() = $names])
                        then $child/node()
                        else functx:remove-elements-not-contents($child, $names)
                   else $child }
};
