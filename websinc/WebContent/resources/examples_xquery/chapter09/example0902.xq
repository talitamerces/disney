(: XQuery by Priscilla Walmsley (c) O'Reilly Media 2007 :)
(: Example 9-2. Useful function: remove-attribute :)
(: See also: http://www.xqueryfunctions.com :)

declare namespace functx = "http://www.functx.com";
declare function functx:remove-attributes
 ($element as element(), $names as xs:string*) as element() {

   element { node-name($element)}
           { $element/@*[not(name() = $names)],
             $element/node() }
};
