(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-4. Useful function: remove-elements-deep :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:remove-elements-deep
 ($element as element(), $names as xs:string*) as element() {
   element {node-name($element)}
           {$element/@*,
            for $child in $element/node()
            return if ($child instance of element())
                   then if ($child[name() = $names])
                        then ()
                        else functx:remove-elements-deep($child, $names)
                   else $child }
};
