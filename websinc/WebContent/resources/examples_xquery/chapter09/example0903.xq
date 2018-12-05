(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-3. Useful function: remove-attribute-deep :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:remove-attributes-deep
 ($element as element(), $names as xs:string*) as element() {
   element { node-name($element)}
           { $element/@*[not(name() = $names)],
             for $child in $element/node()
             return if ($child instance of element())
                    then functx:remove-attributes-deep($child, $names)
                    else $child }
};
